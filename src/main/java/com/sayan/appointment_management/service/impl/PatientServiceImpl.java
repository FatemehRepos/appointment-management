package com.sayan.appointment_management.service.impl;

import com.sayan.appointment_management.component.exception.DuplicatedObjectException;
import com.sayan.appointment_management.component.exception.RecordNotFoundException;
import com.sayan.appointment_management.model.Mapper;
import com.sayan.appointment_management.model.entity.Patient;
import com.sayan.appointment_management.model.enums.PersonType;
import com.sayan.appointment_management.model.request.PatientCreationRequest;
import com.sayan.appointment_management.model.request.PatientUpdateRequest;
import com.sayan.appointment_management.model.response.PatientDto;
import com.sayan.appointment_management.repository.PatientRepository;
import com.sayan.appointment_management.repository.specification.PatientSpecification;
import com.sayan.appointment_management.service.GenderTypeService;
import com.sayan.appointment_management.service.PatientService;
import com.sayan.appointment_management.service.PersonTypeService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PatientServiceImpl implements PatientService {

    private final PatientRepository patientRepository;
    private final PersonTypeService personTypeService;
    private final GenderTypeService genderTypeService;
    private final String FULL_NAME_PATTERN = "%s %s";

    @Override
    public Patient create(PatientCreationRequest request) {
        checkForDuplicate(request.nationalCode());
        return patientRepository.save(generatePatient(request));
    }

    private void checkForDuplicate(String nationalCode) {
        Patient patient = find(nationalCode);
        if (patient != null) throw new DuplicatedObjectException("error.duplicate.patient");
    }

    @Override
    public Patient find(long id) {
        return patientRepository.findById(id)
                .orElseThrow(() -> new RecordNotFoundException("errors.patient.not.found"));
    }

    private Patient find(String nationalCode) {
        return patientRepository.findByNationalCode(nationalCode).orElse(null);
    }

    @Override
    public List<PatientDto> findAll(String fullName, String nationalCode) {
        return patientRepository.findAll(PatientSpecification.filter(fullName, nationalCode))
                .stream()
                .map(Mapper::toPatientDto)
                .toList();
    }

    @Override
    public Patient update(PatientUpdateRequest request) {
        Patient patient = find(request.id());
        Patient newPatient = setNewField(patient, request);
        return patientRepository.save(newPatient);
    }

    private Patient setNewField(Patient patient, PatientUpdateRequest request) {
        String fullName = FULL_NAME_PATTERN.formatted(request.name(), request.lastname());
        patient.setName(fullName);
        patient.setFullName(fullName);
        patient.setUniqueField(request.nationalCode());
        patient.setPatientNationalCode(request.nationalCode());
        patient.setNationalCode(request.nationalCode());
        patient.setFirstname(request.name());
        patient.setLastname(request.lastname());
        patient.setFatherName(request.fatherName());
        patient.setBirthDate(request.birthDate());
        patient.setGender(genderTypeService.find(request.genderId()));
        patient.setDead(request.isDead());
        return patient;
    }

    private Patient generatePatient(PatientCreationRequest request) {
        String fullName = FULL_NAME_PATTERN.formatted(request.name(), request.lastname());
        return Patient.builder()
                .name(fullName)
                .uniqueField(request.nationalCode())
                .personType(personTypeService.find(PersonType.NATURAL.getId()))
                .firstname(request.name())
                .lastname(request.lastname())
                .fatherName(request.fatherName())
                .nationalCode(request.nationalCode())
                .birthDate(request.birthDate())
                .gender(genderTypeService.find(request.genderId()))
                .fullName(fullName)
                .PatientNationalCode(request.nationalCode())
                .creatorId(1L)
                .lastModifierId(1L)
                .build();
    }

}
