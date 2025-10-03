package com.sayan.appointment_management.model.response;

import lombok.Builder;

import java.time.LocalDate;
import java.time.LocalTime;

@Builder
public record AppointmentDto(
        long id,
        LocalDate appointmentDate,
        LocalTime appointmentTime,
        String appointmentType,
        String appointmentStatus,
        int durationMinutes,
        String patient,
        String doctor,
        String service) {
}
