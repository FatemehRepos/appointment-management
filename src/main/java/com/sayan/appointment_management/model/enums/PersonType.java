package com.sayan.appointment_management.model.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum PersonType {

    NATURAL(1), LEGAL(2);

    private final int id;

}
