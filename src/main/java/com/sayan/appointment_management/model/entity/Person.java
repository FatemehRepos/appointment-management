package com.sayan.appointment_management.model.entity;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.time.LocalDateTime;

@Entity
@Setter
@Getter
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@Inheritance(strategy = InheritanceType.JOINED)
public class Person extends BaseEntity {

    @Column(nullable = false)
    private String name;
    @Column(nullable = false, unique = true)
    private String uniqueField;
    private LocalDateTime disableDate;

    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private PersonType personType;

    @ManyToOne(fetch = FetchType.LAZY)
    private Person root;

    @ManyToOne(fetch = FetchType.LAZY)
    private Person parent;

}
