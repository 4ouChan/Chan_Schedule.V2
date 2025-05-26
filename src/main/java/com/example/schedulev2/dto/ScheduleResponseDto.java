package com.example.schedulev2.dto;

import com.example.schedulev2.entity.ScheduleEntity;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class ScheduleResponseDto {

    private final Long userId;

    private final Long scheduleId;

    private final String title;

    private final String schedule;

    private LocalDateTime createDate;

    private LocalDateTime updateDate;


    public ScheduleResponseDto(Long userId, Long scheduleId, String title, String schedule, LocalDateTime createDate, LocalDateTime updateDate) {
        this.userId = userId;
        this.scheduleId = scheduleId;
        this.title = title;
        this.schedule = schedule;
        this.createDate = createDate;
        this.updateDate = updateDate;
    }

    public static ScheduleResponseDto toScheduleDto(ScheduleEntity scheduleEntity) {
        return new ScheduleResponseDto(
                scheduleEntity.getUserEntity().getUserId(),
                scheduleEntity.getScheduleId(),
                scheduleEntity.getTitle(),
                scheduleEntity.getSchedule(),
                scheduleEntity.getCreateDate(),
                scheduleEntity.getUpdateDate()
        );
    }
}
// 제출