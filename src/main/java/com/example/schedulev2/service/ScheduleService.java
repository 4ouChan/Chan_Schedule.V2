package com.example.schedulev2.service;

import com.example.schedulev2.dto.ScheduleResponseDto;
import com.example.schedulev2.entity.ScheduleEntity;
import com.example.schedulev2.entity.UserEntity;
import com.example.schedulev2.repository.ScheduleRepository;
import com.example.schedulev2.repository.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@Service
public class ScheduleService {

    private final ScheduleRepository scheduleRepository;
    private final UserRepository userRepository;

    public ScheduleService(ScheduleRepository scheduleRepository, UserRepository userRepository) {
        this.scheduleRepository = scheduleRepository;
        this.userRepository = userRepository;
    }

    public ScheduleResponseDto createSchedule(Long userId, String title, String schedule) {

        Optional<UserEntity> findUser = userRepository.findById(userId);
        UserEntity userEntity = findUser.get();

        if (userEntity != null) {

            ScheduleEntity createSchedule = new ScheduleEntity(title, schedule);

            createSchedule.setUserEntity(userEntity);

            scheduleRepository.save(createSchedule);

            ScheduleResponseDto responseDto = new ScheduleResponseDto(
                    createSchedule.getUserEntity().getUserId(),
                    createSchedule.getScheduleId(),
                    createSchedule.getTitle(),
                    createSchedule.getSchedule(),
                    createSchedule.getCreateDate(),
                    createSchedule.getUpdateDate()
            );

            return responseDto;
        } else {

            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }

    }

    public List<ScheduleResponseDto> findAllSchedule() {

        return scheduleRepository.findAll()
                .stream()
                .map(ScheduleResponseDto::toScheduleDto)
                .toList();
    }

    public ScheduleResponseDto findByIdSchedule(Long scheduleId) {

        Optional<ScheduleEntity> scheduleResponseDto = scheduleRepository.findById(scheduleId);

        ScheduleEntity responseDto = scheduleResponseDto.get();

        return new ScheduleResponseDto(
                responseDto.getUserEntity().getUserId(),
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

        ScheduleResponseDto scheduleResponseDto = new ScheduleResponseDto(
                scheduleEntity.getUserEntity().getUserId(),
                updateSchedule.getScheduleId(),
                updateSchedule.getTitle(),
                updateSchedule.getSchedule(),
                updateSchedule.getCreateDate(),
                updateSchedule.getUpdateDate()
        );

        return scheduleResponseDto;
    }

    public List<ScheduleResponseDto> deleteSchedule(Long scheduleId) {

        Optional<ScheduleEntity> findByIdSchedule = scheduleRepository.findById(scheduleId);

        ScheduleEntity scheduleEntity = findByIdSchedule.get();

        scheduleRepository.delete(scheduleEntity);

        return scheduleRepository.findAll()
                .stream()
                .map(ScheduleResponseDto::toScheduleDto)
                .toList();
    }
}
