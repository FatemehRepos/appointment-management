package com.sayan.appointment_management.model.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum AppointmentStatus {

    SCHEDULED(1), DONE(2), CANCELED(3), PENDING(4), IN_PROGRESS(5);

    private final long id;
}
