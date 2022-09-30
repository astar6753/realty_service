package com.astar.realty.domain.realty.data;

import java.util.Date;

import javax.validation.constraints.Size;

import org.springframework.format.annotation.DateTimeFormat;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel(value="RealtyPostSearchVO : 매물 게시글 검색어", description="매물 게시글 검색어")
public class RealtyPostSearchVO {

    @ApiModelProperty(value="최소 가격(보증금/매매가)", example = "5000")        
    private Integer min_price;
    
    @ApiModelProperty(value="최대 가격(보증금/매매가)", example = "50000")
    private Integer max_price;

    @ApiModelProperty(value="최소 월세", example = "20")
    private Integer min_monthly_price;

    @ApiModelProperty(value="최대 월세", example = "100")
    private Integer max_monthly_price;

    
    @ApiModelProperty(value="최소 관리비", example = "1")
    private Integer min_maintain_price;
    
    @ApiModelProperty(value="최대 관리비", example = "20")
    private Integer max_maintain_price;
    
    @ApiModelProperty(value="매물 유형(1:월세/2:전세/3:매매)", example="1", allowableValues="1,2,3")
    @Size(min=1,max=3)
    private Integer sale_type;

    @ApiModelProperty(value="최소 가구당 주차 가능 대수", example="1.0")
    private Double parking_count;

    @ApiModelProperty(value="단기 임대 여부", example="1", allowableValues="0,1")
    @Size(min=0,max=1)
    private Integer short_term_lease;

    @ApiModelProperty(value="방 종류(0:원룸/1:투룸/2:쓰리룸/3:빌라/4:오피스텔/5:아파트/6:단독주택)", example="1", allowableValues="0,1,2,3,4,5,6")
    @Size(min=0,max=6)
    private Integer room_type;
    
    @ApiModelProperty(value="매물 층 수", example="[1,2]")
    private Integer[] floor;
    
    @ApiModelProperty(value="최소 공급 면적 (단위:㎡)", example="29.75")
    private Double min_supply_area;

    @ApiModelProperty(value="최대 공급 면적 (단위:㎡)", example="36.75")
    private Double max_supply_area;

    @ApiModelProperty(value="방 개수", example="2")
    private Integer room_count;

    @ApiModelProperty(value="건물 방향(0:동/1:서/2:남/3:북)", example="[0,2]")
    private Integer[] room_direction;

    @ApiModelProperty(value="주방 타입(0:일반/1:빌트인)", example="0", allowableValues="0,1")
    @Size(min=0,max=1)
    private Integer kitchen_type;

    @ApiModelProperty(value="발코니타입(0:없음/1:확장)", example="0", allowableValues="0,1")
    @Size(min=0,max=1)
    private Integer balcony_type;

    @ApiModelProperty(value="최소 입주 가능일")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date min_available_dt;

    @ApiModelProperty(value="최대 입주 가능일(default:now())")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date max_available_dt;

    @ApiModelProperty(value="주방 구조(0:통합/1:분리)", example="0", allowableValues="0,1")
    @Size(min=0,max=1)
    private Integer kitchen_structure;

    @ApiModelProperty(value="인덕션 여부", example="1", allowableValues="0,1")
    @Size(min=0,max=1)
    private Integer induction;
    
    @ApiModelProperty(value="전자레인지 여부", example="1", allowableValues="0,1")
    @Size(min=0,max=1)
    private Integer elec_range;
    
    @ApiModelProperty(value="에어컨 여부", example="1", allowableValues="0,1")
    @Size(min=0,max=1)
    private Integer ariconditioner;

    @ApiModelProperty(value="세탁기 여부", example="1", allowableValues="0,1")
    @Size(min=0,max=1)
    private Integer washing_machine;

    @ApiModelProperty(value="TV 여부", example="1", allowableValues="0,1")
    @Size(min=0,max=1)
    private Integer television;
    
    @ApiModelProperty(value="옷장 여부", example="1", allowableValues="0,1")
    @Size(min=0,max=1)
    private Integer closet;

    @ApiModelProperty(value="침대 여부", example="1", allowableValues="0,1")
    @Size(min=0,max=1)
    private Integer bed;

    @ApiModelProperty(value="책상 여부", example="1", allowableValues="0,1")
    @Size(min=0,max=1)
    private Integer desk;

    @ApiModelProperty(value="신발장 여부", example="1", allowableValues="0,1")
    @Size(min=0,max=1)
    private Integer shoe_closet;
    
    @ApiModelProperty(value="비데 여부", example="1", allowableValues="0,1")
    @Size(min=0,max=1)
    private Integer bidet;
    
    @ApiModelProperty(value="가스레인지 여부", example="1", allowableValues="0,1")
    @Size(min=0,max=1)
    private Integer stove;
    
    @ApiModelProperty(value="냉장고 여부", example="1", allowableValues="0,1")
    @Size(min=0,max=1)
    private Integer refrigerator;
    
    @ApiModelProperty(value="전자도어락 여부", example="1", allowableValues="0,1")
    @Size(min=0,max=1)
    private Integer elec_doorlock;
    
    @ApiModelProperty(value="엘리베이터 유무", example = "1")
    private Integer elevator;
    
    @ApiModelProperty(value="건물 주소", example = "대구광역시 중구 동성로2가 88-1")
    private String address;

    @ApiModelProperty(value="사용자 지도 중심좌표 위도", example = "35.868293")
    private Double center_point_latitude;
    
    @ApiModelProperty(value="사용자 지도 중심좌표 경도", example = "128.594018")
    private Double center_point_longitude;
    
    @ApiModelProperty(value="사용자 지도 중심좌표 반경(단위:km)", example = "3")
    private Double center_point_radius;
}
