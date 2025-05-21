package com.example.schedulev2.dto;

import com.example.schedulev2.entity.ScheduleEntity;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class ScheduleResponseDto {

    private final Long id;

    private final String title;

    private final String schedule;

    private LocalDateTime createDate;

    private LocalDateTime updateDate;


    public ScheduleResponseDto(Long id, String title, String schedule, LocalDateTime createDate, LocalDateTime updateDate) {
        this.id = id;
        this.title = title;
        this.schedule = schedule;
        this.createDate = createDate;
        this.updateDate = updateDate;
    }

    public ScheduleResponseDto (Long id, String title, String schedule, LocalDateTime updateDate) {
        this.id = id;
        this.title = title;
        this.schedule = schedule;
        this.updateDate = updateDate;
    }

    public static ScheduleResponseDto toDto(ScheduleEntity scheduleEntity) {
        return new ScheduleResponseDto(
                scheduleEntity.getScheduleId(),
                scheduleEntity.getTitle(),
                scheduleEntity.getSchedule(),
                scheduleEntity.getCreateDate(),
                scheduleEntity.getUpdateDate()
        );
    }
}
