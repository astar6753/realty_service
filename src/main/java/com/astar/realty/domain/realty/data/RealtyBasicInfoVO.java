package com.astar.realty.domain.realty.data;

import java.util.Date;

import javax.validation.constraints.Size;

import org.springframework.format.annotation.DateTimeFormat;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel(value="RealtyBasicInfoVO: 부동산 매물 기본정보", description="부동산 매물 기본정보")
public class RealtyBasicInfoVO {

    @ApiModelProperty(value="매물 기본정보 번호")
    private Integer rbi_seq;

    @ApiModelProperty(value="매물 (임대-보증금/매매-매매가)가격(단위:만 원)", example="1000")
    private Integer rbi_price;

    @ApiModelProperty(value="매물 월세(단위:만 원)", example="50")
    private Integer rbi_monthly_price;

    @ApiModelProperty(value="매물 관리비(단위:만 원)", example="5")
    private Integer rbi_maintain_price;

    @ApiModelProperty(value="매물 유형(1:월세/2:전세/3:매매)", example="1", allowableValues="1,2,3")
    @Size(min=1,max=3)
    private Integer rbi_sale_type;

    @ApiModelProperty(value="가구당 주차 가능 대수", example="1.0")
    private Double rbi_parking_count;

    @ApiModelProperty(value="단기 임대 여부", example="1", allowableValues="0,1")
    @Size(min=0,max=1)
    private Integer rbi_short_term_lease;

    @ApiModelProperty(value="방 종류(0:원룸/1:투룸/2:쓰리룸/3:빌라/4:오피스텔/5:아파트/6:단독주택)", example="1", allowableValues="0,1,2,3,4,5,6")
    @Size(min=0,max=6)
    private Integer rbi_room_type;

    @ApiModelProperty(value="매물 층 수", example="1")
    private Integer rbi_floor;

    @ApiModelProperty(value="공급 면적 (단위:㎡)", example="29.75")
    private Double rbi_supply_area;

    @ApiModelProperty(value="전용 면적 (단위:㎡)", example="26.44")
    private Double rbi_use_area;

    @ApiModelProperty(value="방 개수", example="2")
    private Integer rbi_room_count;
    
    @ApiModelProperty(value="동", example="100")
    private Integer rbi_buliding_number;

    @ApiModelProperty(value="호", example="101")
    private Integer rbi_room_number;

    @ApiModelProperty(value="건물 방향(0:동/1:서/2:남/3:북)", example="2")
    @Size(min=0,max=3)
    private Integer rbi_room_direction;
    
    @ApiModelProperty(value="건물 난방(0:가스개별/1:전기개발/2:가스중앙집중/3:전기중앙집중/4:기름보일러개별/5:보일러 중앙집중)", example="2", allowableValues="0,1,2,3,4,5")
    @Size(min=0,max=5)
    private Integer rbi_heating_type;

    @ApiModelProperty(value="주방 타입(0:일반/1:빌트인)", example="0", allowableValues="0,1")
    @Size(min=0,max=1)
    private Integer rbi_kitchen_type;
    
    @ApiModelProperty(value="발코니타입(0:없음/1:확장)", example="0", allowableValues="0,1")
    @Size(min=0,max=1)
    private Integer rbi_balcony_type;
    
    @ApiModelProperty(value="입주 가능일(default:now())")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date rbi_available_dt;
    
    @ApiModelProperty(value="매물 등록일(default:now())")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date rbi_reg_dt;

    @ApiModelProperty(value="매물 상태(0:거래가능/1:거래중/2:거래완료)", allowableValues="0,1,2")
    @Size(min=0,max=2)
    private Integer rbi_status;

    @ApiModelProperty(value="주방 구조(0:통합/1:분리)", example="0", allowableValues="0,1")
    @Size(min=0,max=1)
    private Integer rbi_kitchen_structure;

    @ApiModelProperty(value="관리비 포함 항목(데이터 예시 : 1;2;3)", example="1;2;3")
    private String rbi_maintain_list;

    @ApiModelProperty(value="건물 정보 번호", example="1")
    private Integer rbi_bi_seq;

    @ApiModelProperty(value="건물 옵션 정보 번호", example="1")
    private Integer rbi_ro_seq;
}
