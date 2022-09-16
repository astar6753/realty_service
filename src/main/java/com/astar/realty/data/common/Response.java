package com.astar.realty.data.common;

import org.springframework.http.HttpStatus;

import io.swagger.annotations.ApiModel;
import lombok.Builder;
import lombok.Getter;

@Getter @Builder
@ApiModel(value="Response: Response용 공용객체", description="Response용 공용객체")
public class Response {
    private HttpStatus status;
    private String message;
    private Integer count;
    private Object result;
}
