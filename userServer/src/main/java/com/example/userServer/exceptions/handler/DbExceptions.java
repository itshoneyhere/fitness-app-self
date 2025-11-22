package com.example.userServer.exceptions.handler;


import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

//@RestControllerAdvice
public class DbExceptions {

    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity<Map<String,String>> handleDataIntegrityViolation(DataIntegrityViolationException ex) {
        String message = "Database error occurred";


        if (ex.getCause() != null && ex.getCause().getMessage() != null) {
            String causeMsg = ex.getCause().getMessage().toLowerCase();
            if (causeMsg.contains("duplicate key") && causeMsg.contains("users_contact_key")) {
                message = "Phone number exists. Please use a different number.";
            }
        }

        Map<String, String> response = new HashMap<>();
        response.put("error", message);
        return ResponseEntity.status(HttpStatus.CONFLICT).body(response);
    }
}
