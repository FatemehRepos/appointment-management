package com.sayan.appointment_management.model.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import lombok.*;

import java.time.LocalDate;

@Entity
@Setter
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DoctorSchedule extends BaseEntity {

    private LocalDate startDate;
    private LocalDate endDate;
    private int dayOfWeek;
    private int durationMinutes;
    private long creatorId;
    private long lastModifierId;
    @ManyToOne(optional = false)
    private Doctor doctor;
    @ManyToOne(optional = false)
    private Service service;
    @ManyToOne(optional = false)
    private Company company;

}
