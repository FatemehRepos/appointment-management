package com.sayan.appointment_management.repository.specification;

import com.sayan.appointment_management.model.entity.Patient;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;

public class PatientSpecification {

    public static Specification<Patient> filter(String fullName, String nationalCode) {
        return getSpecifications(fullName, nationalCode).stream()
                .reduce(Specification::and)
                .orElse((root, query, builder) -> builder.conjunction());
    }

    public static List<Specification<Patient>> getSpecifications(String fullName, String nationalCode) {
        List<Specification<Patient>> specifications = new ArrayList<>();
        if (StringUtils.hasText(fullName)) specifications.add(getByFullName(fullName));
        if (StringUtils.hasText(nationalCode)) specifications.add(getByNationalCode(nationalCode));
        return specifications;
    }

    public static Specification<Patient> getByFullName(String fullName) {
        return (root, query, builder) ->
                builder.equal(root.get("fullName"), fullName);
    }

    public static Specification<Patient> getByNationalCode(String nationalCode) {
        return (root, query, builder) ->
                builder.equal(root.get("PatientNationalCode"), nationalCode);
    }

}
