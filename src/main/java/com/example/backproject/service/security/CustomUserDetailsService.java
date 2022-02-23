package com.example.backproject.service.security;


import com.example.backproject.advice.exception.UserNotFoundCException;
import com.example.backproject.domain.user.UserJpaRepo;
import com.example.backproject.entity.User;
import com.example.backproject.service.SignService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RequiredArgsConstructor
@Service
public class CustomUserDetailsService implements UserDetailsService {

    private Logger logger = LoggerFactory.getLogger(CustomUserDetailsService.class);

    @Resource(name = "signService")
    private SignService signService;


    @Override
    public UserDetails loadUserByUsername(String userPk) {
        Map<String, Object> param = new HashMap<String, Object>();
        param.put("userId", userPk);

        User result = signService.getUser(param);
        List<String > list = new ArrayList<String>();
        list.add("ROLE_USER");
        list.add("ADMIN");
        result.setRoles(list);

        return result;
    }
}
