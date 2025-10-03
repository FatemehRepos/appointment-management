package com.sayan.appointment_management.model.request;

import lombok.Builder;

import java.time.LocalDate;

@Builder
public record PatientUpdateRequest(
        long id,
        String name,
        String lastname,
        String fatherName,
        String nationalCode,
        LocalDate birthDate,
        long genderId,
        boolean isDead) {
}
