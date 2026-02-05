package com.devsuperior.gscommerce.controllers.handlers;

import com.devsuperior.gscommerce.service.exceptions.CustomError;
import com.devsuperior.gscommerce.service.exceptions.DataBaseException;
import com.devsuperior.gscommerce.service.exceptions.ResourceNotFoundException;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.Instant;

@RestControllerAdvice
public class ControllerExceptionHandler {

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<CustomError> resourceNotFound(ResourceNotFoundException exception, HttpServletRequest request){
        CustomError error = new CustomError(
                Instant.now(),
                HttpStatus.NOT_FOUND.value(),
                exception.getMessage(),
                request.getRequestURI()
        );
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
    }

    @ExceptionHandler(DataBaseException.class)
    public ResponseEntity<CustomError> dataBaseException(DataBaseException exception, HttpServletRequest request){
        CustomError error = new CustomError(
                Instant.now(),
                HttpStatus.BAD_REQUEST.value(),
                "Erro de integridade de dados",
                request.getRequestURI()
        );
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
    }


}
