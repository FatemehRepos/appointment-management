package com.sayan.appointment_management.service.impl;

import com.sayan.appointment_management.component.exception.RecordNotFoundException;
import com.sayan.appointment_management.model.entity.AppointmentStatus;
import com.sayan.appointment_management.repository.AppointmentStatusRepository;
import com.sayan.appointment_management.service.AppointmentStatusService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AppointmentStatusServiceImpl implements AppointmentStatusService {

    private final AppointmentStatusRepository appointmentStatusRepository;

    @Override
    public AppointmentStatus find(long id) {
        return appointmentStatusRepository.findById(id)
                .orElseThrow(() -> new RecordNotFoundException("errors.appointment.status.not.found"));
    }

}
