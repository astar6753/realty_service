package com.astar.realty.data.broker;

import lombok.Data;

@Data
public class BrokerInfoVO {
    private Integer bork_seq;
    private Integer bork_boi_seq;
    private String bork_id;
    private String bork_pwd;
    private String bork_name;
    private String bork_phone;
    private Integer bork_status;
    private String bork_image_file;    
}
