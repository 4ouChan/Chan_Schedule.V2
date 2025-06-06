package com.example.schedulev2.dto;

import lombok.Getter;

@Getter
public class LoginRequestDto {

    private final Long userId;

    private final String email;

    private final String password;

    public LoginRequestDto(Long userId, String email, String password) {
        this.userId = userId;
        this.email = email;
        this.password = password;
    }
}
// 제출