package com.astar.realty.data.response;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.Data;

@Data
public class RealtyPostViewVO {
    private Integer rpi_seq;
    private String rpi_title;
    private String rpi_content;
    private Integer rpi_bork_seq;
    private Integer rpi_rbi_seq;
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
    private Integer ro_seq;
    private Integer ro_induction;
    private Integer ro_elec_range;
    private Integer ro_ariconditioner;
    private Integer ro_washing_machine;
    private Integer ro_television;
    private Integer ro_closet;
    private Integer ro_bed;
    private Integer ro_desk;
    private Integer ro_shoe_closet;
    private Integer ro_bidet;
    private Integer ro_stove;
    private Integer ro_refrigerator;
    private Integer ro_elec_doorlock;
    private Integer bi_seq;
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
    private Integer bork_seq;
    private Integer bork_boi_seq;
    private String bork_id;
    private String bork_pwd;
    private String bork_name;
    private String bork_phone;
    private Integer bork_status;
    private String bork_image_file;
}
