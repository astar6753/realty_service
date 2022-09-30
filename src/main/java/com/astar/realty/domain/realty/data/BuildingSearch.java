package com.astar.realty.domain.realty.data;

import java.util.List;

import org.springframework.http.HttpStatus;

import com.astar.realty.global.common.Pagination;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;

public class BuildingSearch {

    @Data
    @ApiModel(value="BuildingSearch.Request : 건물 조회 요청", description="건물 조회 요청")
    public static class Request {

        private Pagination.Request pagination;

        @ApiModelProperty(value="요청 검색어(건물 이름)", example="교보 빌딩")
        private String keyword;

    }

    @Getter @Builder
    @ApiModel(value="BuildingSearch.Response : 건물 조회 응답", description="건물 조회 응답")
    public static class Response {

        @ApiModelProperty(value="HttpStatus", example="OK")
        private HttpStatus status;
        
        @ApiModelProperty(value="응답 메세지", example="건물 정보가 조회되었습니다.")
        private String message;
        
        private Result result;
    }

    @Data @AllArgsConstructor
    @ApiModel(value="BuildingSearch.Result : 건물 조회 결과", description="건물 조회 결과")
    public static class Result {
        
        @ApiModelProperty(value="요청 검색어(건물 이름)", example="교보 빌딩")
        private String keyword;
        private List<BuildingInfoVO> searchResult;
        private Pagination.Response pagination;

    }
    
}
