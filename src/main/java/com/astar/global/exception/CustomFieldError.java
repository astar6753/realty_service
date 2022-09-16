package com.astar.global.exception;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;
import lombok.Data;

@Data @Builder
@ApiModel(
    value="CustomFieldError: MethodArgumentNotValidException발생시 FieldError처리 객체", 
    description="MethodArgumentNotValidException발생시 CustomExceptionHandler.makeMethodNotValidExceptionResponse에서 FieldError 처리용 객체"
)
public class CustomFieldError {

    @ApiModelProperty(value="validation annotation name", notes="bindingResult.getFieldError().getCode()", example="@Size @Notnull 등 FieldError발생한 어노테이션 이름 반환")
    private String validationName;

    @ApiModelProperty(value="argument field", notes="bindingResult.getFieldError().getField()", example = "FieldError발생한 parameter 이름 반환")
    private String field;
    
    @ApiModelProperty(value="설명", notes="bindingResult.getFieldError().getDefaultMessage()", example = "어노테이션에서 사전에 정의한 message, 없을 경우 default message 반환")
    private String defaultMessage;
}
