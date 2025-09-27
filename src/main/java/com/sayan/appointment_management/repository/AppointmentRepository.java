package com.sayan.appointment_management.repository;

import com.sayan.appointment_management.model.entity.Appointment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AppointmentRepository extends
        JpaRepository<Appointment, Long>, JpaSpecificationExecutor<Appointment> {

    @Query("select t from Appointment t where t.doctorSchedule.id =: scheduleId")
    Optional<Appointment> findByScheduleId(long scheduleId);

}
