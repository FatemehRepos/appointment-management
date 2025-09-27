package com.sayan.appointment_management.model.request;

import lombok.Builder;


@Builder
public record AppointmentCreationRequest(
        long patientId,
        long scheduleId,
        long appointmentTypeId) {
}
