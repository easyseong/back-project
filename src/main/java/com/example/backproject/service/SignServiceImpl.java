package com.example.backproject.service;

import com.example.backproject.dao.MemberDao;
import com.example.backproject.model.Member;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class SignServiceImpl implements SignService {

    @Autowired
    MemberDao memberDao;

    private final JwtTokenProvider jwtTokenProvider;

    private final PasswordEncoder passwordEncoder;


    //회원가입
    @Transactional
    public MemberRegisterResponseDto registerMember(MemberRegisterRequestDto requestDto) { // MemberRegisterRequestDto.class 작성
        validateDuplicated(requestDto.getEmail()); //아이디 중복 확인, 존재하면 예외를 던진다
        //중복이 아니라면 db에 저장
        Member user = memberDao.create(
                Member.builder()
                        .email(requestDto.getEmail())
                        .password(passwordEncoder.encode(requestDto.getPassword()))
                        .build());
        return new MemberRegisterResponseDto(user.getId(), user.getEmail());
    }

    /**
     * Unique한 값을 가져야하나, 중복된 값을 가질 경우를 검증, 아이디 중복 확인
     * @param email
     */
    public void validateDuplicated(String email) {
        if (memberDao.selectList(email).isEmpty())
            throw new MemberEmailAlreadyExistsException(); //예외클래스 만들기
    }

    public MemberLoginResponseDto loginMember(MemberLoginRequestDto requestDto) {
        Member member = memberRepository.findByEmail(requestDto.getEmail()).orElseThrow(LoginFailureException::new);
        if (!passwordEncoder.matches(requestDto.getPassword(), member.getPassword()))
            throw new LoginFailureException();
        return new MemberLoginResponseDto(member.getId(), jwtTokenProvider.createToken(requestDto.getEmail()));

    }
}
