package com.sayan.appointment_management.service.impl;

import com.sayan.appointment_management.component.exception.RecordNotFoundException;
import com.sayan.appointment_management.model.entity.Appointment;
import com.sayan.appointment_management.model.entity.DoctorSchedule;
import com.sayan.appointment_management.model.request.AppointmentCreationRequest;
import com.sayan.appointment_management.repository.AppointmentRepository;
import com.sayan.appointment_management.service.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class AppointmentServiceImpl implements AppointmentService {

    private static final long SCHEDULED_APPOINTMENT_STATUS_ID = 1L;
    private final PatientService patientService;
    private final DoctorScheduleService doctorScheduleService;
    private final AppointmentRepository appointmentRepository;
    private final AppointmentTypeService appointmentTypeService;
    private final ReservationTypeService reservationTypeService;
    private final AppointmentStatusService appointmentStatusService;

    @Override
    @Transactional
    public Appointment create(AppointmentCreationRequest request,long reservationTypeId) throws RecordNotFoundException {
        checkForDuplicate(request.scheduleId());
        return appointmentRepository.save(generateAppointment(request,reservationTypeId));
    }

    public void checkForDuplicate(long scheduleId) {
        Appointment appointment = find(scheduleId);
        if (appointment != null) throw new RecordNotFoundException("error.duplicate.appointment");
    }

    private Appointment find(long scheduleId) {
        return appointmentRepository.findByScheduleId(scheduleId).orElse(null);
    }

    private Appointment generateAppointment(AppointmentCreationRequest request, long reservationTypeId) {
        DoctorSchedule doctorSchedule = doctorScheduleService.find(request.scheduleId());
        return Appointment.builder()
                .doctorSchedule(doctorSchedule)
                .appointmentDate(doctorSchedule.getDate())
                .appointmentTime(doctorSchedule.getStartTime())
                .patient(patientService.find(request.patientId()))
                .appointmentType(appointmentTypeService.find(request.appointmentTypeId()))
                .reservationType(reservationTypeService.find(reservationTypeId))
                .appointmentStatus(appointmentStatusService.find(SCHEDULED_APPOINTMENT_STATUS_ID))
                .build();
    }

}
