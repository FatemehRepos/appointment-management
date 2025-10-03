package com.sayan.appointment_management.service;

import com.sayan.appointment_management.model.entity.Appointment;
import com.sayan.appointment_management.model.request.AppointmentCreationRequest;
import com.sayan.appointment_management.model.request.AppointmentUpdateRequest;
import com.sayan.appointment_management.model.response.AppointmentDto;

import java.time.LocalDate;
import java.util.List;

public interface AppointmentService {

    Appointment create(AppointmentCreationRequest request, long reservationTypeId);

    List<AppointmentDto> getAppointmentsByDateAndStatus(LocalDate date, Long statusId);

    Appointment update(AppointmentUpdateRequest request);

    void cancel(long appointmentId);

}
