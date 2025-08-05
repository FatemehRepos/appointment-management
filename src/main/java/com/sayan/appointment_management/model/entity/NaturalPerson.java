package com.sayan.appointment_management.model.entity;

import com.sayan.appointment_management.model.enums.Gender;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import lombok.*;

import java.time.LocalDate;

@Entity
@Setter
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class NaturalPerson extends Person {

    @Column(nullable = false)
    private String name;
    @Column(nullable = false)
    private String lastname;
    @Column(nullable = false, unique = true)
    private String nationalCode;
    @Column(nullable = false)
    private LocalDate birthDate;
    @Column(nullable = false)
    private Gender gender;
    private long creatorId;
    private long lastModifierId;

}
