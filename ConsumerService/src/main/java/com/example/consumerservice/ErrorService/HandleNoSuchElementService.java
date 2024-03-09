package com.example.consumerservice.ErrorService;

import com.example.consumerservice.domain.ApiError;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.client.HttpClientErrorException;

@RestControllerAdvice
public class HandleNoSuchElementService {

    @ExceptionHandler
    public ResponseEntity<ApiError> noSuchElementExceptionHandler(HttpClientErrorException ex) {
        return new ResponseEntity<>(
                new ApiError(ex.getMessage()),
                HttpStatus.NOT_FOUND
        );
    }
}
