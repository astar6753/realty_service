package com.astar.domain.realty.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.astar.domain.broker.data.BuildingInfoUpdateVO;
import com.astar.domain.broker.data.BuildingInfoVO;
import com.astar.domain.realty.data.RealtyBasicInfoVO;
import com.astar.domain.realty.data.RealtyMaintainInfoVO;
import com.astar.domain.realty.data.RealtyOptionInfoVO;
import com.astar.domain.realty.data.RealtyPostInfoVO;
import com.astar.domain.realty.data.RealtyPostViewVO;
import com.astar.domain.realty.data.RealtySearchRequestVO;

@Mapper
public interface RealtyMapper {
    public void insertBuildingInfo(BuildingInfoVO data);
    public Boolean isExistBuildingAddress(String address);
    
    public Integer selectBuildingTotalPages(String keyword);
    public Integer selectBuildingTotalCount(String keyword);
    public List<BuildingInfoVO> selectBuildingList(String keyword, Integer offset);

    public void updateBuildingInfo(BuildingInfoUpdateVO data);
    public Boolean isExistBuildingInfoBySeq(Integer building_seq);
    public void deleteBuildingInfo(Integer building_seq);

    public void insertRealtyOptionInfo(RealtyOptionInfoVO data);
    public void insertRealtyBasicInfo(RealtyBasicInfoVO data);
    public void insertRealtyPostInfo(RealtyPostInfoVO data);

    public void updateRealtyBasicInfo(RealtyBasicInfoVO data);
    public void updateRealtyOptionInfo(RealtyOptionInfoVO data);
    public void updateRealtyPostInfo(RealtyPostInfoVO data);

    public Boolean isExistMaintainItem(String name);
    public void insertMaintainItem(String name);

    public Integer selectMaintainItemCount();
    public List<RealtyMaintainInfoVO> selectMaintainItemList();

    public void deleteMaintainItem();

    //확인 필요
    public RealtyPostViewVO selectPostList(RealtySearchRequestVO request, Integer offset);
    public Integer selectPostCnt(RealtySearchRequestVO request);
    public Integer selectPostPageCnt(RealtySearchRequestVO request);

    public RealtyPostViewVO selectPostInfoBySeq(Integer seq);

}
