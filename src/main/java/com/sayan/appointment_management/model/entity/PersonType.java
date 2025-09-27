package com.sayan.appointment_management.model.entity;

import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Setter
@Getter
@NoArgsConstructor
public class PersonType extends BaseEntity {

    private String name;
    private String code;
    private LocalDateTime disableDate;

}
