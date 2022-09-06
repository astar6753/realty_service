package com.astar.realty.data.response;


import com.astar.realty.exception.ErrorCode;

import lombok.Data;

//error response용 data객체
@Data
public class ErrorResponse {
    private int status;
    private String message;
    private String code;
    private String desc;
    
    public ErrorResponse(ErrorCode errorCode,String desc){
        this.status = errorCode.getStatus();
        this.message = errorCode.getMessage();
        this.code = errorCode.getCode();
        this.desc = desc;
    }
}
