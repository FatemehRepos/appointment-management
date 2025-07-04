package com.sayan.appointment_management.repository;

import com.sayan.appointment_management.model.entity.DoctorService;
import com.sayan.appointment_management.model.entity.Patient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface DoctorServiceRepository extends JpaRepository<DoctorService, Long> {

    @Query("select t from DoctorService t where t.doctor.id =:doctorId and t.service.id =:serviceId")
    Optional<DoctorService> findDoctorServiceByDoctorAndService(long doctorId, long serviceId);

}
