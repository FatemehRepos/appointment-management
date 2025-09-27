package com.sayan.appointment_management.repository;

import com.sayan.appointment_management.model.entity.AppointmentStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AppointmentStatusRepository extends JpaRepository<AppointmentStatus, Long> {
}
