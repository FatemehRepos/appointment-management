package com.sayan.appointment_management.model.request;

import jakarta.validation.constraints.FutureOrPresent;
import lombok.Builder;

import java.time.LocalDate;
import java.time.LocalTime;

@Builder
public record AppointmentUpdateRequest(
        long id,
        @FutureOrPresent(message = "error.appointment.date.must.be.present.or.future")
        LocalDate appointmentDate,
        @FutureOrPresent(message = "error.appointment.time.must.be.present.or.future")
        LocalTime appointmentTime,
        long serviceId,
        long doctorId) {
}
