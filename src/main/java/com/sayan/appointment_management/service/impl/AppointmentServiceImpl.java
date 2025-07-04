package com.sayan.appointment_management.service.impl;

import com.sayan.appointment_management.model.entity.Appointment;
import com.sayan.appointment_management.model.entity.Patient;
import com.sayan.appointment_management.model.enums.AppointmentStatus;
import com.sayan.appointment_management.model.request.AppointmentCreationRequest;
import com.sayan.appointment_management.model.response.AppointmentResponse;
import com.sayan.appointment_management.repository.AppointmentRepository;
import com.sayan.appointment_management.repository.specification.AppointmentSpecification;
import com.sayan.appointment_management.service.*;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalTime;

@Service
@RequiredArgsConstructor
public class AppointmentServiceImpl implements AppointmentService {

    private final ClinicService clinicService;
    private final PatientService patientService;
    private final DoctorServiceService doctorService;
    private final AppointmentRepository appointmentRepository;
    private final AppointmentTypeService appointmentTypeService;
    private final ReservationTypeService reservationTypeService;


    @Override
    @Transactional
    public Appointment create(AppointmentCreationRequest request) {
        return appointmentRepository.save(generateAppointment(request));
    }

    @Override
    @Transactional
    public Page<AppointmentResponse> findAll(LocalDate date, LocalTime time, Pageable pageable) {
        return appointmentRepository.findAll(
                AppointmentSpecification.appointmentSpecification(date, time),
                pageable).map(this::mapToAppointmentResponse);
    }

    private AppointmentResponse mapToAppointmentResponse(Appointment appointment) {
        return AppointmentResponse.builder()
                .appointmentDate(appointment.getAppointmentDate())
                .appointmentTime(appointment.getAppointmentTime())
                .clinicId(appointment.getClinic().getId())
                .serviceId(appointment.getDoctorService().getService().getId())
                .doctorId(appointment.getDoctorService().getDoctor().getId())
                .appointmentType(appointment.getAppointmentType().getTitle())
                .reservationStatus(appointment.getStatus().name())
                .reservationType(appointment.getReservationType().getTitle())
                .build();
    }

    private Patient generatePatient(AppointmentCreationRequest request) {
        return Patient.builder()
                .name(request.name())
                .lastname(request.lastname())
                .nationalCode(request.nationalCode())
                .build();
    }

    private Appointment generateAppointment(AppointmentCreationRequest request) {
        return Appointment.builder()
                .status(AppointmentStatus.CREATED)
                .appointmentDate(request.appointmentDate())
                .appointmentTime(request.appointmentTime())
                .clinic(clinicService.find(request.clinicId()))
                .patient(patientService.create(generatePatient(request)))
                .appointmentType(appointmentTypeService.find(request.appointmentTypeId()))
                .doctorService(doctorService.find(request.doctorId(), request.doctorId()))
                .reservationType(reservationTypeService.find(request.reservationTypeId()))
                .build();
    }

}
