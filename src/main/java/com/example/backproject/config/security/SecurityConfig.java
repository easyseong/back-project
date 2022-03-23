package com.example.backproject.config.security;

import com.example.backproject.config.security.custom.CustomAccessDeniedHandler;
import com.example.backproject.config.security.custom.CustomAuthenticationEntryPoint;
import com.example.backproject.config.security.jwt.JwtAuthenticationFilter;
import com.example.backproject.config.security.jwt.JwtTokenProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;

@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private final JwtTokenProvider jwtTokenProvider;


    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.cors().configurationSource(request -> new CorsConfiguration().applyPermitDefaultValues());
        http
                .httpBasic().disable() // rest api이므로 기본설정 미사용
                .csrf().disable() // rest api이므로 csrf 보안 미사용
                .formLogin().disable()//로그인페이지 미사용
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS) // jwt로 인증하므로 세션 미사용
                .and()

                .authorizeRequests()
                .antMatchers("/").permitAll()
                .antMatchers("/main").permitAll()
                .antMatchers("/sign/**").permitAll()
                .antMatchers("/exception/**").permitAll()
                .anyRequest().authenticated()
                .and()

                .exceptionHandling().authenticationEntryPoint(new CustomAuthenticationEntryPoint()) //인증필요페이지접근시 예외
                .and()
                .exceptionHandling().accessDeniedHandler(new CustomAccessDeniedHandler())//권한필요페이지 예외
                .and()
                .addFilterBefore(new JwtAuthenticationFilter(jwtTokenProvider), UsernamePasswordAuthenticationFilter.class); // jwt 필터 추가
    }
}
