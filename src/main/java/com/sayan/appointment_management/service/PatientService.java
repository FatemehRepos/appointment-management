package com.sayan.appointment_management.service;

import com.sayan.appointment_management.model.entity.Patient;

public interface PatientService {

    Patient create(Patient patient);

    Patient find(String nationalCode);

}
