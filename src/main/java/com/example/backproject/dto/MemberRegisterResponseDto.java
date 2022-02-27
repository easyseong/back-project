package com.example.backproject.dto;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class MemberRegisterResponseDto { //왜 아이디랑 이메일이냐 이메일이랑 비번이 아니고

    private Long id;

    private String email;

    @Builder
    public MemberRegisterResponseDto(Long id, String email) {
        this.id = id;
        this.email = email;
    }
}