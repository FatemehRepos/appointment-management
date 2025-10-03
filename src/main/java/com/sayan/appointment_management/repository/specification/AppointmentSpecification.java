package com.sayan.appointment_management.repository.specification;

import com.sayan.appointment_management.model.entity.Appointment;
import org.springframework.data.jpa.domain.Specification;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class AppointmentSpecification {

    public static Specification<Appointment> filter(LocalDate date, Long statusId) {
        return getSpecifications(date, statusId).stream()
                .reduce(Specification::and)
                .orElse((root, query, builder) -> builder.conjunction());
    }

    public static List<Specification<Appointment>> getSpecifications(LocalDate date, Long statusId) {
        List<Specification<Appointment>> specifications = new ArrayList<>();
        if (date != null) specifications.add(getByDate(date));
        if (statusId != null) specifications.add(getByStatus(statusId));
        return specifications;
    }

    public static Specification<Appointment> getByDate(LocalDate date) {
        return (root, query, builder) ->
                builder.equal(root.get("appointmentDate"), date);
    }

    public static Specification<Appointment> getByStatus(Long statusId) {
        return (root, query, builder) ->
                builder.equal(root.get("appointmentStatus").get("id"), statusId);
    }

}
