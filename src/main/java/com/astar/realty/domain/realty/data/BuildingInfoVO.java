package com.astar.realty.domain.realty.data;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class BuildingInfoVO {

    @ApiModelProperty(value="건물 번호", example = "1")
    private Integer bi_seq;
    
    @ApiModelProperty(value="건물 이름", example = "교보 빌딩")
    private String bi_name;

    @ApiModelProperty(value="건물 전체 층수(지하 포함)", example = "20")
    private Integer bi_total_floor;

    @ApiModelProperty(value="최대 주차 차량 수", example = "200")
    private Integer bi_total_parking;

    @ApiModelProperty(value="엘리베이터 유무", example = "1")
    private Integer bi_elevator;

    @ApiModelProperty(value="건물 유형", example = "오피스")
    private String bi_use_type;
    
    @ApiModelProperty(value="건물 사용 승인일", example = "2001-01-01")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date bi_use_accepted_dt;
    
    @ApiModelProperty(value="건물 주소", example = "대구광역시 중구 동성로2가 88-1")
    private String bi_address;
    
    @ApiModelProperty(value="경도", example = "128.594018")
    private Double bi_longitude;
    
    @ApiModelProperty(value="위도", example = "35.868293")
    private Double bi_latitude;

}
