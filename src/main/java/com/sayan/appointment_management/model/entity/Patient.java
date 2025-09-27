package com.sayan.appointment_management.model.entity;

import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Setter
@Getter
@NoArgsConstructor
public class Patient extends NaturalPerson {

    private String fullName;
    private String PatientNationalCode;

}
