package com.example.backproject.controller;


import com.example.backproject.dao.MemberDao;
import com.example.backproject.model.Member;
import com.example.backproject.service.SignService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;


@Log4j2
@RestController
@RequiredArgsConstructor
public class HomeController {


    @GetMapping("/main")
    public List<String> main() {
        log.info("main.......................................................");
        return Arrays.asList("main","Hello");
    }

    @GetMapping("/hello")
    public String hello() {
        log.info("hello.......................................................");
        return "Hello";
    }

}
