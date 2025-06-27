package com.sayan.appointment_management.model.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Setter
@Getter
@NoArgsConstructor
public class DoctorService extends BaseEntity {

    @ManyToOne(optional = false)
    private Doctor doctor;
    @ManyToOne(optional = false)
    private Service service;

}
