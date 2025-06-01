package com.maid.service.provider.exception;

import com.maid.service.provider.util.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Response> handleValidationExceptions(MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getFieldErrors().forEach(error -> 
            errors.put(error.getField(), error.getDefaultMessage()));
        
        return ResponseEntity.badRequest().body(
            new Response(HttpStatus.BAD_REQUEST.value(), "Validation failed", errors)
        );
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<Response> handleAllExceptions(Exception ex) {
        return ResponseEntity.internalServerError().body(
            new Response(HttpStatus.INTERNAL_SERVER_ERROR.value(), 
                        "An error occurred: " + ex.getMessage(), 
                        null)
        );
    }
}