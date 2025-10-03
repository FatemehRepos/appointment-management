package com.sayan.appointment_management.repository;

import com.sayan.appointment_management.model.entity.GenderType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GenderTypeRepository extends JpaRepository<GenderType, Long> {

}
