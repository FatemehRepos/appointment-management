package com.sayan.appointment_management.model.entity;

import com.sayan.appointment_management.model.enums.AppointmentStatus;
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
    @Column(nullable = false, unique = true)
    private LocalTime appointmentTime;
    private LocalDate disableDate;
    private LocalDate endDate;
    @Enumerated(EnumType.STRING)
    private AppointmentStatus status;
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    private Patient patient;
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    private DoctorService doctorService;
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    private Clinic clinic;
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    private AppointmentType appointmentType;
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    private Appointment root;
    @OneToOne(fetch = FetchType.LAZY, optional = false)
    private Appointment parent;
    @ManyToOne(fetch = FetchType.LAZY)
    private Claim claim;

}
