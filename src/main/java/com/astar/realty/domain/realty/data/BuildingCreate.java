package com.astar.realty.domain.realty.data;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;

public class BuildingCreate {
    
    @Data
    @ApiModel(value="BuildingCreate.Request : 건물 등록 요청", description="건물 등록 요청")
    public static class Request {

        @ApiModelProperty(value="건물 이름", example = "교보 빌딩")
        private String name;

        @ApiModelProperty(value="건물 전체 층수(지하 포함)", example = "20")
        private Integer totalFloor;

        @ApiModelProperty(value="최대 주차 차량 수", example = "200")
        private Integer totalParking;

        @ApiModelProperty(value="엘리베이터 유무", example = "1")
        private Integer elevator;

        @ApiModelProperty(value="건물 유형", example = "오피스")
        private String useType;

        @ApiModelProperty(value="건물 사용 승인일", example = "2001-01-01")
        @DateTimeFormat(pattern = "yyyy-MM-dd")
        private Date useAcceptedDate;

        @ApiModelProperty(value="건물 주소", example = "대구광역시 중구 동성로2가 88-1")
        private String address;

        @ApiModelProperty(value="경도", example = "128.594018")
        private Double longitude;

        @ApiModelProperty(value="위도", example = "35.868293")
        private Double latitude;

        public BuildingInfoVO toEntity() {
            return BuildingInfoVO.builder()
                                .bi_name(name)
                                .bi_total_floor(totalFloor)
                                .bi_total_parking(totalParking)
                                .bi_elevator(elevator)
                                .bi_use_type(useType)
                                .bi_use_accepted_dt(useAcceptedDate)
                                .bi_address(address)
                                .bi_longitude(longitude)
                                .bi_latitude(latitude)
                                .build();
        }

    }
    
    @Getter @Builder
    @ApiModel(value="BuildingCreate.Response : 건물 등록 응답", description="건물 등록 응답")
    public static class Response {

        @ApiModelProperty(value="HttpStatus", example="CREATED")
        private HttpStatus status;
        
        @ApiModelProperty(value="응답 메세지", example="건물 정보가 등록되었습니다.")
        private String message;

        @ApiModelProperty(value="성공 여부", allowableValues="true,false")
        private Boolean result;
    }
}
