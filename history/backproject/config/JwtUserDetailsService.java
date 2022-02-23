package com.example.backproject.config;

//- UserDetailsService를 implements 해주었다는 것이 중요합니다.
//  DB에서 UserDetail를 얻어와 AuthenticationManager에게 제공하는 역할을 수행합니다.
//
//        - 이번에는 DB 없이 하드코딩된 User List에서 get userDetail합니다.
//
//        - password 부분이 해싱되어있는데, Spring Security 5.0에서는 Password를 BryptEncoder를 통해 Brypt화하여 저장하고 있습니다. 따라서 하드코딩해서 넣어줍니다.
//
//        - https://www.javainuse.com/onlineBcrypt 에서 user_pw를 Bcrypt화할 수 있습니다.
//
//        - id : user_id, pw: user_pw로 고정해 사용자 확인하고, 사용자 확인 실패시 throw Exception을 제공합니다.

import java.util.ArrayList;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@RequiredArgsConstructor
public class JwtUserDetailsService implements UserDetailsService {

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        if ("user_id".equals(username)) {
            return new User("user_id", "$2a$10$jCvWm3NXDRFs/EfuI4h4.u0ZxNocv.ZkgEy6qbjVXrfQ5.KzLfhAe", new ArrayList<>());
        } else {
            throw new UsernameNotFoundException("User not found with username: " + username);
        }
    }
}
