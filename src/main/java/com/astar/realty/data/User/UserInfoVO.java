package com.astar.realty.data.User;

import java.util.Date;

import lombok.Data;

@Data
public class UserInfoVO {
    private Integer ui_seq;
    private String ui_id;
    private String ui_pwd;
    private String ui_name;
    private Date ui_birth;
    private Integer ui_gen;
    private String ui_phone;
    private Date ui_reg_dt;
    private Integer ui_status;
    private String ui_image_file;
}
