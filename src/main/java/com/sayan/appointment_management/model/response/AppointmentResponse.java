package com.sayan.appointment_management.model.response;

import lombok.Builder;

import java.time.LocalDate;
import java.time.LocalTime;

@Builder
public record AppointmentResponse(
        long id,
        String name,
        String lastname,
        String nationalCode,
        LocalDate appointmentDate,
        LocalTime appointmentTime,
        long clinicId,
        String clinicName,
        String appointmentType,
        String reservationType,
        String reservationStatus,
        long serviceId,
        String serviceName,
        long doctorId,
        String doctorName) {
}
