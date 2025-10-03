package com.sayan.appointment_management.service;

import com.sayan.appointment_management.model.entity.DoctorSchedule;

public interface DoctorScheduleService {

    DoctorSchedule create(DoctorSchedule doctorSchedule);

    DoctorSchedule find(long id);

}
