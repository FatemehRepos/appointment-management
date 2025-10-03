package com.sayan.appointment_management.controller;

import com.sayan.appointment_management.model.entity.Appointment;
import com.sayan.appointment_management.model.enums.AppointmentStatus;
import com.sayan.appointment_management.model.enums.ReservationType;
import com.sayan.appointment_management.model.request.AppointmentCreationRequest;
import com.sayan.appointment_management.model.request.AppointmentUpdateRequest;
import com.sayan.appointment_management.model.response.AppointmentDto;
import com.sayan.appointment_management.model.response.CreationResponse;
import com.sayan.appointment_management.model.response.Response;
import com.sayan.appointment_management.service.AppointmentService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/appointments")
public class AppointmentController {

    private final AppointmentService appointmentService;

    @PostMapping("/in-person")
    public ResponseEntity<Response<CreationResponse>> createByPerson(@RequestBody @Valid AppointmentCreationRequest request) {
        Appointment appointment = appointmentService.create(request, ReservationType.IN_PERSON.getId());
        CreationResponse response = CreationResponse.builder().id(appointment.getId()).build();
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(Response.success(response));
    }

    @PostMapping("/online")
    public ResponseEntity<Response<CreationResponse>> createOnline(@RequestBody @Valid AppointmentCreationRequest request) {
        Appointment appointment = appointmentService.create(request, ReservationType.ONLINE.getId());
        CreationResponse response = CreationResponse.builder().id(appointment.getId()).build();
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(Response.success(response));
    }

    @GetMapping("/today")
    public ResponseEntity<Response<List<AppointmentDto>>> getTodayAppointment() {
        List<AppointmentDto> appointments = appointmentService.getAppointmentsByDateAndStatus(
                LocalDate.now(), AppointmentStatus.SCHEDULED.getId());
        return ResponseEntity.ok(Response.success(appointments));
    }

    @GetMapping
    public ResponseEntity<Response<List<AppointmentDto>>> getAppointments(
            @RequestParam(required = false) LocalDate date,@RequestParam(required = false) Long statusId) {
        List<AppointmentDto> appointments = appointmentService.getAppointmentsByDateAndStatus(date, statusId);
        return ResponseEntity.ok(Response.success(appointments));
    }


    @PutMapping
    public ResponseEntity<Response<CreationResponse>> update(@RequestBody @Valid AppointmentUpdateRequest request) {
        Appointment updated = appointmentService.update(request);
        CreationResponse response = CreationResponse.builder().id(updated.getId()).build();
        return ResponseEntity.ok(Response.success(response));
    }

    @PatchMapping("/{appointmentId}")
    public ResponseEntity<?> cancel(@PathVariable long appointmentId) {
        appointmentService.cancel(appointmentId);
        return ResponseEntity.ok().build();
    }

}
