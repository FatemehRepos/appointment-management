package com.sayan.appointment_management.service.impl;

import com.sayan.appointment_management.component.exception.RecordNotFoundException;
import com.sayan.appointment_management.model.entity.Patient;
import com.sayan.appointment_management.repository.PatientRepository;
import com.sayan.appointment_management.service.PatientService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PatientServiceImpl implements PatientService {

    private final PatientRepository patientRepository;

    @Override
    public Patient find(long id) {
        return patientRepository.findById(id)
                .orElseThrow(()->new RecordNotFoundException("errors.patient.not.found"));
    }

}
