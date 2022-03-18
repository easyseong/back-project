package com.example.backproject.controller;

import com.example.backproject.result.SingleResult;
import com.example.backproject.service.ResponseService;
import com.example.backproject.dto.MemberLoginRequestDto;
import com.example.backproject.dto.MemberLoginResponseDto;
import com.example.backproject.dto.MemberRegisterRequestDto;
import com.example.backproject.dto.MemberRegisterResponseDto;
import com.example.backproject.service.SignServiceImpl;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.web.bind.annotation.*;

@Log4j2
@RestController
@RequiredArgsConstructor
@RequestMapping("/sign")
@CrossOrigin(origins = "http://localhost:4200")
public class SignController {

    private final SignServiceImpl signServiceImpl;
    private final ResponseService responseService;

    @PostMapping("/register") //등록, create
    public SingleResult<MemberRegisterResponseDto> register(@RequestBody MemberRegisterRequestDto requestDto) {
        log.info("requestDto :  "+requestDto);
        MemberRegisterResponseDto responseDto = signServiceImpl.registerMember(requestDto);
        log.info("responseDto :  "+responseDto);
        return responseService.getSingleResult(responseDto);
    }

    @PostMapping("/login")
    public SingleResult<MemberLoginResponseDto> login(@RequestBody MemberLoginRequestDto requestDto) { //바디에 email, password 입력
        MemberLoginResponseDto responseDto = signServiceImpl.loginMember(requestDto); //id, 로그인 동시에 발급된 token
        SingleResult<MemberLoginResponseDto> result = responseService.getSingleResult(responseDto);
        log.info("MemberLoginResponseDto : " + result); //결과: com.example.backproject.result.SingleResult@4e6de22b
        return result;
    }
}
