package com.astar.realty.domain.broker.data;

import java.util.List;

import org.springframework.http.HttpStatus;

import com.astar.realty.global.common.Pagination;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;

public class BrokerOfficeSearch {

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
        
        @ApiModelProperty(value="응답 메세지", example = "공인중개사 사무소 정보가 조회되었습니다.")
        private String message;
        
        private Result result;
    }

    @Data @AllArgsConstructor
    @ApiModel(value="BrokerOfiiceSearch.Result : 공인중개사 사무소 조회 결과", description="공인중개사 사무소 조회 결과")
    public static class Result {
        
        @ApiModelProperty(value="요청 검색어(공인중개사 상호명)", example = "스프링 공인중개사")
        private String keyword;
        private List<BrokerOfficeInfoVO> searchResult;
        private Pagination.Response pagination;        
    }
    
}
