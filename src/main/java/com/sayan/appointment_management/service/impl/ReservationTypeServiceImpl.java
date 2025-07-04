package com.sayan.appointment_management.service.impl;

import com.sayan.appointment_management.component.exception.RecordNotFoundException;
import com.sayan.appointment_management.model.entity.ReservationType;
import com.sayan.appointment_management.repository.ReservationTypeRepository;
import com.sayan.appointment_management.service.ReservationTypeService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ReservationTypeServiceImpl implements ReservationTypeService {

    private final ReservationTypeRepository reservationTypeRepository;

    @Override
    public ReservationType find(long reservationTypeId) {
        return reservationTypeRepository.findById(reservationTypeId)
                .orElseThrow(()->new RecordNotFoundException("errors.reservation.type.not.found"));
    }

}
