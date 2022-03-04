package com.example.backproject.config.security.member;

import com.example.backproject.advice.exception.MemberNotFoundException;
import com.example.backproject.config.security.member.MemberDetails;
import com.example.backproject.dao.MemberDao;
import com.example.backproject.model.Member;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class MemberDetailsService implements UserDetailsService {

    private final MemberDao memberDao;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Member member = memberDao.readMember(email).orElseThrow(MemberNotFoundException::new); //예외추가

        return MemberDetails.builder()
                .email(member.getEmail())
                .password(member.getPassword())
                .build();
    }
}
