package com.astar.realty.domain.realty.data;

import org.springframework.http.HttpStatus;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;

public class RealtyPostCreate {
    
    @Data
    @ApiModel(value="RealtyPostCreate.Request : 부동산 매물 게시글 등록 요청", description="부동산 매물 게시글 등록 요청")
    public static class Request {
        RealtyBasicInfoVO basic_info;
        RealtyOptionInfoVO option_info;
        RealtyPostInfoVO post_info;
    }
    
    @Getter @Builder
    @ApiModel(value="RealtyPostCreate.Response : 부동산 매물 게시글 등록 응답", description="부동산 매물 게시글 등록 응답")
    public static class Response {

        @ApiModelProperty(value="HttpStatus", example="CREATED")
        private HttpStatus status;
        
        @ApiModelProperty(value="응답 메세지", example="부동산 매물 게시글을 등록하였습니다.")
        private String message;

        @ApiModelProperty(value="성공 여부", allowableValues="true,false")
        private Boolean result;
    }
}