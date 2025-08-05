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
public class UnitTypePosition extends BaseEntity {

    @ManyToOne(optional = false)
    private UnitType unitType;
    @ManyToOne(optional = false)
    private Position position;
    private long creatorId;
    private long lastModifierId;

}
