package com.sayan.appointment_management.model.response;

import lombok.Builder;

import java.time.LocalDate;

@Builder
public record PatientDto(
        long id,
        String name,
        String lastname,
        String fatherName,
        LocalDate birthDate) {
}
