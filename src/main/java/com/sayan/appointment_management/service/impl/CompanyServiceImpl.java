package com.sayan.appointment_management.service.impl;

import com.sayan.appointment_management.component.exception.RecordNotFoundException;
import com.sayan.appointment_management.model.entity.Company;
import com.sayan.appointment_management.repository.CompanyRepository;
import com.sayan.appointment_management.service.CompanyService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CompanyServiceImpl implements CompanyService {

    private final CompanyRepository clinicRepository;

    @Override
    public Company find(long companyId) {
        return clinicRepository.findById(companyId)
                .orElseThrow(() -> new RecordNotFoundException("errors.clinic.not.found"));
    }

}
