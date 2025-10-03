package com.sayan.appointment_management.repository;

import com.sayan.appointment_management.model.entity.Appointment;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface AppointmentRepository extends
        JpaRepository<Appointment, Long>, JpaSpecificationExecutor<Appointment> {

    @Override
    @EntityGraph(attributePaths = {"patient","appointmentStatus", "doctorSchedule",
            "doctorSchedule.doctorCompanyService", "doctorSchedule.doctorCompanyService.doctor",
            "doctorSchedule.doctorCompanyService.service"})
    List<Appointment> findAll(Specification<Appointment> spec, Sort sort);

    @Query("select t from Appointment t where t.doctorSchedule.id =:scheduleId")
    Optional<Appointment> findByScheduleId(long scheduleId);

}
