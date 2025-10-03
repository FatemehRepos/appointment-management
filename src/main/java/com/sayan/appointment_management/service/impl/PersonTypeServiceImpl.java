package com.sayan.appointment_management.service.impl;

import com.sayan.appointment_management.component.exception.RecordNotFoundException;
import com.sayan.appointment_management.model.entity.PersonType;
import com.sayan.appointment_management.repository.PersonTypeRepository;
import com.sayan.appointment_management.service.PersonTypeService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PersonTypeServiceImpl implements PersonTypeService {

    private final PersonTypeRepository personTypeRepository;

    @Override
    public PersonType find(long id) {
        return personTypeRepository.findById(id)
                .orElseThrow(()->new RecordNotFoundException("errors.person.type.not.found"));
    }

}
