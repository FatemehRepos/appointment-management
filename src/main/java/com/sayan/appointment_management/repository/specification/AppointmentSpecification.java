package com.sayan.appointment_management.repository.specification;

import com.sayan.appointment_management.model.entity.Appointment;
import org.springframework.data.jpa.domain.Specification;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

public class AppointmentSpecification {

    public static Specification<Appointment> appointmentSpecification(LocalDate date, LocalTime time) {
        return getSpecifications(date, time).stream()
                .reduce(Specification::and)
                .orElse((root, query, builder) -> builder.conjunction());
    }

    public static List<Specification<Appointment>> getSpecifications(LocalDate date, LocalTime time) {
        List<Specification<Appointment>> specifications = new ArrayList<>();
        if (date != null) specifications.add(getByDate(date));
        if (time != null) specifications.add(getByTime(time));
        return specifications;
    }

    public static Specification<Appointment> getByDate(LocalDate date) {
        return (root, query, builder) ->
                builder.equal(root.get("appointmentDate"), date);
    }

    public static Specification<Appointment> getByTime(LocalTime time) {
        return (root, query, builder) ->
                builder.equal(root.get("appointmentTime"), time);
    }

}
