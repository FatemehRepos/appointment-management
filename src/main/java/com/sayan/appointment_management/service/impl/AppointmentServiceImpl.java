package com.sayan.appointment_management.service.impl;

import com.sayan.appointment_management.component.exception.DuplicatedObjectException;
import com.sayan.appointment_management.component.exception.RecordNotFoundException;
import com.sayan.appointment_management.model.Mapper;
import com.sayan.appointment_management.model.entity.Appointment;
import com.sayan.appointment_management.model.entity.AppointmentStatus;
import com.sayan.appointment_management.model.entity.DoctorSchedule;
import com.sayan.appointment_management.model.request.AppointmentCreationRequest;
import com.sayan.appointment_management.model.request.AppointmentUpdateRequest;
import com.sayan.appointment_management.model.response.AppointmentDto;
import com.sayan.appointment_management.repository.AppointmentRepository;
import com.sayan.appointment_management.repository.specification.AppointmentSpecification;
import com.sayan.appointment_management.service.*;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
public class AppointmentServiceImpl implements AppointmentService {

    private final PatientService patientService;
    private final DoctorScheduleService doctorScheduleService;
    private final AppointmentRepository appointmentRepository;
    private final AppointmentTypeService appointmentTypeService;
    private final ReservationTypeService reservationTypeService;
    private final AppointmentStatusService appointmentStatusService;

    @Override
    @Transactional
    public Appointment create(AppointmentCreationRequest request, long reservationTypeId) throws RecordNotFoundException {
        checkForDuplicate(request.scheduleId());
        Appointment saved = saveAppointment(generateAppointment(request, reservationTypeId));
        updateDoctorSchedule(request.scheduleId(), false);
        return saved;
    }

    @Override
    public List<AppointmentDto> getAppointmentsByDateAndStatus(LocalDate date, Long statusId) {
        List<Appointment> appointments = appointmentRepository.findAll(AppointmentSpecification.filter(date, statusId),
                Sort.by(Sort.Direction.ASC, "appointmentTime"));
        return appointments.stream()
                .map(Mapper::toAppointmentDto)
                .toList();
    }

    @Override
    @Transactional
    public Appointment update(AppointmentUpdateRequest request) {
        Appointment savedAppointment = findById(request.appointmentId());
        if (savedAppointment == null) throw new RecordNotFoundException("errors.appointment.not.found");
        checkForDuplicate(request.scheduleId());
        DoctorSchedule doctorSchedule = getDoctorSchedule(request.scheduleId());
        Appointment updated = saveAppointment(Mapper.toAppointment(savedAppointment, doctorSchedule));
        updateDoctorSchedule(request.scheduleId(), false);
        updateDoctorSchedule(savedAppointment.getDoctorSchedule().getId(), true);
        return updated;
    }

    @Override
    @Transactional
    public void cancel(long appointmentId) {
        Appointment savedAppointment = findById(appointmentId);
        if (savedAppointment == null) throw new RecordNotFoundException("errors.appointment.not.found");
        savedAppointment.setAppointmentStatus(getAppointmentStatus(
                com.sayan.appointment_management.model.enums.AppointmentStatus.CANCELED.getId()));
        saveAppointment(savedAppointment);
        updateDoctorSchedule(savedAppointment.getDoctorSchedule().getId(), true);
    }

    private AppointmentStatus getAppointmentStatus(long statusId) {
        return appointmentStatusService.find(statusId);
    }

    private Appointment saveAppointment(Appointment appointment) {
        return appointmentRepository.save(appointment);
    }

    private void updateDoctorSchedule(long scheduleId, boolean isActive) {
        DoctorSchedule doctorSchedule = doctorScheduleService.find(scheduleId);
        doctorSchedule.setActive(isActive);
        doctorScheduleService.create(doctorSchedule);
    }

    private DoctorSchedule getDoctorSchedule(long scheduleId) {
        return doctorScheduleService.find(scheduleId);
    }

    public void checkForDuplicate(long scheduleId) {
        Appointment appointment = findByScheduleID(scheduleId);
        if (appointment != null) throw new DuplicatedObjectException("error.duplicate.appointment");
    }

    private Appointment findByScheduleID(long scheduleId) {
        return appointmentRepository.findByScheduleId(scheduleId).orElse(null);
    }

    private Appointment findById(long id) {
        return appointmentRepository.findById(id).orElse(null);
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
                .appointmentStatus(getAppointmentStatus(
                        com.sayan.appointment_management.model.enums.AppointmentStatus.SCHEDULED.getId()))
                .creatorId(1L)
                .lastModifierId(1L)
                .build();
    }

}
