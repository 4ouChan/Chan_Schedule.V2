package com.example.schedulev2.service;

import com.example.schedulev2.dto.ScheduleResponseDto;
import com.example.schedulev2.entity.ScheduleEntity;
import com.example.schedulev2.repository.ScheduleRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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

    public List<ScheduleResponseDto> findAllSchedule() {

        return scheduleRepository.findAll()
                .stream()
                .map(ScheduleResponseDto::toDto)
                .toList();
    }

    public ScheduleResponseDto findByIdSchedule(Long scheduleId) {

        Optional<ScheduleEntity> scheduleResponseDto = scheduleRepository.findById(scheduleId);

        ScheduleEntity responseDto = scheduleResponseDto.get();

        return new ScheduleResponseDto(
                responseDto.getScheduleId(),
                responseDto.getTitle(),
                responseDto.getSchedule(),
                responseDto.getCreateDate(),
                responseDto.getUpdateDate()
        );
    }
}
