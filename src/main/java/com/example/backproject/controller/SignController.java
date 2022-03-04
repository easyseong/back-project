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
@CrossOrigin(origins = "http://localhost:3000")
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
    public SingleResult<MemberLoginResponseDto> login(@RequestBody MemberLoginRequestDto requestDto) {
        MemberLoginResponseDto responseDto = signServiceImpl.loginMember(requestDto);
        return responseService.getSingleResult(responseDto);
    }
}
