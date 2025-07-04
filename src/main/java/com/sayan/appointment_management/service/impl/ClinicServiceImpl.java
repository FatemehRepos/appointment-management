package com.sayan.appointment_management.service.impl;

import com.sayan.appointment_management.component.exception.RecordNotFoundException;
import com.sayan.appointment_management.model.entity.Clinic;
import com.sayan.appointment_management.repository.ClinicRepository;
import com.sayan.appointment_management.service.ClinicService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ClinicServiceImpl implements ClinicService {

    private final ClinicRepository clinicRepository;

    @Override
    public Clinic find(long clinicId) {
        return clinicRepository.findById(clinicId)
                .orElseThrow(() -> new RecordNotFoundException("errors.clinic.not.found"));
    }

}
