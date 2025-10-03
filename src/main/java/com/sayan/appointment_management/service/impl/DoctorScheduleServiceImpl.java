package com.sayan.appointment_management.service.impl;

import com.sayan.appointment_management.component.exception.RecordNotFoundException;
import com.sayan.appointment_management.model.entity.DoctorSchedule;
import com.sayan.appointment_management.repository.DoctorScheduleRepository;
import com.sayan.appointment_management.service.DoctorScheduleService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class DoctorScheduleServiceImpl implements DoctorScheduleService {

    private final DoctorScheduleRepository doctorScheduleRepository;

    @Override
    public DoctorSchedule create(DoctorSchedule doctorSchedule) {
        return doctorScheduleRepository.save(doctorSchedule);
    }

    @Override
    public DoctorSchedule find(long id) {
        return doctorScheduleRepository.findById(id)
                .orElseThrow(() -> new RecordNotFoundException("error.doctor.schedule.not.found"));
    }

}
