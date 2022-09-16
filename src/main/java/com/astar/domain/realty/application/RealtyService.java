package com.astar.domain.realty.application;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.astar.domain.broker.data.BuildingInfoUpdateVO;
import com.astar.domain.broker.data.BuildingInfoVO;
import com.astar.domain.realty.data.RealtyBasicInfoVO;
import com.astar.domain.realty.data.RealtyMaintainInfoVO;
import com.astar.domain.realty.data.RealtyOptionInfoVO;
import com.astar.domain.realty.data.RealtyPostInfoVO;
import com.astar.domain.realty.data.RealtySearchRequestVO;
import com.astar.domain.realty.data.RealtyTotalInfoVO;
import com.astar.domain.realty.mapper.RealtyMapper;
import com.astar.global.exception.CustomExceptions;
import com.astar.global.exception.ErrorCode;

@Service
public class RealtyService {
    @Autowired RealtyMapper mapper;
    public void insertBrokerInfo(BuildingInfoVO request) {
        String address = request.getBi_address().replaceAll(" ","");
        if(mapper.isExistBuildingAddress(address)){
            throw new CustomExceptions(ErrorCode.REALTY_ADDRESS_DUPICATION, "이미 등록된 주소지입니다.");
        }
        else{
            mapper.insertBuildingInfo(request);
        }
    }

    public void isExistBuildingAddress(String address) {
        address = address.replaceAll(" ","");
        if(mapper.isExistBuildingAddress(address)){
            throw new CustomExceptions(ErrorCode.REALTY_ADDRESS_DUPICATION, "이미 등록된 주소지입니다.");
        }
    }

    public Map<String,Object> selectBuildingInfoList(String keyword, Integer page) {
        Map<String,Object> resultMap = new LinkedHashMap<String,Object>();
        if(keyword==null) {keyword="";}
        if(page==null) {page=1;}
        Integer offset = (page-1)*10;
        resultMap.put("currenPage",page);
        resultMap.put("keword",keyword);
        resultMap.put("pageCnt",mapper.selectBuildingTotalPages(keyword));
        resultMap.put("totalCnt",mapper.selectBuildingTotalCount(keyword));
        resultMap.put("list", mapper.selectBuildingList(keyword, offset));
        return resultMap;
    }

    //확인필요
    public void updateBuildingInfo(BuildingInfoUpdateVO request) {
        if(request.getUpdate_address()) {
            String address = request.getBuilding_info().getBi_address().replaceAll(" ","");
            // if(!mapper.isExistBuildingAddress(address)) {
            //     throw new CustomExceptions(ErrorCode.INVALID_ADDRESS, "존재하지 않는 주소지입니다.");
            // }
        }
        mapper.updateBuildingInfo(request);
    }
    
    public void deleteBuildingInfo(Integer building_seq) {
        if(!mapper.isExistBuildingInfoBySeq(building_seq)) {
            throw new CustomExceptions(ErrorCode.NOT_EXISTED_BUILDING, "해당 건물은 존재하지 않습니다.");
        }
        mapper.deleteBuildingInfo(building_seq);
    }

    public void insertRealtyTotalInfo(RealtyTotalInfoVO request) {
        RealtyOptionInfoVO option = request.getOption_info();
        RealtyBasicInfoVO basic = request.getBasic_info();
        RealtyPostInfoVO post = request.getPost_info();

        mapper.insertRealtyOptionInfo(option);
        basic.setRbi_ro_seq(option.getRo_seq());
        
        mapper.insertRealtyBasicInfo(basic);
        post.setRpi_rbi_seq(basic.getRbi_seq());

        mapper.insertRealtyPostInfo(post);
    }

    public String updateRealtyBasicInfo(RealtyBasicInfoVO data) {
        mapper.updateRealtyBasicInfo(data);
        String msg = "매물 기본 정보가 수정되었습니다.";
        return msg;
    }
    public String updateRealtyOptionInfo(RealtyOptionInfoVO data) {
        mapper.updateRealtyOptionInfo(data);
        String msg = "매물 옵션 정보가 수정되었습니다.";
        return msg;
    }
    public String updateRealtyPostInfo(RealtyPostInfoVO data) {
        mapper.updateRealtyPostInfo(data);
        String msg = "매물 포스트 정보가 수정되었습니다.";
        return msg;
    }
    public String updateRealtyAllInfo(RealtyTotalInfoVO data) {
        mapper.updateRealtyBasicInfo(data.getBasic_info());
        mapper.updateRealtyOptionInfo(data.getOption_info());
        mapper.updateRealtyPostInfo(data.getPost_info());
        String msg = "매물 정보가 수정되었습니다.";
        return msg;
    }

    public void insertRealtyMaintainItem(String name) {
        if(mapper.isExistMaintainItem(name)) {
            throw new CustomExceptions(ErrorCode.EXISTED_MAINTAIN_ITEM, name+"항목은 이미 존재합니다.");
        }
        else{
            mapper.insertMaintainItem(name);
        }
    }

    public List<RealtyMaintainInfoVO> selectMaintainList() {
        List<RealtyMaintainInfoVO> list = mapper.selectMaintainItemList();
        return list;
    }
    
    public void deleteMaintainItem(String name) {
        if(!mapper.isExistMaintainItem(name)) {
            throw new CustomExceptions(ErrorCode.NOT_EXISTED_MAINTAIN_ITEM, name+"항목은 존재하지 않는 관리비 항목입니다.");
        }
        mapper.deleteMaintainItem();
    }
    
    public Map<String,Object> selectPostList(RealtySearchRequestVO request,Integer page) {
        Map<String,Object> resultMap = new LinkedHashMap<String,Object>();
        if(page==null) {page=1;}
        Integer offset = (page-1)*10;
        if(request.getCenter_point_latitude()==null||request.getCenter_point_longitude()==null){
            resultMap.put("msg","위도 경도는 필수 입력값입니다.");
            return resultMap;
        }
        if(request.getCenter_point_radius()==null){
            request.setCenter_point_radius(3.0);
            //사용자 지도로부터 거리 상한선 필요
        }
        resultMap.put("currentPage",page);
        resultMap.put("searchRequest",request);
        resultMap.put("searchResult",mapper.selectPostList(request,offset));
        resultMap.put("totalCnt",mapper.selectPostCnt(request));
        resultMap.put("pageCnt",mapper.selectPostPageCnt(request));
        return resultMap;
    }

    public Map<String,Object> selectPostInfoBySeq(Integer seq) {
        Map<String,Object> resultMap = new LinkedHashMap<String,Object>();
        mapper.selectPostInfoBySeq(seq);
        return resultMap;
    }
}

