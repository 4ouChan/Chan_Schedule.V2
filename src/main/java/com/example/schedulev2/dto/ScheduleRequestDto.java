package com.example.schedulev2.dto;

import lombok.Getter;

@Getter
public class ScheduleRequestDto {

    private final String title;

    private final String schedule;

    public ScheduleRequestDto(Long userId, String title, String schedule) {
        this.title = title;
        this.schedule = schedule;
    }


}
// 제출
