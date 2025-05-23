package com.example.schedulev2.service;

import com.example.schedulev2.dto.ScheduleResponseDto;
import com.example.schedulev2.entity.ScheduleEntity;
import com.example.schedulev2.entity.UserEntity;
import com.example.schedulev2.repository.ScheduleRepository;
import com.example.schedulev2.repository.UserRepository;
import jakarta.servlet.http.HttpSession;
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

    public ScheduleResponseDto createSchedule(HttpSession session, String title, String schedule) {

        Long userId = (Long) session.getAttribute("userId");

        Optional<UserEntity> findUser = userRepository.findById(userId);
        UserEntity userEntity = findUser.get();

        if (userEntity != null) {

            ScheduleEntity createSchedule = new ScheduleEntity(title, schedule);

            createSchedule.setUserEntity(userEntity);

            scheduleRepository.save(createSchedule);

            ScheduleResponseDto responseDto = new ScheduleResponseDto(
                    userId,
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
    public ScheduleResponseDto updateSchedule(Long scheduleId, String title, String schedule, String password) {

        Optional<ScheduleEntity> findScheduleById = scheduleRepository.findById(scheduleId);
        ScheduleEntity findSchedule = findScheduleById.get();

        if (password.equals(findSchedule.getUserEntity().getPassword())) {

            findSchedule.setSchedule(title, schedule);

            ScheduleResponseDto scheduleResponseDto = new ScheduleResponseDto(
                    findSchedule.getUserEntity().getUserId(),
                    findSchedule.getScheduleId(),
                    findSchedule.getTitle(),
                    findSchedule.getSchedule(),
                    findSchedule.getCreateDate(),
                    findSchedule.getUpdateDate()

            );

            return scheduleResponseDto;
        } else {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }
    }

    public List<ScheduleResponseDto> deleteSchedule(Long scheduleId, String password) {

        Optional<ScheduleEntity> findByIdSchedule = scheduleRepository.findById(scheduleId);

        ScheduleEntity scheduleEntity = findByIdSchedule.get();

        if (password.equals(scheduleEntity.getUserEntity().getPassword())) {

            scheduleRepository.delete(scheduleEntity);

            return scheduleRepository.findAll()
                    .stream()
                    .map(ScheduleResponseDto::toScheduleDto)
                    .toList();
        } else {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }
    }
}
