package com.example.backproject.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MemberRegisterRequestDto {
    private String username;
    private String email;
    private String password;
    private String phonenumber;
    private String address;
}
