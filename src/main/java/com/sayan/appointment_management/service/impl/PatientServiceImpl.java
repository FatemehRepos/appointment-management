package com.sayan.appointment_management.service.impl;

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
    public Patient create(Patient patient) {
        Patient findedPatient = find(patient.getNationalCode());
        return findedPatient == null ? patientRepository.save(patient) : findedPatient;
    }

    @Override
    public Patient find(String nationalCode) {
        return patientRepository.findByNationalCode(nationalCode).orElse(null);
    }

}
