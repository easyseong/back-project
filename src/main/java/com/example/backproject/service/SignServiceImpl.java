package com.example.backproject.service;

import com.example.backproject.advice.exception.LoginFailureException;
import com.example.backproject.advice.exception.MemberEmailAlreadyExistsException;
import com.example.backproject.config.security.JwtTokenProvider;
import com.example.backproject.dao.MemberDao;
import com.example.backproject.dto.MemberLoginRequestDto;
import com.example.backproject.dto.MemberLoginResponseDto;
import com.example.backproject.dto.MemberRegisterRequestDto;
import com.example.backproject.dto.MemberRegisterResponseDto;
import com.example.backproject.model.Member;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Log4j2
@Service
@Transactional//(readOnly = true)
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

        Member user = Member.builder()
                .email(requestDto.getEmail())
                .password(passwordEncoder.encode(requestDto.getPassword()))
                .build();
        log.info("받아온 member객체 : "+user);
        create(user.getEmail(), user.getPassword());
        log.info("create함수 실행 됨");

        return new MemberRegisterResponseDto(user.getId(), user.getEmail()); // 멤버가 쿼리로 저장된 후에 반환된 member객체는 MemberRegisterResponseDto.class 에 담긴다. 왜담지? 굳이 아이디랑 이메일을?
    }

    /**
     * Unique한 값을 가져야하나, 중복된 값을 가질 경우를 검증, 아이디 중복 확인
     * @param email
     */
    public void validateDuplicated(String email) {
        if (! selectEmail(email).isEmpty()) {
            log.info("중복된 아이디 : "+selectEmail(email));
            throw new MemberEmailAlreadyExistsException(); //예외클래스 작성
        }

    }


    //로그인, 입력한 비번이 틀리면 예외발생 아니라면 jwt토큰 발행
    public MemberLoginResponseDto loginMember(MemberLoginRequestDto requestDto) {
        //Member member = memberRepository.findByEmail(requestDto.getEmail()).orElseThrow(LoginFailureException::new); //orElseThrow => 값이 없으면 예외를 던져줌

        Member member = readMember(requestDto.getEmail());
        if(member == null) {
            throw new LoginFailureException();
        }

        if (!passwordEncoder.matches(requestDto.getPassword(), member.getPassword())) //요청dto의 비번과 가져온 멤버정보의 비번 비교
            throw new LoginFailureException();
        return new MemberLoginResponseDto(member.getId(), jwtTokenProvider.createToken(requestDto.getEmail())); // !!여기가 토큰 발급 구간 프론트에 넘겨주어 헤더에 토큰을 담음

    }

    @Override
    public List<Member> selectList() {
        return memberDao.selectList();
    }

    @Override
    public List<Member> selectEmail(String email) {
        return memberDao.selectEmail(email);
    }

    @Override
    public Member readMember(String email) {
        return memberDao.readMember(email);
    }

    @Override
    public void create(String email, String password) {
            log.info("create함수 호출 : email= "+email+" password = "+password);
            memberDao.create(email, password);
        //return readMember(email); //갓 만든 유저의 이메일을 어떻게 받아오지? 멤버객체랑 이베일을 같이 보내버려
    }



}