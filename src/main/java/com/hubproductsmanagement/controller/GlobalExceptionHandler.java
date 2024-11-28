package com.hubproductsmanagement.controller;

import com.hubproductsmanagement.exception.ProblemProcessingDataException;
import org.flywaydb.core.api.ErrorCode;
import org.flywaydb.core.api.ErrorDetails;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;


@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ProblemProcessingDataException.class)
    public ResponseEntity<ErrorDetails> handleUserNotFoundException(ProblemProcessingDataException ex, WebRequest request) {
        return new ResponseEntity<>(new ErrorDetails(ErrorCode.ERROR, ex.getMessage()), HttpStatus.SERVICE_UNAVAILABLE);
    }

    @ExceptionHandler(BadCredentialsException.class)
    public ResponseEntity<ErrorDetails> handleUnauthorized(BadCredentialsException ex, WebRequest request) {
        return new ResponseEntity<>(new ErrorDetails(ErrorCode.CONFIGURATION, ex.getMessage()), HttpStatus.UNAUTHORIZED);
    }


    @ExceptionHandler(AccessDeniedException.class)
    public ResponseEntity<ErrorDetails> handleAccessDenied(AccessDeniedException ex, WebRequest request) {
        return new ResponseEntity<>(new ErrorDetails(ErrorCode.CONFIGURATION, ex.getMessage()), HttpStatus.UNAUTHORIZED);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorDetails> handleGlobalException(Exception ex, WebRequest request) {
        return new ResponseEntity<>(
                new ErrorDetails(ErrorCode.ERROR, ex.getMessage()),
                HttpStatus.INTERNAL_SERVER_ERROR);
    }
}