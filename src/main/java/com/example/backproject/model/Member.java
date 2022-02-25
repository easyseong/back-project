package com.example.backproject.model;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Member {

    private int id;
    private String email;
    private String password;
}
