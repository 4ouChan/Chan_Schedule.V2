package com.example.schedulev2.dto;

import lombok.Getter;

@Getter
public class UpdateUserPasswordResponseDto {

    private final String message;

    public UpdateUserPasswordResponseDto(String message) {
        this.message = message;
    }
}
// 제출