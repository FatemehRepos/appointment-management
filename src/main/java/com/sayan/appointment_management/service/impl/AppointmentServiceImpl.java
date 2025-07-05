package com.sayan.appointment_management.service.impl;

import com.sayan.appointment_management.component.exception.RecordNotFoundException;
import com.sayan.appointment_management.model.entity.Appointment;
import com.sayan.appointment_management.model.entity.DoctorService;
import com.sayan.appointment_management.model.entity.Patient;
import com.sayan.appointment_management.model.enums.AppointmentStatus;
import com.sayan.appointment_management.model.request.AppointmentCreationRequest;
import com.sayan.appointment_management.model.request.AppointmentUpdateRequest;
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
        Appointment appointment = find(request.appointmentDate(), request.appointmentTime());
        if (appointment != null) throw new RecordNotFoundException("error.duplicate.appointment");
        return appointmentRepository.save(generateAppointment(request));
    }

    @Override
    public Page<AppointmentResponse> findAll(LocalDate date, LocalTime time, Pageable pageable) {
        return appointmentRepository.findAll(
                AppointmentSpecification.appointmentSpecification(date, time),
                pageable).map(this::mapToAppointmentResponse);
    }

    @Override
    public AppointmentResponse findOne(long id) {
        return mapToAppointmentResponse(find(id));
    }

    @Override
    @Transactional
    public Appointment update(AppointmentUpdateRequest request) {
        Appointment existing = find(request.id());
        Appointment duplicate = find(request.appointmentDate(), request.appointmentTime());
        if (duplicate != null) throw new RecordNotFoundException("error.duplicate.appointment");
        return appointmentRepository.save(updateAppointmentFields(existing, request));
    }

    @Override
    public void delete(long id) {
        Appointment appointment = find(id);
        appointment.setDisableDate(LocalDate.now());
        appointmentRepository.save(appointment);
    }

    private Appointment updateAppointmentFields(Appointment appointment, AppointmentUpdateRequest request) {
        DoctorService findedDoctorService = doctorService.find(request.doctorId(), request.serviceId());
        appointment.setAppointmentTime(request.appointmentTime());
        appointment.setAppointmentDate(request.appointmentDate());
        appointment.setDoctorService(findedDoctorService);
        return appointment;
    }

    private Appointment find(long id) {
        return appointmentRepository.findById(id)
                .orElseThrow(() -> new RecordNotFoundException("error.appointment.not.found"));
    }

    private Appointment find(LocalDate date, LocalTime time) {
        return appointmentRepository.findByAppointmentDateAndAppointmentTime(date, time)
                .orElse(null);
    }

    private AppointmentResponse mapToAppointmentResponse(Appointment appointment) {
        return AppointmentResponse.builder()
                .id(appointment.getId())
                .name(appointment.getPatient().getName())
                .lastname(appointment.getPatient().getLastname())
                .nationalCode(appointment.getPatient().getNationalCode())
                .appointmentDate(appointment.getAppointmentDate())
                .appointmentTime(appointment.getAppointmentTime())
                .clinicName(appointment.getClinic().getName())
                .clinicId(appointment.getClinic().getId())
                .serviceId(appointment.getDoctorService().getService().getId())
                .serviceName(appointment.getDoctorService().getService().getTitle())
                .doctorId(appointment.getDoctorService().getDoctor().getId())
                .doctorName(appointment.getDoctorService().getDoctor().getName())
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
