package com.sayan.appointment_management.model.entity;

import jakarta.persistence.Entity;
import lombok.*;

@Entity
@Setter
@Getter
@NoArgsConstructor
public class GenderType extends BaseEntity {

    private String name;

}
