package com.sayan.appointment_management.model.response;

import lombok.Builder;

import java.time.LocalDateTime;

@Builder
public record ErrorResponse(
        LocalDateTime timeStamp,
        int status,
        String error,
        String path) {
}
