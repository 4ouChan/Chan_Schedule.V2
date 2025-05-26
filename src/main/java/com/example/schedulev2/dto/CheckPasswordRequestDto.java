package com.example.schedulev2.dto;

import lombok.Getter;

@Getter
public class CheckPasswordRequestDto {

    private final String password;

    public CheckPasswordRequestDto(String password) {
        this.password = password;
    }
}
// 제출