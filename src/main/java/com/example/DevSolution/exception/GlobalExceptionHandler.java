package com.example.DevSolution.exception;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@RestControllerAdvice
public class GlobalExceptionHandler {

    /**
     * Exception handler for IllegalArgumentException.
     * Handles validation exceptions and returns an ErrorResponse with the corresponding error details.
     *
     * @param ex The IllegalArgumentException instance.
     * @return The ResponseEntity containing the ErrorResponse and HttpStatus.BAD_REQUEST.
     */

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<ErrorResponse> handleValidationException(IllegalArgumentException ex) {

        ErrorResponse errorResponse = new ErrorResponse();
        errorResponse.setTimestamp(LocalDateTime.now().format(DateTimeFormatter.ISO_DATE_TIME));
        errorResponse.setStatus(HttpStatus.BAD_REQUEST.value());
        errorResponse.setError(HttpStatus.BAD_REQUEST.getReasonPhrase());
        errorResponse.setMessage(ex.getMessage());

        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
    }

    /**
     * Exception handler for ConstraintViolationException.
     * Handles constraint violation exceptions and returns an ErrorResponse with the corresponding error details.
     *
     * @param ex The ConstraintViolationException instance.
     * @return The ResponseEntity containing the ErrorResponse and HttpStatus.BAD_REQUEST.
     */

    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<ErrorResponse> handleConstraintViolationException(ConstraintViolationException ex) {

        ErrorResponse errorResponse = new ErrorResponse();
        errorResponse.setTimestamp(LocalDateTime.now().format(DateTimeFormatter.ISO_DATE_TIME));
        errorResponse.setStatus(HttpStatus.BAD_REQUEST.value());
        errorResponse.setError(HttpStatus.BAD_REQUEST.getReasonPhrase());

        StringBuilder errorMessage = new StringBuilder();
        for (ConstraintViolation<?> violation : ex.getConstraintViolations()) {
            errorMessage.append(violation.getMessage()).append("; ");
        }
        errorResponse.setMessage(errorMessage.toString().trim());

        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
    }

}