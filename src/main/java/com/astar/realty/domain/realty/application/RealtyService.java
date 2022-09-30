package com.astar.realty.domain.realty.application;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.astar.realty.domain.realty.data.BuildingCreate;
import com.astar.realty.domain.realty.data.BuildingInfoVO;
import com.astar.realty.domain.realty.data.BuildingModify;
import com.astar.realty.domain.realty.data.BuildingSearch;
import com.astar.realty.domain.realty.data.RealtyBasicInfoVO;
import com.astar.realty.domain.realty.data.RealtyMaintainInfoVO;
import com.astar.realty.domain.realty.data.RealtyMaintainShow;
import com.astar.realty.domain.realty.data.RealtyOptionInfoVO;
import com.astar.realty.domain.realty.data.RealtyPostCreate;
import com.astar.realty.domain.realty.data.RealtyPostInfoVO;
import com.astar.realty.domain.realty.data.RealtyPostModify;
import com.astar.realty.domain.realty.data.RealtyPostSearch;
import com.astar.realty.domain.realty.data.RealtyPostSearchVO;
import com.astar.realty.domain.realty.data.RealtyPostViewVO;
import com.astar.realty.domain.realty.mapper.RealtyMapper;
import com.astar.realty.global.common.Pagination;
import com.astar.realty.global.exception.CustomExceptions;
import com.astar.realty.global.exception.ErrorCode;

@Service
public class RealtyService {
    @Autowired RealtyMapper mapper;

    public Boolean createBuildingInfo(BuildingCreate.Request request) {
        String noneSpacingAddress = request.getAddress().replaceAll(" ","");
        if(mapper.isExistBuildingAddress(noneSpacingAddress)){
            throw new CustomExceptions(ErrorCode.BUILDING_ADDRESS_DUPICATION, "이미 등록된 주소지입니다.");
        }
        else{
            if(mapper.insertBuildingInfo(request.toEntity())==1) {return true;}
            else {return false;}
        }
    }

    public Boolean checkBuildingDuplication(String address) {
        String noneSpacingAddress = address.replaceAll(" ","");
        if(mapper.isExistBuildingAddress(noneSpacingAddress)){
            throw new CustomExceptions(ErrorCode.BUILDING_ADDRESS_DUPICATION, "이미 등록된 주소지입니다.");
        }
        else {
            return true;
        }
    }

    public BuildingSearch.Result searchBuildingInfoList(BuildingSearch.Request request) {
        Integer page = 1;
        if(request.getPagination().getPage()!=null) {page=request.getPagination().getPage();}
        String keyword = "";
        if(request.getKeyword()!=null) {keyword=request.getKeyword();}
        Integer limit = 10;
        if(request.getPagination().getLimit()!=null) {limit=request.getPagination().getLimit();}
        Integer offset = (page-1)*limit;

        Integer pageCnt = mapper.selectBuildingPageCount(keyword, limit);
        Integer totalCnt = mapper.selectBuildingTotalCount(keyword);
        Pagination.Response pagination = new Pagination.Response(page, pageCnt, totalCnt);
        List<BuildingInfoVO> searchResult = mapper.selectBuildingList(keyword, limit, offset);
        BuildingSearch.Result result = new BuildingSearch.Result(keyword, searchResult, pagination);
        return result;
    }

    public Boolean modifyBuildingInfo(BuildingModify.Request request) {
        if(request.getUpdate_address()) {
            String noneSpacingAddress = request.getBuilding_info().getBi_address().replaceAll(" ","");
            if(!mapper.isExistBuildingAddress(noneSpacingAddress)) {
                throw new CustomExceptions(ErrorCode.NOT_EXIST_BUILDING_ADDRESS, "존재하지 않는 주소지입니다.");
            }
        }        
        if(!mapper.isExistBuildingInfoBySeq(request.getBuilding_info().getBi_seq())) {
            throw new CustomExceptions(ErrorCode.NOT_EXIST_BUILDING_NO, "존재하지 않는 건물 번호입니다.");
        }
        if(mapper.updateBuildingInfo(request)==1) {return true;}
        else {return false;}        
    }
    
    public Boolean removeBuildingInfoBySeq(Integer building_no) {
        if(!mapper.isExistBuildingInfoBySeq(building_no)) {
            throw new CustomExceptions(ErrorCode.NOT_EXIST_BUILDING_NO, "존재하지 않는 건물 번호입니다.");
        }
        if(mapper.deleteBuildingInfo(building_no)==1){return true;}
        else{return false;}
    }

    @Transactional
    public Boolean createRealtyPostInfo(RealtyPostCreate.Request request) {
        RealtyOptionInfoVO option = request.getOption_info();
        RealtyBasicInfoVO basic = request.getBasic_info();
        RealtyPostInfoVO post = request.getPost_info();

        mapper.insertRealtyOptionInfo(option);

        basic.setRbi_ro_seq(option.getRo_seq());
        if(!mapper.isExistBuildingInfoBySeq(basic.getRbi_bi_seq())) {
            throw new CustomExceptions(ErrorCode.NOT_EXIST_BUILDING_NO, "존재하지 않는 건물 번호입니다.");
        }

        mapper.insertRealtyBasicInfo(basic);
        post.setRpi_rbi_seq(basic.getRbi_seq());

        mapper.insertRealtyPostInfo(post);
        return true;
    }

    @Transactional
    public Boolean modifyRealtyPostTotalInfo(String type, RealtyPostModify.Request request) {
        if(type.equals("basic")) {
            return modifyRealtyBasicInfo(request.getBasic_info());
        }
        else if(type.equals("option")){
            return modifyRealtyOptionInfo(request.getOption_info());
        }
        else if(type.equals("post")) {
            return modifyRealtyPostInfo(request.getPost_info());
        }
        else if(type.equals("all")) {
            if(!modifyRealtyBasicInfo(request.getBasic_info())) {return false;}
            else if(!modifyRealtyOptionInfo(request.getOption_info())) {return false;}
            else if(!modifyRealtyPostInfo(request.getPost_info())) {return false;}
            else{return true;}
        }
        else{
            throw new CustomExceptions(ErrorCode.INVALID_TYPE, "타입 정보가 잘못되었습니다.");
        }
    }

    public Boolean modifyRealtyBasicInfo(RealtyBasicInfoVO basic) {
        if(!mapper.isExistBuildingInfoBySeq(basic.getRbi_bi_seq())) {
            throw new CustomExceptions(ErrorCode.NOT_EXIST_BUILDING_NO, "존재하지 않는 건물 번호입니다.");
        }
        else if(!mapper.isExistRealtyOptionInfoBySeq(basic.getRbi_ro_seq())) {
            throw new CustomExceptions(ErrorCode.NOT_EXIST_OPTION_NO, "존재하지 않는 옵션 정보 번호입니다.");
        }
        else if(mapper.updateRealtyBasicInfo(basic)==1){return true;}
        else{return false;}
    }
    public Boolean modifyRealtyOptionInfo(RealtyOptionInfoVO option) {
        if(!mapper.isExistRealtyOptionInfoBySeq(option.getRo_seq())) {
            throw new CustomExceptions(ErrorCode.NOT_EXIST_OPTION_NO, "존재하지 않는 옵션 정보 번호입니다.");
        }
        else if(mapper.updateRealtyOptionInfo(option)==1){return true;}
        else{return false;}
    }
    public Boolean modifyRealtyPostInfo(RealtyPostInfoVO post) {
        if(!mapper.isExistRealtyPostInfoBySeq(post.getRpi_seq())) {
            throw new CustomExceptions(ErrorCode.NOT_EXIST_POST_NO, "존재하지 않는 매물 게시글 정보 번호입니다.");
        }
        if(mapper.updateRealtyPostInfo(post)==1){return true;}
        else{return false;}
    }

    public Boolean createRealtyMaintainInfo(String name) {
        if(mapper.isExistMaintainName(name)) {
            throw new CustomExceptions(ErrorCode.MAINTAIN_NAME_DUPLICATION, name+"항목은 이미 존재합니다.");
        }
        else{
            if(mapper.insertMaintainItem(name)==1){return true;}
            else{return false;}
        }
    }

    public RealtyMaintainShow.Result showMaintainList() {
        Integer totalCount = mapper.selectMaintainItemCount();
        List<RealtyMaintainInfoVO> list = mapper.selectMaintainItemList();
        return new RealtyMaintainShow.Result(totalCount, list);
    }
    
    public Boolean removeMaintainItemBySeq(Integer maintain_no) {
        if(!mapper.isExistMaintainItemBySeq(maintain_no)) {
            throw new CustomExceptions(ErrorCode.NOT_EXISTED_MAINTAIN_ITEM, "항목은 존재하지 않는 관리비 항목입니다.");
        }
        if(mapper.deleteMaintainItemBySeq(maintain_no)==1){return true;}
        else{return false;}
    }
    
    public RealtyPostSearch.Result searchRealtyPostList(RealtyPostSearch.Request request) {
        Integer page = 1;
        if(request.getPagination().getPage()!=null){page=request.getPagination().getPage();}
        Integer limit = 10;
        if(request.getPagination().getLimit()!=null){limit=request.getPagination().getLimit();}
        Integer offset = (page-1)*limit;
        
        RealtyPostSearchVO searchKeywords = request.getSearchKeywords();
        if(searchKeywords.getCenter_point_latitude()==null) {
            searchKeywords.setCenter_point_latitude(35.868293);
        }
        if(searchKeywords.getCenter_point_longitude()==null) {
            searchKeywords.setCenter_point_longitude(128.594018);
        }
        if(searchKeywords.getCenter_point_radius()==null) {
            searchKeywords.setCenter_point_radius(3.0);
        }

        Integer pageCnt = mapper.selectRealtyPostPageCount(searchKeywords, limit);
        Integer totalCnt = mapper.selectRealtyPostTotalCount(searchKeywords);
        Pagination.Response pagination = new Pagination.Response(page, pageCnt, totalCnt);
        List<RealtyPostViewVO> searchResult = mapper.selectRealtyPostList(searchKeywords, limit, offset);
        RealtyPostSearch.Result result = new RealtyPostSearch.Result(searchKeywords, searchResult, pagination);
        return result;
    }

    public RealtyPostViewVO findRealtyPostBySeq(Integer post_no) {
        if(!mapper.isExistRealtyPostInfoBySeq(post_no)) {
            throw new CustomExceptions(ErrorCode.NOT_EXIST_POST_NO, "존재하지 않는 게시물 번호입니다.");
        }
        return mapper.selectRealtyPostInfoBySeq(post_no);
    }
}

