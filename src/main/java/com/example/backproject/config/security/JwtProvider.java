package com.example.backproject.config.security;


import io.jsonwebtoken.*;
import io.jsonwebtoken.impl.Base64UrlCodec;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;
import java.nio.charset.StandardCharsets;
import java.util.Base64;
import java.util.Date;
import java.util.List;
import java.lang.String;

@Slf4j
@RequiredArgsConstructor
@Component
public class JwtProvider {

    @Value("spring.jwt.secret")
    private String secretKey;
   // private String ROLES = "roles";
    private final Long tokenValidMillisecond = 60 * 60 * 1000L; // 1 hour
   // private final Long refreshTokenValidMillisecond = 14 * 24 * 60 * 60 * 1000L; // 14 day
    private final UserDetailsService userDetailsService;

    @PostConstruct
    protected void init() {
        // 암호화
        //secretKey = Base64UrlCodec.BASE64URL.encode(secretKey.getBytes(StandardCharsets.UTF_8));
        secretKey = Base64.getEncoder().encodeToString(secretKey.getBytes());
    }

    // Jwt 생성
    public String createToken(String userPk, List<String> roles) {

        // Claims 에 user 구분을 위한 User pk 및 authorities 목록 삽입
        Claims claims = Jwts.claims().setSubject(userPk);
        claims.put("roles", roles);

        // 생성날짜, 만료날짜를 위한 Date
        Date now = new Date();


        return Jwts.builder()
                .setClaims(claims)
                .setIssuedAt(now)
                .setExpiration(new Date(now.getTime()+tokenValidMillisecond))
                .signWith(SignatureAlgorithm.HS256, secretKey)
                .compact();
    }

    // Jwt 로 인증정보를 조회
    public Authentication getAuthentication(String token) {

//        // Jwt 에서 claims 추출
//        Claims claims = parseClaims(token);
//
//        // 권한 정보가 없음
//        if (claims.get(ROLES) == null) {
//            throw new CAuthenticationEntryPointException();
//        }

        UserDetails userDetails = userDetailsService.loadUserByUsername(this.getUserPk(token));
        return new UsernamePasswordAuthenticationToken(userDetails, "", userDetails.getAuthorities());
    }

    public String getUserPk(String token) {
        return Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token).getBody().getSubject();
    }

    // HTTP Request 의 Header 에서 Token Parsing -> "X-AUTH-TOKEN: jwt"
    public String resolveToken(HttpServletRequest request) {
        return request.getHeader("X-AUTH-TOKEN");
    }

    // jwt 의 유효성 및 만료일자 확인
    public boolean validationToken(String token) {
        try {
            Jws<Claims> claimsJws = Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token);
            return !claimsJws.getBody().getExpiration().before(new Date());
        } catch (Exception e) {
            return false;
        }
    }
}
