package com.astar.realty.domain.realty.data;

import java.util.List;

import org.springframework.http.HttpStatus;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;

public class RealtyMaintainShow {
    
    @Getter @Builder
    @ApiModel(value="RealtyMaintainInfoVO.Response : 부동산 관리비 항목 조회 응답", description="부동산 관리비 항목 조회 응답")
    public static class Response {
        
        @ApiModelProperty(value="HttpStatus", example="OK")
        private HttpStatus status;
        
        @ApiModelProperty(value="응답 메세지", example="건물 정보가 조회되었습니다.")
        private String message;

        private Result result;
    }
    
    @Data @AllArgsConstructor
    @ApiModel(value="RealtyMaintainInfoVO.Result : 부동산 관리비 항목 조회 결과", description="부동산 관리비 항목 조회 결과")
    public static class Result {

        @ApiModelProperty(value="관리비 항목 총 개수")
        private Integer totalCount;

        private List<RealtyMaintainInfoVO> list;
    }
}
