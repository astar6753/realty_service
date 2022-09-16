package com.astar.realty.data.common;



import com.astar.realty.exception.ErrorCode;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel(
    value="ErrorResponse: MethodArgumentNotValidException발생시 응답", 
    description="MethodArgumentNotValidException발생시 CustomExceptionHandler.makeMethodNotValidExceptionResponse에서 Response 처리용 객체"
)
public class ErrorResponse {

    @ApiModelProperty(value="httpStatusCode", notes="ErrorCode에서 정의한 httpStatusCode", example="400")
    private int statusCode;

    @ApiModelProperty(value="httpStatusText", notes="ErrorCode에서 정의한 error의 httpStatusText", example="Bad Request")
    private String statusText;

    @ApiModelProperty(value="exceptionCode", notes="ErrorCode에서 정의한 error의 exceptionCode", example="@Size @Notnull 등 FieldError발생한 어노테이션 이름 반환")
    private String exceptionCode;

    @ApiModelProperty(value="message", notes="ErrorCode에서 정의한 error의 message 없을 경우 defaultmessage반환", example="@Size @Notnull 등 FieldError발생한 어노테이션 이름 반환")
    private String message;

    @ApiModelProperty(value="validation annotation name", notes="bindingResult.getFieldError().getCode()", example="@Size @Notnull 등 FieldError발생한 어노테이션 이름 반환")
    private String desc;            // 서비스단에서 정의한 추가 설명 메세지

    public ErrorResponse(ErrorCode errorCode, String desc){
        this.statusCode = errorCode.getStatusCode();
        this.statusText = errorCode.getStatusText();
        this.exceptionCode = errorCode.getExceptionCode();
        this.message = errorCode.getMessage();
        this.desc = desc;
    }
}
