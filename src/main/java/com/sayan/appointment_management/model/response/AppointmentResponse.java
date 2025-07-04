package com.sayan.appointment_management.model.response;

import lombok.Builder;

import java.time.LocalDate;
import java.time.LocalTime;

@Builder
public record AppointmentResponse(
        String name,
        String lastname,
        String nationalCode,
        LocalDate appointmentDate,
        LocalTime appointmentTime,
        long clinicId,
        String appointmentType,
        String reservationType,
        String reservationStatus,
        long serviceId,
        long doctorId) {
}
