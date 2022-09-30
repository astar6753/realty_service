package com.astar.realty.domain.realty.data;

import javax.validation.constraints.Size;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel(value="RealtyOptionInfoVO: 부동산 매물 옵션정보", description="부동산 매물 옵션정보")
public class RealtyOptionInfoVO {
    
    @ApiModelProperty(value="매물 옵션정보 번호")
    private Integer ro_seq;
    
    @ApiModelProperty(value="인덕션 여부", example="1", allowableValues="0,1")
    @Size(min=0,max=1)
    private Integer ro_induction;
    
    @ApiModelProperty(value="전자레인지 여부", example="1", allowableValues="0,1")
    @Size(min=0,max=1)
    private Integer ro_elec_range;
    
    @ApiModelProperty(value="에어컨 여부", example="1", allowableValues="0,1")
    @Size(min=0,max=1)
    private Integer ro_ariconditioner;

    @ApiModelProperty(value="세탁기 여부", example="1", allowableValues="0,1")
    @Size(min=0,max=1)
    private Integer ro_washing_machine;

    @ApiModelProperty(value="TV 여부", example="1", allowableValues="0,1")
    @Size(min=0,max=1)
    private Integer ro_television;
    
    @ApiModelProperty(value="옷장 여부", example="1", allowableValues="0,1")
    @Size(min=0,max=1)
    private Integer ro_closet;
    
    @ApiModelProperty(value="침대 여부", example="1", allowableValues="0,1")
    @Size(min=0,max=1)
    private Integer ro_bed;
    
    @ApiModelProperty(value="책상 여부", example="1", allowableValues="0,1")
    @Size(min=0,max=1)
    private Integer ro_desk;
    
    @ApiModelProperty(value="신발장 여부", example="1", allowableValues="0,1")
    @Size(min=0,max=1)
    private Integer ro_shoe_closet;
    
    @ApiModelProperty(value="비데 여부", example="1", allowableValues="0,1")
    @Size(min=0,max=1)
    private Integer ro_bidet;
    
    @ApiModelProperty(value="가스레인지 여부", example="1", allowableValues="0,1")
    @Size(min=0,max=1)
    private Integer ro_stove;
    
    @ApiModelProperty(value="냉장고 여부", example="1", allowableValues="0,1")
    @Size(min=0,max=1)
    private Integer ro_refrigerator;
    
    @ApiModelProperty(value="전자도어락 여부", example="1", allowableValues="0,1")
    @Size(min=0,max=1)
    private Integer ro_elec_doorlock;
}
