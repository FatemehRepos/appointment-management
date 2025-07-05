package com.sayan.appointment_management.repository;

import com.sayan.appointment_management.model.entity.Appointment;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Optional;

@Repository
public interface AppointmentRepository extends
        JpaRepository<Appointment, Integer>, JpaSpecificationExecutor<Appointment> {

    @EntityGraph(attributePaths = {
            "patient",
            "doctorService",
            "doctorService.doctor",
            "doctorService.service",
            "clinic",
            "appointmentType",
            "reservationType"
    })
    Optional<Appointment> findById(long id);

    Optional<Appointment> findByAppointmentDateAndAppointmentTime(LocalDate appointmentDate, LocalTime appointmentTime);

}
