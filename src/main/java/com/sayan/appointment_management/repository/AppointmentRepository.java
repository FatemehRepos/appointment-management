package com.sayan.appointment_management.repository;

import com.sayan.appointment_management.model.entity.Appointment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface AppointmentRepository extends
        JpaRepository<Appointment, Integer>, JpaSpecificationExecutor<Appointment> {
}
