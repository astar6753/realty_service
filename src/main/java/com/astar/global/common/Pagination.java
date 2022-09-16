package com.astar.global.common;


import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;

public class Pagination {
    
    @Data
    @ApiModel(value="Pagination.Request : 페이징 요청", description="페이징 요청")
    public static class Request {

        @ApiModelProperty(value="요청 페이지 (null일시 1)", example = "1")
        private Integer page;

        @ApiModelProperty(value="요청 정보 리스트 사이즈 (null일시 10)", example = "10")
        private Integer limit;

    }
    
    @Getter @AllArgsConstructor
    @ApiModel(value="Pagination.Response : 페이징 응답", description="페이징 응답")
    public static class Response {

        @ApiModelProperty(value="현재 페이지", example = "1")
        private Integer currentPage;

        @ApiModelProperty(value="총 페이지 수", example = "10")
        private Integer pageCnt;

        @ApiModelProperty(value="조회 정보 전체 수", example = "100")
        private Integer totalCnt;
    }
}
