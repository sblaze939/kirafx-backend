package com.kirafx.kirabackend.exception;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.kirafx.kirabackend.dto.ApiResponseGenerator;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ResourceAlreadyExistsException.class)
    public ResponseEntity<ApiResponseGenerator<Object>> handleResourceAlreadyExists(ResourceAlreadyExistsException ex) {
        ApiResponseGenerator<Object> response = ApiResponseGenerator.error(409, ex.getMessage());
        return ResponseEntity.status(409).body(response);
    }

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ApiResponseGenerator<Object>> handleResourceNotFound(ResourceNotFoundException ex) {
        ApiResponseGenerator<Object> response = ApiResponseGenerator.error(404, ex.getMessage());
        return ResponseEntity.status(404).body(response);
    }

    @ExceptionHandler(InvalidCredentialsException.class)
    public ResponseEntity<ApiResponseGenerator<Object>> handleInvalidCredentials(InvalidCredentialsException ex) {
        ApiResponseGenerator<Object> response = ApiResponseGenerator.error(401, ex.getMessage());
        return ResponseEntity.status(401).body(response);
    }

    @ExceptionHandler(UnauthorizedAccessException.class)
    public ResponseEntity<ApiResponseGenerator<Object>> handleUnauthorizedAccess(UnauthorizedAccessException ex) {
        ApiResponseGenerator<Object> response = ApiResponseGenerator.error(403, ex.getMessage());
        return ResponseEntity.status(403).body(response);
    }

    @ExceptionHandler(BadRequestException.class)
    public ResponseEntity<ApiResponseGenerator<Object>> handleBadRequest(BadRequestException ex) {
        ApiResponseGenerator<Object> response = ApiResponseGenerator.error(400, ex.getMessage());
        return ResponseEntity.status(400).body(response);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ApiResponseGenerator<Object>> handleGenericException(Exception ex) {
        ApiResponseGenerator<Object> response = ApiResponseGenerator.error(500, "Internal server error");
        return ResponseEntity.status(500).body(response);
    }
}
