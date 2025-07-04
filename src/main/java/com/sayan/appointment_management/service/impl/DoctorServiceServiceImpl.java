package com.sayan.appointment_management.service.impl;

import com.sayan.appointment_management.component.exception.RecordNotFoundException;
import com.sayan.appointment_management.model.entity.DoctorService;
import com.sayan.appointment_management.repository.DoctorServiceRepository;
import com.sayan.appointment_management.service.DoctorServiceService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class DoctorServiceServiceImpl implements DoctorServiceService {

    private final DoctorServiceRepository doctorServiceRepository;

    @Override
    public DoctorService find(long doctorId, long serviceId) {
        return doctorServiceRepository.findDoctorServiceByDoctorAndService(doctorId,serviceId)
                .orElseThrow(()->new RecordNotFoundException("errors.doctor.service.not.found"));
    }

}
