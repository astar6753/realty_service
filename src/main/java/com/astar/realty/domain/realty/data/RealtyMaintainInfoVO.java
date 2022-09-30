package com.astar.realty.domain.realty.data;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel(value="RealtyMaintainInfoVO: 부동산 관리비 항목 정보", description="부동산 관리비 항목 정보")
public class RealtyMaintainInfoVO {
    
    @ApiModelProperty(value="관리비 항목 정보 번호", example="1")
    private Integer maintain_seq;
    
    @ApiModelProperty(value="관리비 항목 이름", example="인터넷")
    private String maintain_name;
}
