package com.sayan.appointment_management.controller;

import com.sayan.appointment_management.model.request.AppointmentCreationRequest;
import com.sayan.appointment_management.model.response.AppointmentResponse;
import com.sayan.appointment_management.service.AppointmentService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.LocalTime;

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

    @GetMapping
    public ResponseEntity<Page<AppointmentResponse>> findAll(
            @RequestParam(required = false) LocalDate date,
            @RequestParam(required = false) LocalTime time,
            Pageable pageable) {
        return ResponseEntity.ok(appointmentService.findAll(date, time, pageable));
    }

}
