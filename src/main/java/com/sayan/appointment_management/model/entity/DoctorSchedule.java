package com.sayan.appointment_management.model.entity;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.time.LocalDate;
import java.time.LocalTime;

@Entity
@Setter
@Getter
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
public class DoctorSchedule extends BaseEntity {

    @Column(nullable = false)
    private LocalDate date;
    @Column(nullable = false)
    @Temporal(TemporalType.TIME)
    private LocalTime startTime;
    @Column(nullable = false)
    @Temporal(TemporalType.TIME)
    private LocalTime endTime;
    private int weekDay;
    private int durationMinutes;
    private boolean active;

    @ManyToOne(optional = false)
    private DoctorCompanyService doctorCompanyService;

}
