package com.sayan.appointment_management.service;

import com.sayan.appointment_management.model.entity.Appointment;
import com.sayan.appointment_management.model.request.AppointmentCreationRequest;

public interface AppointmentService {

    Appointment create(AppointmentCreationRequest request,long reservationTypeId);

}
