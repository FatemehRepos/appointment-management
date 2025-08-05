package com.sayan.appointment_management.controller;

import com.sayan.appointment_management.model.request.AppointmentCreationRequest;
import com.sayan.appointment_management.service.AppointmentService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/appointments")
public class AppointmentController {

    private final AppointmentService appointmentService;

    @PostMapping
    public ResponseEntity<?> create(@RequestBody @Valid AppointmentCreationRequest request) {
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(appointmentService.create(request));
    }

}
