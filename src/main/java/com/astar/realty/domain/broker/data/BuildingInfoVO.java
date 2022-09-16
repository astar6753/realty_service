package com.astar.realty.domain.broker.data;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class BuildingInfoVO {
    private Integer bi_seq;
    @ApiModelProperty("교보빌딩")
    private String bi_name;
    private Integer bi_total_floor;
    private Integer bi_total_parking;
    private Integer bi_elevator;
    private String bi_use_type;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date bi_use_accepted_dt;
    private String bi_address;
    private Double bi_longitude;
    private Double bi_latitude;
}
