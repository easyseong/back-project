package com.example.backproject.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.security.core.userdetails.UserDetails;

@ToString
@Getter
@Setter

public class User implements UserDetails {
    String msrl;
    String password;
    String username;


}
