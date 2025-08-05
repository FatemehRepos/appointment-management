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
public class PersonUnitTypePosition extends BaseEntity {

    @ManyToOne(optional = false)
    private UnitTypePosition unitTypePosition;
    @ManyToOne(optional = false)
    private Person person;
    private long creatorId;
    private long lastModifierId;

}
