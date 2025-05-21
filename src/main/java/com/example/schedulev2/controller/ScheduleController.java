package com.example.schedulev2.controller;

import com.example.schedulev2.dto.ScheduleRequestDto;
import com.example.schedulev2.dto.ScheduleResponseDto;
import com.example.schedulev2.service.ScheduleService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/schedules")
public class ScheduleController {

    private final ScheduleService scheduleService;

    public ScheduleController(ScheduleService scheduleService) {
        this.scheduleService = scheduleService;
    }

    @PostMapping
    public ResponseEntity<ScheduleResponseDto> createScheduleAPI(@RequestBody ScheduleRequestDto dto) {

        ScheduleResponseDto scheduleResponseDto =
                scheduleService.createSchedule(dto.getTitle(), dto.getSchedule());

        return new ResponseEntity<>(scheduleResponseDto, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<ScheduleResponseDto>> findAllScheduleAPI() {

        List<ScheduleResponseDto> allSchedule = scheduleService.findAllSchedule();

        return new ResponseEntity<>(allSchedule, HttpStatus.OK);
    }

    @GetMapping("/{scheduleId}")
    public ResponseEntity<ScheduleResponseDto> findByIdScheduleAPI(@PathVariable Long scheduleId) {

        ScheduleResponseDto byIdSchedule = scheduleService.findByIdSchedule(scheduleId);

        return new ResponseEntity<>(byIdSchedule, HttpStatus.OK);
    }

    @PutMapping("/{scheduleId}")
    public ResponseEntity<ScheduleResponseDto> updateScheduleAPI(
            @PathVariable Long scheduleId,
            @RequestBody ScheduleRequestDto dto
    ) {
        ScheduleResponseDto scheduleResponseDto =
                scheduleService.updateSchedule(scheduleId, dto.getTitle(), dto.getSchedule()
                );

        return new ResponseEntity<>(scheduleResponseDto, HttpStatus.OK);
    }


    @DeleteMapping("/{scheduleId}")
    public ResponseEntity<List<ScheduleResponseDto>> deleteScheduleAPI(@PathVariable Long scheduleId) {

        List<ScheduleResponseDto> scheduleResponseDto = scheduleService.deleteSchedule(scheduleId);

        return new ResponseEntity<>(scheduleResponseDto, HttpStatus.OK);
    }

}
