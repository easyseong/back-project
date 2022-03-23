package com.example.backproject.model;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Member {
    private Long id;
    private String username;
    private String email;
    private String password;
    private String phonenumber;
    private String address;

}
