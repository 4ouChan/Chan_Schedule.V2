package com.example.schedulev2.dto;

import lombok.Getter;

@Getter
public class CreateScheduleRequestDto {

    private final String title;

    private final String schedule;

    public CreateScheduleRequestDto(String title, String schedule) {
        this.title = title;
        this.schedule = schedule;
    }
}
