package com.astar.realty.data.response;

import org.springframework.http.HttpStatus;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class Response {
    private HttpStatus status;
    private String message;
    private Integer count;
    private Object result;
}
