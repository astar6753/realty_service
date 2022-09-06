package com.astar.realty.data.response;

import org.springframework.http.HttpStatus;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@AllArgsConstructor
public class Response {
    private HttpStatus status;
    private String message;
    private Integer count;
    private Object result;
}
