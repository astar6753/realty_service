package com.astar.realty.domain.realty.data;

import org.springframework.http.HttpStatus;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;

public class RealtyPostModify {
    
    @Data
    @ApiModel(value="RealtyPostModify.Request : 부동산 매물 게시글 수정 요청", description="부동산 매물 게시글 수정 요청")
    public static class Request {
        RealtyBasicInfoVO basic_info;
        RealtyOptionInfoVO option_info;
        RealtyPostInfoVO post_info;
    }
    
    @Getter @Builder
    @ApiModel(value="RealtyPostModify.Response : 부동산 매물 게시글 수정 응답", description="부동산 매물 게시글 수정 응답")
    public static class Response {

        @ApiModelProperty(value="HttpStatus", example="OK")
        private HttpStatus status;
        
        @ApiModelProperty(value="응답 메세지", example="부동산 매물 게시글을 수정하였습니다.")
        private String message;

        @ApiModelProperty(value="성공 여부", allowableValues="true,false")
        private Boolean result;
    }

}