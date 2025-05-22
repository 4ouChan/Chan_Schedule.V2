package com.example.schedulev2.dto;

import lombok.Getter;

@Getter
public class UpdateUserPasswordRequestDto {

    private final String userPassword;

    private final String newUserPassword;

    public UpdateUserPasswordRequestDto(String userPassword, String newUserPassword) {
        this.userPassword = userPassword;
        this.newUserPassword = newUserPassword;
    }
}
