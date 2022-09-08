package com.astar.realty.data;

import java.util.Date;

import lombok.Data;

@Data
public class BrokerOfficeInfoVO {
    private Integer boi_seq;
    private String boi_name;
    private String boi_address;
    private String boi_phone;
    private String boi_master_name;
    private String boi_reg_number;
    private Date boi_reg_dt;
    private String boi_image_file;
}
