package com.example.backproject.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

@RestController
public class HomeController {

    @GetMapping("/hello")
    public String hello() {
        return "hello world";
    }

    @GetMapping("/api/hello")
    public String time() {
        return "안녕하세요.  현재는 " + new Date() + "입니다. \n";
    }

}
