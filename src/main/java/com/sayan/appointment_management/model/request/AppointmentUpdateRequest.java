package com.sayan.appointment_management.model.request;

import lombok.Builder;

@Builder
public record AppointmentUpdateRequest(
        long scheduleId,
        long appointmentId) {
}
