package com.example.schedulev2.service;

import com.example.schedulev2.dto.ScheduleResponseDto;
import com.example.schedulev2.entity.ScheduleEntity;
import com.example.schedulev2.repository.ScheduleRepository;
import org.springframework.stereotype.Service;

@Service
public class ScheduleService {

    private final ScheduleRepository scheduleRepository;

    public ScheduleService(ScheduleRepository scheduleRepository) {
        this.scheduleRepository = scheduleRepository;
    }


    public ScheduleResponseDto createSchedule(String title, String schedule) {

        ScheduleEntity createSchedule = new ScheduleEntity(title, schedule);

        scheduleRepository.save(createSchedule);

        ScheduleResponseDto responseDto = new ScheduleResponseDto(
                createSchedule.getScheduleId(),
                createSchedule.getTitle(),
                createSchedule.getSchedule(),
                createSchedule.getCreateDate(),
                createSchedule.getUpdateDate()
        );

        return responseDto;
    }
}
