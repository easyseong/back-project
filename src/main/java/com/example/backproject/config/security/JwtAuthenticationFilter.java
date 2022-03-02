package com.example.backproject.config.security;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

@Slf4j
@RequiredArgsConstructor
public class JwtAuthenticationFilter extends GenericFilter { //발급받은토큰으로 인증을 진행하는 필터

    private final JwtTokenProvider jwtTokenProvider;

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        String token = jwtTokenProvider.resolveToken((HttpServletRequest) request);
        log.info("token............... :"+token);

        if (token != null && jwtTokenProvider.validateTokenExpiration(token)) { // .validateToken을 통해 토큰 유효성검사, 여길 못타나 ? ㄴㄴ탐
            Authentication auth = jwtTokenProvider.getAuthentication(token);
            log.info("auth............... :"+auth);
            SecurityContextHolder.getContext().setAuthentication(auth); //유효한사용자임을 Security에게 알려줌
        }
        log.info("request : "+request+", response : "+response);
        chain.doFilter(request, response); //다음 필터로 넘어가 AuthenticationFilter에서 이미 인증되어 있는 객체를 통해 인증이 되게 된다
    }
}
