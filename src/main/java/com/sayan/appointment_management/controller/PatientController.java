package com.sayan.appointment_management.controller;

import com.sayan.appointment_management.model.entity.Patient;
import com.sayan.appointment_management.model.request.PatientCreationRequest;
import com.sayan.appointment_management.model.request.PatientUpdateRequest;
import com.sayan.appointment_management.model.response.CreationResponse;
import com.sayan.appointment_management.model.response.PatientDto;
import com.sayan.appointment_management.model.response.Response;
import com.sayan.appointment_management.service.PatientService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/patients")
public class PatientController {

    private final PatientService patientService;

    @PostMapping
    public ResponseEntity<Response<CreationResponse>> create(@RequestBody @Valid PatientCreationRequest request) {
        Patient patient = patientService.create(request);
        CreationResponse response = CreationResponse.builder().id(patient.getId()).build();
        return ResponseEntity.ok(Response.success(response));
    }

    @GetMapping
    public ResponseEntity<Response<List<PatientDto>>> getPatients(
            @RequestParam(required = false) String fullName, @RequestParam(required = false) String nationalCode) {
        List<PatientDto> patients = patientService.findAll(fullName, nationalCode);
        return ResponseEntity.ok(Response.success(patients));
    }

    @PutMapping
    public ResponseEntity<Response<CreationResponse>> update(@RequestBody @Valid PatientUpdateRequest request) {
        Patient update = patientService.update(request);
        CreationResponse response = CreationResponse.builder().id(update.getId()).build();
        return ResponseEntity.ok(Response.success(response));
    }

}
