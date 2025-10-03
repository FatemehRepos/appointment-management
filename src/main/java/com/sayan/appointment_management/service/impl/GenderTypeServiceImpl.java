package com.sayan.appointment_management.service.impl;

import com.sayan.appointment_management.component.exception.RecordNotFoundException;
import com.sayan.appointment_management.model.entity.GenderType;
import com.sayan.appointment_management.repository.GenderTypeRepository;
import com.sayan.appointment_management.service.GenderTypeService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class GenderTypeServiceImpl implements GenderTypeService {

    private final GenderTypeRepository genderTypeRepository;

    @Override
    public GenderType find(long id) {
        return genderTypeRepository.findById(id)
                .orElseThrow(()->new RecordNotFoundException("errors.gender.type.not.found"));
    }

}
