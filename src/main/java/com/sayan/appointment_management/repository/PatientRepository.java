package com.sayan.appointment_management.repository;

import com.sayan.appointment_management.model.entity.Patient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PatientRepository extends JpaRepository<Patient, Long> {

    @Query(value = "select t from Patient t where t.person.nationalCode =:nationalCode")
    Optional<Patient> findByNationalCode(String nationalCode);

}
