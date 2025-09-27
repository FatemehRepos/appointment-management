package com.sayan.appointment_management.model.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Setter
@Getter
@NoArgsConstructor
public class Speciality extends BaseEntity {

    @Column(nullable = false, unique = true)
    private String name;

}
