package com.astar.domain.realty.data;

import lombok.Data;

@Data
public class RealtyTotalInfoVO {
    RealtyBasicInfoVO basic_info;
    RealtyOptionInfoVO option_info;
    RealtyPostInfoVO post_info;
}
