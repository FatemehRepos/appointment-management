package com.sayan.appointment_management.service;

import com.sayan.appointment_management.model.entity.Appointment;
import com.sayan.appointment_management.model.request.AppointmentCreationRequest;
import com.sayan.appointment_management.model.response.AppointmentResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.time.LocalDate;
import java.time.LocalTime;

public interface AppointmentService {

    Appointment create(AppointmentCreationRequest request);

    Page<AppointmentResponse> findAll(LocalDate date, LocalTime time, Pageable pageable);

}
