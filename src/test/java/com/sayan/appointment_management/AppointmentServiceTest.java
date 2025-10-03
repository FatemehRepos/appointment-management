package com.sayan.appointment_management;

import com.sayan.appointment_management.model.entity.Appointment;
import com.sayan.appointment_management.model.enums.ReservationType;
import com.sayan.appointment_management.model.request.AppointmentCreationRequest;
import com.sayan.appointment_management.service.AppointmentService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;
@SpringBootTest
public class AppointmentServiceTest {

    @Autowired
    private  AppointmentService appointmentService;

    @Test
    public void shouldCreateAppointment() {
        Appointment appointment = appointmentService.create(generateAppointmentRequest(), ReservationType.BY_PHONE.getId());
        assertThat(appointment).isNotNull();
    }

    private AppointmentCreationRequest generateAppointmentRequest() {
        return AppointmentCreationRequest.builder()
                .appointmentTypeId(1L)
                .patientId(14L)
                .scheduleId(5L)
                .build();
    }
}
