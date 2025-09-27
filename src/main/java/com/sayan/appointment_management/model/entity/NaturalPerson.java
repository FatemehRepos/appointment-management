package com.sayan.appointment_management.model.entity;

import jakarta.persistence.*;
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
    private String firstname;
    @Column(nullable = false)
    private String lastname;
    private String fatherName;
    @Column(nullable = false, unique = true)
    private String nationalCode;
    @Column(nullable = false)
    private LocalDate birthDate;
    private boolean isDead;

    @ManyToOne(fetch = FetchType.LAZY,optional = false)
    private GenderType gender;

}
