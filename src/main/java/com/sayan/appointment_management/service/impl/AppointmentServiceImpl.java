package com.sayan.appointment_management.service.impl;

import com.sayan.appointment_management.component.exception.RecordNotFoundException;
import com.sayan.appointment_management.model.entity.Appointment;
import com.sayan.appointment_management.model.entity.NaturalPerson;
import com.sayan.appointment_management.model.entity.Patient;
import com.sayan.appointment_management.model.request.AppointmentCreationRequest;
import com.sayan.appointment_management.repository.AppointmentRepository;
import com.sayan.appointment_management.service.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalTime;

@Service
@RequiredArgsConstructor
public class AppointmentServiceImpl implements AppointmentService {

    private final CompanyService companyService;
    private final PatientService patientService;
    private final DoctorServiceService doctorService;
    private final AppointmentRepository appointmentRepository;
    private final AppointmentTypeService appointmentTypeService;
    private final ReservationTypeService reservationTypeService;

    @Override
    @Transactional
    public Appointment create(AppointmentCreationRequest request) {
        checkForDuplicate(request.appointmentDate(), request.appointmentTime());
        return appointmentRepository.save(generateAppointment(request));
    }

    public void checkForDuplicate(LocalDate appointmentDate, LocalTime appointmentTime) {
        Appointment appointment = find(appointmentDate, appointmentTime);
        if (appointment != null) throw new RecordNotFoundException("error.duplicate.appointment");
    }

    private Appointment find(LocalDate date, LocalTime time) {
        return appointmentRepository.findByAppointmentDateAndAppointmentTime(date, time)
                .orElse(null);
    }

    private Patient generatePatient(AppointmentCreationRequest request) {
        return Patient.builder()
                .active(true)
                .person(NaturalPerson.builder()
                        .name(request.name())
                        .lastname(request.lastname())
                        .nationalCode(request.nationalCode())
                        .birthDate(request.birthdate())
                        .gender(request.gender())
                        .build())
                .build();
    }

    private Appointment generateAppointment(AppointmentCreationRequest request) {
        return Appointment.builder()
                .appointmentDate(request.appointmentDate())
                .appointmentTime(request.appointmentTime())
                .company(companyService.find(request.companyId()))
                .patient(patientService.create(generatePatient(request)))
                .appointmentType(appointmentTypeService.find(request.appointmentTypeId()))
                .doctorService(doctorService.find(request.doctorId(), request.doctorId()))
                .reservationType(reservationTypeService.find(request.reservationTypeId()))
                .build();
    }

}
