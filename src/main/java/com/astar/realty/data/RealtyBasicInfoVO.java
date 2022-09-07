package com.astar.realty.data;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.Data;

@Data
public class RealtyBasicInfoVO {
    private Integer rbi_seq;
    private Integer rbi_price;
    private Integer rbi_monthly_price;
    private Integer rbi_maintain_price;
    private Integer rbi_sale_type;
    private Double rbi_parking_count;
    private Integer rbi_short_term_lease;
    private Integer rbi_room_type;
    private Integer rbi_floor;
    private Double rbi_supply_area;
    private Double rbi_use_area;
    private Integer rbi_room_count;
    private Integer rbi_buliding_number;
    private Integer rbi_room_number;
    private Integer rbi_room_direction;
    private Integer rbi_heating_type;
    private Integer rbi_kitchen_type;
    private Integer rbi_balcony_type;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date rbi_available_dt;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date rbi_reg_dt;
    private Integer rbi_status;
    private Integer rbi_kitchen_structure;
    private String rbi_maintain_list;
    private Integer rbi_bi_seq;
    private Integer rbi_ro_seq;
}
