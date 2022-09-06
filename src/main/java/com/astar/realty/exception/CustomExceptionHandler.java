package com.astar.realty.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.astar.realty.data.response.ErrorResponse;

@ControllerAdvice
public class CustomExceptionHandler {
    @ExceptionHandler(CustomExceptions.class)
    public ResponseEntity<ErrorResponse> userNotFoundException(CustomExceptions e) {
        ErrorResponse response = new ErrorResponse(e.getErrorCode(),e.getDesc());
        return new ResponseEntity<>(response, HttpStatus.valueOf(e.getErrorCode().getStatus()));
    }
}
