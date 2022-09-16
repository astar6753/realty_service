package com.astar.domain.user.data;

import java.util.Date;

import lombok.Data;

@Data
public class UserRealtyLookupVO {
    private Integer rli_seq;
    private Integer rli_ui_seq;
    private Integer rli_rpi_seq;
    private Date rli_time;
}
