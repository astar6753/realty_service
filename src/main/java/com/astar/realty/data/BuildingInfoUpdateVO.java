package com.astar.realty.data;

import lombok.Data;

@Data
public class BuildingInfoUpdateVO {    
    private Boolean update_name;
    private Boolean update_total_floor;
    private Boolean update_total_parking;
    private Boolean update_elevator;
    private Boolean update_use_type;
    private Boolean update_use_accepted_dt;
    private Boolean update_address;
    private Boolean update_longitude;
    private Boolean update_latitude;
    private BuildingInfoVO building_info;
}
