package com.mivmagul.stakelogic.accounting.application.exception;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.ArrayList;
import java.util.List;

@ControllerAdvice
public class CustomResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(
            MethodArgumentNotValidException ex,
            HttpHeaders headers,
            HttpStatus status,
            WebRequest request) {

        List<String> errors = new ArrayList<>();
        ex.getBindingResult().getFieldErrors().forEach(
                error -> errors.add(error.getField() + ": " + error.getDefaultMessage())
        );
        ex.getBindingResult().getGlobalErrors().forEach(
                error -> errors.add(error.getObjectName() + ": " + error.getDefaultMessage())
        );

        HttpError apiError = new HttpError(HttpStatus.BAD_REQUEST, ex.getMessage(), errors);
        return handleExceptionInternal(ex, apiError, headers, apiError.getStatus(), request);
    }

    @ExceptionHandler({EntityFieldValidationException.class})
    public ResponseEntity<Object> handleEmployeeFieldValidation(EntityFieldValidationException ex) {
        HttpError apiError = new HttpError(HttpStatus.BAD_REQUEST, ex.getMessage(), ex.getEntity());
        return new ResponseEntity<>(apiError, new HttpHeaders(), apiError.getStatus());
    }

    @ExceptionHandler({EntityNotFoundException.class})
    public ResponseEntity<Object> handleMethodArgumentTypeMismatch(EntityNotFoundException ex) {
        HttpError apiError = new HttpError(HttpStatus.NOT_FOUND, ex.getMessage(), ex.getEntity());
        return new ResponseEntity<>(apiError, new HttpHeaders(), apiError.getStatus());
    }
}