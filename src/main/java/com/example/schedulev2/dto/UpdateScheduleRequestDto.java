package com.example.schedulev2.dto;

import lombok.Getter;

@Getter
public class UpdateScheduleRequestDto {

    private final String title;

    private final String schedule;

    private final String password;

    public UpdateScheduleRequestDto(String title, String schedule, String password) {
        this.title = title;
        this.schedule = schedule;
        this.password = password;
    }
}
