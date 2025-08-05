package com.sayan.appointment_management.model.request;

import com.sayan.appointment_management.model.enums.Gender;
import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotBlank;
import lombok.Builder;

import java.time.LocalDate;
import java.time.LocalTime;

@Builder
public record AppointmentCreationRequest(
        @NotBlank(message = "error.name.is.required")
        String name,
        @NotBlank(message = "error.lastname.is.required")
        String lastname,
        @NotBlank(message = "error.nationalCode.is.required")
        String nationalCode,
        @NotBlank(message = "error.birthdate.is.required")
        LocalDate birthdate,
        Gender gender,
        @FutureOrPresent(message = "error.appointment.date.must.be.present.or.future")
        LocalDate appointmentDate,
        @FutureOrPresent(message = "error.appointment.time.must.be.present.or.future")
        LocalTime appointmentTime,
        long companyId,
        long appointmentTypeId,
        long reservationTypeId,
        long serviceId,
        long doctorId) {
}
