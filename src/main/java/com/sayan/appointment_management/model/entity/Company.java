package com.sayan.appointment_management.model.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToOne;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Setter
@Getter
@NoArgsConstructor
public class Company extends BaseEntity {

    @Column(nullable = false)
    private String companyName;
    @Column(unique = true, nullable = false)
    private String registrationNumber;
    private long creatorId;
    private long lastModifierId;

    @OneToOne
    private Person person;

}
