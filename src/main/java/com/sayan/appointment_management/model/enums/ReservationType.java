package com.sayan.appointment_management.model.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum ReservationType {

    IN_PERSON(0), ONLINE(1), BY_PHONE(2);

    private final int id;

}
