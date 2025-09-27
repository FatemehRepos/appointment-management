package com.sayan.appointment_management.model.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Setter
@Getter
@NoArgsConstructor
public class DoctorSpeciality extends BaseEntity {

    private LocalDateTime disableDate;

    @ManyToOne(optional = false)
    private Doctor doctor;
    @ManyToOne(optional = false)
    private Speciality speciality;

}
