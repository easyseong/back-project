package com.example.backproject.config.security.jwt;

import io.jsonwebtoken.*;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;
import java.util.Base64;
import java.util.Date;

@Log4j2
@Component
@RequiredArgsConstructor
public class JwtTokenProvider { //Access Token을 발행

    @Value("${spring.jwt.secretKey}")
    private String secretKey;

    private long tokenValidTime = 1000L * 60 * 30; // 토큰의 만료기간 30분

    private final UserDetailsService memberDetailsService;

    @PostConstruct
    protected void init() {
        secretKey = Base64.getEncoder().encodeToString(secretKey.getBytes());
    }


    //토큰을 생성
    public String createToken(String email) {
        Claims claims = Jwts.claims().setSubject(email); //payload에 저장되는 토큰단위. 토큰의 Subject는 중복되지 않는 값인 email로 지정
        Date now = new Date();

        return Jwts.builder()
                .setClaims(claims) //정보저장
                .setIssuedAt(now) //토큰발행 시간 정보
                .setExpiration(new Date(now.getTime() + tokenValidTime)) //30분동안
                .signWith(SignatureAlgorithm.HS256, secretKey) // 서명의 알고리즘은 HS256, 키는 지정값 사용
                .compact();
    }


    //JWT토큰에서 인증 정보 조회회
   public Authentication getAuthentication(String token) { //토큰으로 인증 객체를 끌고옴
        log.info("getAuthentication-token : "+token);
        log.info("userDetails : "+getMemberEmail(token));
        UserDetails userDetails = memberDetailsService.loadUserByUsername(getMemberEmail(token)); //여기!!!!!!!!
        log.info("getAuthentication 실행됨 : "+userDetails );
        return new UsernamePasswordAuthenticationToken(userDetails, "", userDetails.getAuthorities());
    }

    public String getMemberEmail(String token) { //이메일을 얻기 위해 토큰을 디코딩
        try {
            return Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token).getBody().getSubject();
        } catch(ExpiredJwtException e) {
            return e.getClaims().getSubject();
        }
    }

    public String resolveToken(HttpServletRequest req) { //HTTP 헤더에 저장돼있는토큰을 사용하기 위해 꺼내옴
        return req.getHeader("X-AUTH-TOKEN");
    } //헤더에서 토큰을 가져옴

    public boolean validateTokenExpiration(String token) {
        try {
            Jws<Claims> claims = Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token);
            return !claims.getBody().getExpiration().before(new Date());
        } catch(Exception e) {
            return false;
        }
    }
}
