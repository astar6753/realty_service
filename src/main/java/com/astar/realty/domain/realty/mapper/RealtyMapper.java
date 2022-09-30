package com.astar.realty.domain.realty.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.astar.realty.domain.realty.data.BuildingInfoVO;
import com.astar.realty.domain.realty.data.BuildingModify;
import com.astar.realty.domain.realty.data.RealtyBasicInfoVO;
import com.astar.realty.domain.realty.data.RealtyMaintainInfoVO;
import com.astar.realty.domain.realty.data.RealtyOptionInfoVO;
import com.astar.realty.domain.realty.data.RealtyPostInfoVO;
import com.astar.realty.domain.realty.data.RealtyPostSearchVO;
import com.astar.realty.domain.realty.data.RealtyPostViewVO;

@Mapper
public interface RealtyMapper {

    //BuildingCreate
    public int insertBuildingInfo(BuildingInfoVO info);
    public Boolean isExistBuildingAddress(String noneSpacingAddress);
    
    //BuildingSearch
    public Integer selectBuildingPageCount(String keyword, Integer limit);
    public Integer selectBuildingTotalCount(String keyword);
    public List<BuildingInfoVO> selectBuildingList(String keyword,  Integer limit, Integer offset);

    //BuildingModify
    public int updateBuildingInfo(BuildingModify.Request request);
    public Boolean isExistBuildingInfoBySeq(Integer building_no);

    //BuildingRemove
    public int deleteBuildingInfo(Integer building_no);

    //RealtyPostCreate
    public int insertRealtyOptionInfo(RealtyOptionInfoVO data);
    public int insertRealtyBasicInfo(RealtyBasicInfoVO data);
    public int insertRealtyPostInfo(RealtyPostInfoVO data);

    //RealtyPostModify
    public int updateRealtyBasicInfo(RealtyBasicInfoVO data);
    public int updateRealtyOptionInfo(RealtyOptionInfoVO data);
    public Boolean isExistRealtyOptionInfoBySeq(Integer option_no);
    public int updateRealtyPostInfo(RealtyPostInfoVO data);
    public Boolean isExistRealtyPostInfoBySeq(Integer post_no);    

    //MaintainInfoCreate
    public Boolean isExistMaintainName(String name);
    public int insertMaintainItem(String name);

    //MaintainInfoShow
    public Integer selectMaintainItemCount();
    public List<RealtyMaintainInfoVO> selectMaintainItemList();

    //MaintainInfoRemove
    public Boolean isExistMaintainItemBySeq(Integer maintain_no);
    public int deleteMaintainItemBySeq(Integer maintain_no);

    //RealtyPostSearch
    public List<RealtyPostViewVO> selectRealtyPostList(RealtyPostSearchVO searchKeywords, Integer limit, Integer offset);
    public Integer selectRealtyPostPageCount(RealtyPostSearchVO searchKeywords, Integer limit);
    public Integer selectRealtyPostTotalCount(RealtyPostSearchVO searchKeywords);

    //RealtyPostFind
    public RealtyPostViewVO selectRealtyPostInfoBySeq(Integer post_no);

}
