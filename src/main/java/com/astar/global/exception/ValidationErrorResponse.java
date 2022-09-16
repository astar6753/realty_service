package com.astar.global.exception;

import java.util.List;

import io.swagger.annotations.ApiModel;
import lombok.Data;

@Data
@ApiModel(value="ValidationErrorResponse: @Valid처리시 발생한 FieldError 응답", description="")
public class ValidationErrorResponse {
    
    private int statusCode;         // ErrorCode에서 정의한 error의 httpStatusCode
    private String statusText;      // ErrorCode에서 정의한 error의 httpStatusText
    private String exceptionCode;   // ErrorCode에서 정의한 error의 exceptionCode
    private String message;         // ErrorCode에서 정의한 error의 message
    private int errorCount;
    private List<CustomFieldError> fieldErrors;

    
    public ValidationErrorResponse(ErrorCode errorCode, int errorCount, List<CustomFieldError> fieldErrors){
        this.statusCode = errorCode.getStatusCode();
        this.statusText = errorCode.getStatusText();
        this.exceptionCode = errorCode.getExceptionCode();
        this.message = errorCode.getMessage();
        this.errorCount = errorCount;
        this.fieldErrors = fieldErrors;
    }
}
