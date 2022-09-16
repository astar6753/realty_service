package com.astar.realty.domain.realty.data;

import java.util.Date;

import lombok.Data;

@Data
public class RealtySearchRequestVO {

    private Integer min_price;              // 가격(보증금/매매가)
    private Integer max_price;
    private Integer min_monthly_price;      // 월세
    private Integer max_monthly_price;
    private Integer min_maintain_price;     // 관리비
    private Integer max_maintain_price;
    private Integer sale_type;              // 월세1전세2매매3
    private Double parking_count;           // 주차가능여부
    private Integer short_term_lease;       // 단기임대 가능여부
    private Integer room_type;              // 원룸0/투룸1/쓰리룸2/빌라3/아파트4/오피스텔5
    private Integer[] floor;                // 층 (ex:{2,3,4,5})
    private Double min_supply_area;         // 공급면적
    private Double max_supply_area;
    private Integer room_count;             // 방 개수
    private Integer[] room_direction;       // 방향(동0서1남2북3) (ex:{1,2})
    private Integer kitchen_type;           // 주방 형태(0통합1분리)
    private Integer balcony_type;           // 베란다 발코니 유무(0무1유)
    private Date min_available_dt;          // 사용승인일 ##현재 연도 마이너스 계산 필요
    private Date max_available_dt;
    private Integer kitchen_structure;      // 주방 빌트인 여부(0부/1여)
    private Integer induction;              // 옵션 항목 유무(0무1유)
    private Integer elec_range;
    private Integer ariconditioner;
    private Integer washing_machine;
    private Integer television;
    private Integer closet;
    private Integer bed;
    private Integer desk;
    private Integer shoe_closet;
    private Integer bidet;
    private Integer stove;
    private Integer refrigerator;
    private Integer elec_doorlock;
    private Integer elevator;
    private String address;                 // 주소검색 키워드

    //분리 필요
    private Double center_point_latitude;   //사용자 지도 중심좌표 위도
    private Double center_point_longitude;   //사용자 지도 중심좌표 경도
    private Double center_point_radius;   //사용자 지도 중심좌표 반경(단위:km)
}
