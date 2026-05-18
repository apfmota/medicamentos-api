package com.petcc_enfermagem.medicamentos_api.config;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {
    
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, Object>> handleValidationExceptions(
            MethodArgumentNotValidException ex) {
        
        StringBuilder errorMessage = new StringBuilder("Validação falhou: ");
        ex.getBindingResult().getFieldErrors().forEach(error ->
            errorMessage.append(error.getField()).append(" - ").append(error.getDefaultMessage()).append("; ")
        );
        
        Map<String, Object> response = new HashMap<>();
        response.put("message", errorMessage.toString());
        
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }
}
