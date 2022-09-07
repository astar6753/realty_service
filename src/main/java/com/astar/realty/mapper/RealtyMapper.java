package com.astar.realty.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.astar.realty.data.BuildingInfoVO;
import com.astar.realty.data.RealtyBasicInfoVO;
import com.astar.realty.data.RealtyOptionInfoVO;
import com.astar.realty.data.RealtyPostInfoVO;

@Mapper
public interface RealtyMapper {
    public void insertBuildingInfo(BuildingInfoVO request);
    public Boolean isExistBuilding(String address);
    
    public Integer selectBuildingTotalPages(String keyword);
    public Integer selectBuildingTotalCount(String keyword);
    public List<BuildingInfoVO> selectBuildingList(String keyword, Integer offset);

    public void insertRealtyOptionInfo(RealtyOptionInfoVO data);
    public void insertRealtyBasicInfo(RealtyBasicInfoVO data);
    public void insertRealtyPostInfo(RealtyPostInfoVO data);


}
