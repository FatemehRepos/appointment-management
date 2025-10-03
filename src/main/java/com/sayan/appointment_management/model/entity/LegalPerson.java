package com.sayan.appointment_management.model.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@Entity
@Setter
@Getter
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
public class LegalPerson extends Person {

    @Column(nullable = false)
    private String economicCode;
    @Column(unique = true)
    private String nationalCode;
    @Column(unique = true)
    private String registrationCode;

}
