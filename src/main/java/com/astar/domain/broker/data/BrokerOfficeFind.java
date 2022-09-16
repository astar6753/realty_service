package com.astar.domain.broker.data;


import org.springframework.http.HttpStatus;

import com.astar.global.common.Pagination;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;

public class BrokerOfficeFind {

    @Data
    @ApiModel(value="BrokerOfiiceSearch.Request : 공인중개사 사무소 조회 요청", description="공인중개사 사무소 조회 요청")
    public static class Request {

        private Pagination.Request pagination;

        @ApiModelProperty(value="요청 검색어(공인중개사 상호명)", example = "스프링 공인중개사")
        private String keyword;

    }

    @Getter @Builder
    @ApiModel(value="BrokerOfiiceSearch.Response : 공인중개사 사무소 조회 응답", description="공인중개사 사무소 조회 응답")
    public static class Response {

        @ApiModelProperty(value="HttpStatus", example = "OK")
        private HttpStatus status;
        
        @ApiModelProperty(value="응답 메세지", example = "공인중개사 회원 정보가 추가되었습니다.")
        private String message;
        
        private BrokerOfficeInfoVO result;
    }
}
