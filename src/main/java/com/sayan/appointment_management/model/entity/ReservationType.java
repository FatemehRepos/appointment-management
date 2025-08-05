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
public class ReservationType extends BaseEntity {

    @Column(nullable = false, unique = true)
    private String title;
    private boolean disabled;
    private long creatorId;
    private long lastModifierId;

}
