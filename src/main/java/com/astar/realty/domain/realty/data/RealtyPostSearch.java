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

public class RealtyPostSearch {

    @Data
    @ApiModel(value="RealtyPostSearch.Request : 매물 게시글 검색 요청", description="매물 게시글 검색 요청")
    public static class Request {

        private Pagination.Request pagination;
        private RealtyPostSearchVO searchKeywords;

    }

    @Getter @Builder
    @ApiModel(value="RealtyPostSearch.Response : 매물 게시글 검색 응답", description="매물 게시글 검색 응답")
    public static class Response {

        @ApiModelProperty(value="HttpStatus", example = "OK")
        private HttpStatus status;
        
        @ApiModelProperty(value="응답 메세지", example = "공인중개사 사무소 정보가 조회되었습니다.")
        private String message;
        
        private Result result;
    }

    @Data @AllArgsConstructor
    @ApiModel(value="RealtyPostSearch.Result : 매물 게시글 검색 결과", description="매물 게시글 검색 결과")
    public static class Result {
        
        private RealtyPostSearchVO searchKeywords;        
        private List<RealtyPostViewVO> searchResult;
        private Pagination.Response pagination;
    }
    
}
