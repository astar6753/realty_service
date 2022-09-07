package com.astar.realty.service;

import java.util.LinkedHashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.astar.realty.data.BuildingInfoVO;
import com.astar.realty.data.RealtyBasicInfoVO;
import com.astar.realty.data.RealtyOptionInfoVO;
import com.astar.realty.data.RealtyPostInfoVO;
import com.astar.realty.data.RealtyTotalInfoVO;
import com.astar.realty.exception.CustomExceptions;
import com.astar.realty.exception.ErrorCode;
import com.astar.realty.mapper.RealtyMapper;

@Service
public class RealtyService {
    @Autowired RealtyMapper mapper;
    public void insertBrokerInfo(BuildingInfoVO request) {
        String address = request.getBi_address().replaceAll(" ","");
        if(mapper.isExistBuilding(address)){
            throw new CustomExceptions(ErrorCode.REALTY_ADDRESS_DUPICATION, "이미 등록된 주소지입니다.");
        }
        else{
            mapper.insertBuildingInfo(request);
        }
    }

    public void isExistBuildingAddress(String address) {
        address = address.replaceAll(" ","");
        if(mapper.isExistBuilding(address)){
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
}

