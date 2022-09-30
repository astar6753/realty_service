package com.astar.realty.domain.realty.data;

import org.springframework.http.HttpStatus;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;

public class BuildingModify {
    
    @Data
    @ApiModel(value="BuildingModify.Request : 건물 정보 변경 요청", description="건물 정보 변경 요청")
    public static class Request {

        @ApiModelProperty(value="건물 이름 변경 여부", example = "true")
        private Boolean update_name;

        @ApiModelProperty(value="건물 전체 층수 변경 여부", example = "true")
        private Boolean update_total_floor;

        @ApiModelProperty(value="건물 최대 주차 차량 수 변경 여부", example = "true")
        private Boolean update_total_parking;

        @ApiModelProperty(value="건물 엘리베이터 유무 변경 여부", example = "true")
        private Boolean update_elevator;

        @ApiModelProperty(value="건물 유형 변경 여부", example = "true")
        private Boolean update_use_type;

        @ApiModelProperty(value="건물 사용 승인일 변경 여부", example = "true")
        private Boolean update_use_accepted_dt;

        @ApiModelProperty(value="건물 주소 변경 여부", example = "true")
        private Boolean update_address;

        @ApiModelProperty(value="경도 변경 여부", example = "true")
        private Boolean update_longitude;

        @ApiModelProperty(value="위도 변경 여부", example = "true")
        private Boolean update_latitude;

        private BuildingInfoVO building_info;
    }

    @Getter @Builder
    @ApiModel(value="BuildingModify.Response : 건물 정보 변경 응답", description="건물 정보 변경 요청")
    public static class Response {

        @ApiModelProperty(value="HttpStatus", example = "OK")
        private HttpStatus status;
        
        @ApiModelProperty(value="응답 메세지", example = "건물 정보가 수정되었습니다.")
        private String message;

        @ApiModelProperty(value="성공 여부", allowableValues="true,false")
        private Boolean result;
    }
}
