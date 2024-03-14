package com.example.supplierservice.errorService;

import com.example.supplierservice.entity.ApiError;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.NoSuchElementException;

@RestControllerAdvice
public class HandleNoSuchElementService {

    @ExceptionHandler
    public ResponseEntity<ApiError> noSuchElementExceptionHandler(NoSuchElementException ex) {
        return new ResponseEntity<>(
                new ApiError(ex.getMessage()),
                HttpStatus.NOT_FOUND
        );
    }
}
