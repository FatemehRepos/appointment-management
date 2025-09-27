package com.sayan.appointment_management.model.response;

import lombok.Getter;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;

@Getter
public class Response<T> {

    private final T result;
    private final boolean hasError;
    private final String messageResult;
    private final LocalDateTime timeStamp;
    private final List<ErrorResponse> errors;

    private Response(T result, boolean hasError, String messageResult,
                     LocalDateTime timeStamp, List<ErrorResponse> errors) {
        this.result = result;
        this.hasError = hasError;
        this.messageResult = messageResult;
        this.errors = errors;
        this.timeStamp = timeStamp;
    }

    public static <T> Response<T> success(T result) {
        return new Response<>(
                result
                , false,
                "errors.operation.done.successfully",
                LocalDateTime.now(),
                Collections.emptyList());
    }

    public static <T> Response<T> error(List<ErrorResponse> errors) {
        return new Response<>(
                null,
                true,
                "",
                LocalDateTime.now(),
                errors == null ? Collections.emptyList() : errors
        );
    }

}
