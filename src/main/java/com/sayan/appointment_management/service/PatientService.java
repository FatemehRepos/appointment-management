package com.sayan.appointment_management.service;

import com.sayan.appointment_management.model.entity.Patient;
import com.sayan.appointment_management.model.request.PatientCreationRequest;
import com.sayan.appointment_management.model.request.PatientUpdateRequest;
import com.sayan.appointment_management.model.response.PatientDto;

import java.util.List;

public interface PatientService {

    Patient create(PatientCreationRequest request);

    Patient find(long id);

    List<PatientDto> findAll(String fullName, String nationalCode);

    Patient update(PatientUpdateRequest request);

}
