package com.example.backproject.controller;


import com.example.backproject.model.Member;
import com.example.backproject.service.SignServiceImpl;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;

@CrossOrigin(origins = "http://localhost:4200")
@Log4j2
@RestController
@RequiredArgsConstructor
public class HomeController {

    private final SignServiceImpl signService;


    @GetMapping("/")
    public List<Member> main() {
        log.info("main.......................................................");
        return signService.selectList();
    }

    @GetMapping("/hello")
    public String hello() {
        log.info("hello.......................................................");
        return "Hello";
    }

}
