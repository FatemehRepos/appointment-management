package com.sayan.appointment_management.component.exception;

import com.sayan.appointment_management.model.response.ErrorResponse;
import com.sayan.appointment_management.model.response.Response;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.context.support.MessageSourceAccessor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@ControllerAdvice
@RequiredArgsConstructor
public class ExceptionHandler {

    private final MessageSource messageSource;

    @org.springframework.web.bind.annotation.ExceptionHandler(RecordNotFoundException.class)
    public ResponseEntity<Response<Object>> handleException(
            RecordNotFoundException exception, HttpServletRequest request) {
        ErrorResponse error = ErrorResponse.error(
                HttpStatus.NOT_FOUND.value(),
                exception.getClass().getName(),
                messageSource.getMessage(
                        exception.getMessage(),
                        null,
                        LocaleContextHolder.getLocale()));
        return new ResponseEntity<>(Response.error(List.of(error)), HttpStatus.NOT_FOUND);
    }

    @org.springframework.web.bind.annotation.ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Response<Object>> handleException(
            MethodArgumentNotValidException exception, HttpServletRequest request) {
        List<ErrorResponse> errors = getMethodArgumentNotValidExceptionErrors(exception);
        return new ResponseEntity<>(Response.error(errors), HttpStatus.BAD_REQUEST);
    }

    private List<ErrorResponse> getMethodArgumentNotValidExceptionErrors(MethodArgumentNotValidException exception) {
        List<ErrorResponse> validationErrors = new ArrayList<>();
        exception.getBindingResult().getFieldErrors().forEach(error -> {
            ErrorResponse errorResponse = getErrorResponse(error);
            validationErrors.add(errorResponse);
        });
        return validationErrors;
    }

    private ErrorResponse getErrorResponse(FieldError error) {
        return ErrorResponse.error(
                Integer.parseInt(Objects.requireNonNull(error.getCode())),
                error.getClass().getName(),
                messageSource.getMessage(
                        Objects.requireNonNull(error.getDefaultMessage()),
                        null,
                        LocaleContextHolder.getLocale()));
    }

}
