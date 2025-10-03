package com.sayan.appointment_management.model;

import com.sayan.appointment_management.model.entity.Appointment;
import com.sayan.appointment_management.model.entity.DoctorSchedule;
import com.sayan.appointment_management.model.entity.Patient;
import com.sayan.appointment_management.model.response.AppointmentDto;
import com.sayan.appointment_management.model.response.PatientDto;

public class Mapper {

    public static Appointment toAppointment(Appointment appointment, DoctorSchedule doctorSchedule) {
        return Appointment.builder()
                .doctorSchedule(doctorSchedule)
                .appointmentDate(doctorSchedule.getDate())
                .appointmentTime(doctorSchedule.getStartTime())
                .patient(appointment.getPatient())
                .appointmentType(appointment.getAppointmentType())
                .reservationType(appointment.getReservationType())
                .appointmentStatus(appointment.getAppointmentStatus())
                .root(appointment.getRoot() != null ? appointment.getRoot() : appointment)
                .parent(appointment)
                .build();
    }

    public static AppointmentDto toAppointmentDto(Appointment appointment) {
        return AppointmentDto.builder()
                .id(appointment.getId())
                .appointmentDate(appointment.getAppointmentDate())
                .appointmentTime(appointment.getAppointmentTime())
                .appointmentStatus(appointment.getAppointmentStatus().getName())
                .appointmentType(appointment.getAppointmentType().getName())
                .durationMinutes(appointment.getDoctorSchedule().getDurationMinutes())
                .patient(appointment.getPatient().getName())
                .doctor(appointment.getDoctorSchedule().getDoctorCompanyService().getDoctor().getName())
                .service(appointment.getDoctorSchedule().getDoctorCompanyService().getService().getName())
                .build();
    }

    public static PatientDto toPatientDto(Patient patient) {
        return PatientDto.builder()
                .id(patient.getId())
                .name(patient.getName())
                .lastname(patient.getLastname())
                .birthDate(patient.getBirthDate())
                .fatherName(patient.getFatherName())
                .build();
    }

}
