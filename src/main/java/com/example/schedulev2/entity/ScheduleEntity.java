package com.example.schedulev2.entity;

import jakarta.persistence.*;
import lombok.Getter;

@Getter
@Entity
@Table(name =  "schedules")
public class ScheduleEntity extends TimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "scheduleId")
    private Long scheduleId;

    private String title;

    private String schedule;


    public ScheduleEntity() {}

    public ScheduleEntity(String title, String schedule) {

        this.title = title;
        this.schedule = schedule;
    }

    public void setSchedule(String title, String schedule) {
        this.title = title;
        this.schedule = schedule;
    }
}
