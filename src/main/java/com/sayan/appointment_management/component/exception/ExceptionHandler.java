package com.sayan.appointment_management.component.exception;

import com.sayan.appointment_management.model.response.ErrorResponse;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
    public ResponseEntity<ErrorResponse> handleException(
            RecordNotFoundException exception, HttpServletRequest request) {
        ErrorResponse errorResponse = ErrorResponse.builder()
                .timeStamp(LocalDateTime.now())
                .status(HttpStatus.NOT_FOUND.value())
                .path(request.getRequestURI())
                .errors(getErrors(exception))
                .build();
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorResponse);
    }


    @org.springframework.web.bind.annotation.ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorResponse> handleException(
            MethodArgumentNotValidException exception, HttpServletRequest request) {
        ErrorResponse errorResponse = ErrorResponse.builder()
                .timeStamp(LocalDateTime.now())
                .status(HttpStatus.BAD_REQUEST.value())
                .path(request.getRequestURI())
                .errors(getMethodArgumentNotValidExceptionErrors(exception))
                .build();
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse);
    }

    private List<String> getErrors(RuntimeException exception) {
        return List.of(messageSource.getMessage(
                exception.getMessage(),
                null,
                LocaleContextHolder.getLocale()));
    }

    private List<String> getMethodArgumentNotValidExceptionErrors(MethodArgumentNotValidException exception) {
        List<String> validationErrors = new ArrayList<>();
        exception.getBindingResult().getFieldErrors().forEach(error -> {
            validationErrors.add(messageSource.getMessage(
                    Objects.requireNonNull(error.getDefaultMessage()),
                    null,
                    LocaleContextHolder.getLocale()));
        });
        return validationErrors;
    }

}
