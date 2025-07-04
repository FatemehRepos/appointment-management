package com.sayan.appointment_management.service;

import com.sayan.appointment_management.model.entity.DoctorService;

public interface DoctorServiceService {

    DoctorService find(long doctorId, long serviceId);

}
