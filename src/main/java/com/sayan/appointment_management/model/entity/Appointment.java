package com.sayan.appointment_management.model.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalTime;

@Entity
@Setter
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Appointment extends BaseEntity {

    @Column(nullable = false)
    private LocalDate appointmentDate;
    @Column(nullable = false)
    private LocalTime appointmentTime;
    private LocalDate disableDate;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    private Patient patient;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    private DoctorSchedule doctorSchedule;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    private AppointmentType appointmentType;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    private ReservationType reservationType;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    private AppointmentStatus appointmentStatus;

    @ManyToOne(fetch = FetchType.LAZY)
    private Appointment root;

    @ManyToOne(fetch = FetchType.LAZY)
    private Appointment parent;

}
