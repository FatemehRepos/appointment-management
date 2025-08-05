package com.sayan.appointment_management.model.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Setter
@Getter
@NoArgsConstructor
public class LegalPerson extends Person {

    @Column(nullable = false, unique = true)
    private String nationalCode;
    @Column(unique = true)
    private String economyCode;
    @Column(nullable = false)
    private String name;
    private long creatorId;
    private long lastModifierId;

}
