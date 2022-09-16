package com.astar.realty.exception;


import java.util.ArrayList;
import java.util.List;

import javax.validation.ConstraintViolationException;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.astar.realty.data.common.CustomFieldError;
import com.astar.realty.data.common.ErrorResponse;
import com.astar.realty.data.common.ValidationErrorResponse;

@ControllerAdvice
public class CustomExceptionHandler {
    @ExceptionHandler(CustomExceptions.class)
    public ResponseEntity<ErrorResponse> makeCustomExceptionResponse(CustomExceptions e) {
        ErrorResponse response = new ErrorResponse(e.getErrorCode(),e.getDesc());
        return new ResponseEntity<>(response, HttpStatus.valueOf(e.getErrorCode().getStatusCode()));
    }

    // @validation으로 객체 검증 MethodArgumentNotValidException발생시 handling
    // ValidationErrorResponse 객체에 CustomFieldError객체 리스트 담아서 반환
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ValidationErrorResponse> makeMethodNotValidExceptionResponse(MethodArgumentNotValidException e) {
        BindingResult bindingResult = e.getBindingResult();
        int errorCount = bindingResult.getFieldErrors().size();

        List<CustomFieldError> fieldErrors = new ArrayList<CustomFieldError>();
        for(int i = 0; i<errorCount; i++) {
            String validationName = bindingResult.getFieldErrors().get(i).getCode();
            String field = bindingResult.getFieldErrors().get(i).getField();
            String defaultMessage = bindingResult.getFieldErrors().get(i).getDefaultMessage();
            CustomFieldError fieldError = CustomFieldError.builder()
                                                    .validationName(validationName)
                                                    .field(field)
                                                    .defaultMessage(defaultMessage)
                                                    .build();
            fieldErrors.add(fieldError);
        }

        ValidationErrorResponse response = new ValidationErrorResponse(ErrorCode.INVALID_FIELD, errorCount, fieldErrors);
        return new ResponseEntity<>(response, HttpStatus.valueOf(response.getStatusCode()));
    }


    //MethodArgumentNotValidException
    // @RequestBody 어노테이션으로 받은 파라미터
    //BindException
    // @ModelAttribute 어노테이션으로 받은 파라미터
    //ConstraintViolationException 

    // @validation으로 requestparam 검증 ConstraintViolationException발생시 handling
    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<ValidationErrorResponse> makeConstraintViolationException(ConstraintViolationException e) {
        System.out.println("===========================================");
        System.out.println(e.getMessage());
        System.out.println("===========================================");
        System.out.println(e.getConstraintViolations());
        System.out.println("===========================================");
        return new ResponseEntity<>(null,null);
    }
    
}
