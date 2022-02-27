package com.example.backproject.controller;

import com.example.backproject.domain.result.SingleResult;
import com.example.backproject.domain.service.ResponseService;
import com.example.backproject.dto.MemberLoginRequestDto;
import com.example.backproject.dto.MemberLoginResponseDto;
import com.example.backproject.dto.MemberRegisterRequestDto;
import com.example.backproject.dto.MemberRegisterResponseDto;
import com.example.backproject.service.SignService;
import com.example.backproject.service.SignServiceImpl;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Log4j2
@RestController
@RequiredArgsConstructor
@RequestMapping("/sign")
public class SignController {

    private final SignServiceImpl signServiceImpl;
    private final ResponseService responseService;

    @PostMapping("/register") //등록
    public SingleResult<MemberRegisterResponseDto> register(@RequestBody MemberRegisterRequestDto requestDto) {
        log.info("requestDto :  "+requestDto);
        MemberRegisterResponseDto responseDto = signServiceImpl.registerMember(requestDto);
        log.info("responseDto :  "+responseDto); //id값 안가져올 듯
        return responseService.getSingleResult(responseDto);
    }

    @PostMapping("/login")
    public SingleResult<MemberLoginResponseDto> login(@RequestBody MemberLoginRequestDto requestDto) {
        MemberLoginResponseDto responseDto = signServiceImpl.loginMember(requestDto);
        return responseService.getSingleResult(responseDto);
    }
}