package com.example.backproject.dto.user;

import com.example.backproject.domain.user.User;
import lombok.Getter;
import org.springframework.security.core.GrantedAuthority;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.List;

@Getter
public class UserResponseDto {
    private final Long userId;
    private final List<String> roles;
    private final LocalDateTime createdDate;

    public UserResponseDto(User user) {
        this.userId = user.getUserId();
        this.roles = user.getRoles();
        this.createdDate = user.getCreatedDate();
    }
}
