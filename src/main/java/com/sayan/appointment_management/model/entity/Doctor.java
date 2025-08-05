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
public class Doctor extends BaseEntity {

    @Column(nullable = false, unique = true)
    private long licenseNumber;
    private boolean active;
    private long creatorId;
    private long lastModifierId;

    @OneToOne
    private Person person;

}
