package com.recrutement.applications.exception;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(DuplicateApplicationException.class)
    public ResponseEntity<Map<String, String>> handleDuplicateApplication(
            DuplicateApplicationException exception
            ){
        Map<String, String> response = Map.of(
                "code", "Duplicate_application",
                "message", exception.getMessage()
        );
        return ResponseEntity.status(HttpStatus.CONFLICT)
                .body(response);
    }

    @ExceptionHandler(ApplicationNotFoundException.class)
    public ResponseEntity<Map<String , String>> handleApplicationNotFound(
            ApplicationNotFoundException exception

    ){
        Map<String,String> response = Map.of(
                "code","APPLICATION_NOT_FOUND",
                "message",exception.getMessage()
        );
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
    }

    @ExceptionHandler(InvalidApplicationStatusTransitionException.class)
    public ResponseEntity<Map<String, String>> handleInvalidApplicationStatusTransition(
            InvalidApplicationStatusTransitionException exception
    ){
        Map<String,String> response = Map.of(
                "code","INVALID_STATUS_TRANSITION",
                "message",exception.getMessage()
        );
        return ResponseEntity.status(HttpStatus.CONFLICT).body(response);
    }

}
