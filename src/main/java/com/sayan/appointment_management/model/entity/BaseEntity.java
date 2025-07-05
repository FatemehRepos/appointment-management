package com.sayan.appointment_management.model.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;


@Setter
@Getter
@NoArgsConstructor
@MappedSuperclass
public class BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private long id;
    @CreationTimestamp
    private LocalDateTime createAt;
    @UpdateTimestamp
    private LocalDateTime updateAt;
    private long creatorId;
    private long lastModifierId;

}
