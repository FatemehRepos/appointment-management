package com.sayan.appointment_management.service.impl;

import com.sayan.appointment_management.component.exception.RecordNotFoundException;
import com.sayan.appointment_management.model.entity.AppointmentType;
import com.sayan.appointment_management.repository.AppointmentTypeRepository;
import com.sayan.appointment_management.service.AppointmentTypeService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AppointmentTypeServiceImpl implements AppointmentTypeService {

    private final AppointmentTypeRepository appointmentTypeRepository;

    @Override
    public AppointmentType find(long typeId) {
        return appointmentTypeRepository.findById(typeId)
                .orElseThrow(()->new RecordNotFoundException("errors.appointment.type.not.found"));
    }

}
