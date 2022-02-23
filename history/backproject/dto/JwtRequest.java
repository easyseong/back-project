package com.example.backproject.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.web.bind.annotation.GetMapping;

import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class JwtRequest implements Serializable { //일종의 Dto, 가공된 정보를 받을 수 있도록 합니다

    private static final long serialVersionUID = 5926468583005150707L;

    private String username;

    private String password;
}
