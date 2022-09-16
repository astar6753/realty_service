package com.astar.global.exception;

import lombok.Builder;
import lombok.Getter;

//custom exception 정보 이너클래스로 정의
@Getter
@Builder
public class CustomExceptions extends RuntimeException {   

    private ErrorCode errorCode;
    private String desc;    
    // private String detail;       

    public CustomExceptions(ErrorCode errorCode, String desc) {
        // super(message);
        this.errorCode = errorCode;
        this.desc = desc;
    }

}
