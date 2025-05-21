package com.example.schedulev2.service;

import com.example.schedulev2.dto.ScheduleResponseDto;
import com.example.schedulev2.entity.ScheduleEntity;
import com.example.schedulev2.repository.ScheduleRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;
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

    @Transactional
    public ScheduleResponseDto updateSchedule(Long scheduleId, String title, String schedule) {

        ScheduleEntity scheduleEntity = new ScheduleEntity(title, schedule);

        Optional<ScheduleEntity> checkSchedule = scheduleRepository.findById(scheduleId);

        ScheduleEntity updateSchedule = checkSchedule.get();

        updateSchedule.setSchedule(scheduleEntity.getTitle(), scheduleEntity.getSchedule());

        ScheduleResponseDto scheduleResponseDto = new ScheduleResponseDto(updateSchedule.getScheduleId(), updateSchedule.getTitle(), updateSchedule.getSchedule(), updateSchedule.getCreateDate(), updateSchedule.getUpdateDate());

        return scheduleResponseDto;
    }

    public List<ScheduleResponseDto> deleteSchedule(Long scheduleId) {

        Optional<ScheduleEntity> findByIdSchedule = scheduleRepository.findById(scheduleId);

        ScheduleEntity scheduleEntity = findByIdSchedule.get();

        scheduleRepository.delete(scheduleEntity);

        return scheduleRepository.findAll()
                .stream()
                .map(ScheduleResponseDto::toDto)
                .toList();
    }
}
