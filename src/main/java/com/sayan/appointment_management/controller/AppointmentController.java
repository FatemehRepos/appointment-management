package com.sayan.appointment_management.controller;

import com.sayan.appointment_management.model.entity.Appointment;
import com.sayan.appointment_management.model.enums.ReservationType;
import com.sayan.appointment_management.model.request.AppointmentCreationRequest;
import com.sayan.appointment_management.model.response.Response;
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

    @PostMapping("/in-person")
    public ResponseEntity<Response<Appointment>> createByPerson(@RequestBody @Valid AppointmentCreationRequest request) {
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(Response.success(appointmentService.create(request, ReservationType.IN_PERSON.getId())));
    }

    @PostMapping("/online")
    public ResponseEntity<Response<Appointment>> createOnline(@RequestBody @Valid AppointmentCreationRequest request) {
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(Response.success(appointmentService.create(request,ReservationType.ONLINE.getId())));
    }

}
