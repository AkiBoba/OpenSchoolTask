package com.example.consumerservice.ErrorService;

import com.example.consumerservice.domain.ApiError;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.client.HttpClientErrorException;

public class  HandlerHttpClientErrorServer {

    @ExceptionHandler
    public ResponseEntity<ApiError> httpClientErrorHandler(HttpClientErrorException ex) {
        return new ResponseEntity<>(
                new ApiError(ex.getMessage()),
                HttpStatus.INTERNAL_SERVER_ERROR
        );
    }
}
