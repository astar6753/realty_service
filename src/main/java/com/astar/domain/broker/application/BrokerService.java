package com.astar.domain.broker.application;


import java.util.List;

import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.astar.domain.broker.data.BrokerCreate;
import com.astar.domain.broker.data.BrokerLogin;
import com.astar.domain.broker.data.BrokerModify;
import com.astar.domain.broker.data.BrokerOfficeCreate;
import com.astar.domain.broker.data.BrokerOfficeInfoVO;
import com.astar.domain.broker.data.BrokerOfficeModify;
import com.astar.domain.broker.data.BrokerOfficeSearch;
import com.astar.domain.broker.mapper.BrokerMapper;
import com.astar.global.common.Pagination;
import com.astar.global.exception.CustomExceptions;
import com.astar.global.exception.ErrorCode;
import com.astar.global.util.AESAlgorithm;

@Service
public class BrokerService {
    @Autowired BrokerMapper mapper;

    public Boolean createBroker(BrokerCreate.Request request) {
        if(mapper.isExistBrokerAccountId(request.getBork_id())) {
            throw new CustomExceptions(ErrorCode.ID_DUPLICATTION,request.getBork_id()+"은/는 이미 가입된 아이디입니다.");
        }
        else {
            try {
                request.setBork_pwd(AESAlgorithm.Encrypt(request.getBork_pwd()));
            } catch (Exception e) {
                throw new CustomExceptions(ErrorCode.AES_ALGORITHM_ERROR,"AES 암호화 오류.");
            }

            if(mapper.insertBrokerCreateInfo(request)==1) {return true;}
            else {return false;}
        }
    }

    public Boolean checkBrokerIdDuplication(String id) {
        if(mapper.isExistBrokerAccountId(id)) {
            throw new CustomExceptions(ErrorCode.ID_DUPLICATTION,id+"은/는 이미 가입된 아이디입니다.");
        }
        else{return false;}
    }

    public BrokerLogin.Result loginBroker(BrokerLogin.Request request, HttpSession session) {
        try {
            request.setPwd(AESAlgorithm.Encrypt(request.getPwd()));
        } catch (Exception e) {
            throw new CustomExceptions(ErrorCode.AES_ALGORITHM_ERROR,"AES 암호화 오류.");
        }
        if(!mapper.isValidBrokerLoginInfo(request)) {
            throw new CustomExceptions(ErrorCode.INCORRECT_LOGIN_ARGUMENT,"아이디 혹은 비밀번호를 확인해주세요.");
        }
        else{
            BrokerLogin.Result result = mapper.selectBrokerLoginInfo(request);

            // session // SPRING-BOOT-FE
            session.setAttribute("sessionInfo", result);
            return result;
        }
    }

    // session // SPRING-BOOT-FE
    public void logoutBroker(HttpSession session) {
        session.invalidate();
    }

    public Boolean modifyBrokerInfoById(BrokerModify.Request request) {
        if(!mapper.isExistBrokerAccountId(request.getBork_id())){
            throw new CustomExceptions(ErrorCode.INVALID_ID,"존재하지 않는 ID입니다.");
        }
        else if(!mapper.isExistBrokerOfficeBySeq(request.getBork_boi_seq())){
            throw new CustomExceptions(ErrorCode.INVALID_BROKER_OFFICE,"존재하지 않는 공인중개사 사무소입니다.");
        }
        else{
            try {
                request.setBork_pwd(AESAlgorithm.Encrypt(request.getBork_pwd()));
            } catch (Exception e) {
                throw new CustomExceptions(ErrorCode.AES_ALGORITHM_ERROR,"AES 암호화 오류.");
            }
            if(mapper.updateBrokerInfoById(request)==1) {return true;}
            else {return false;}
        }
    }

    //##보류
    public String modifyBrokerStatus(Integer status, String grade, Integer broker_no, HttpSession session) {
        String[] arr_msg= {
            "탈퇴 철회가 완료되었습니다.",
            "중개인 정지 처리가 완료되었습니다.",
            "탈퇴 신청이 완료되었습니다.",
            "중개인 즉시 탈퇴 처리가 완료되었습니다.",
        };
        Integer current = mapper.selectBrokerStatus(broker_no);
        String msg;
        if(grade.equals("broker")) {
            if(current==status) {
                throw new CustomExceptions(ErrorCode.INVALID_STATUS,"현재 status와 입력한 status가 동일합니다. (current:"+current+"incoming:"+status+")");
            }
            else if(status==1||status==3){
                msg = arr_msg[status-1];
            }
            else{
                throw new CustomExceptions(ErrorCode.INVALID_STATUS,"중개인 계정은 탈퇴 및 탈퇴 철회만 가능합니다.");
            }
        }
        else if(grade.equals("admin")){
            if(current==status) {
                throw new CustomExceptions(ErrorCode.INVALID_STATUS,"현재 status와 입력한 status가 동일합니다. (current:"+current+"incoming:"+status+")");
            }
            else if(status<=4 && status>0){
                msg = arr_msg[status-1];
            }
            else{
                throw new CustomExceptions(ErrorCode.INVALID_STATUS,"잘못된 요청입니다. (usage: status:[1,2,3,4])");
            }
        }
        else{
            throw new CustomExceptions(ErrorCode.INVALID_VALUE,"잘못된 요청입니다. (usage: /api/broker/[broker,admin])");
        }
        mapper.updateBrokerStatus(broker_no, status);
        return msg;
    }

    public Boolean createBrokerOffice(BrokerOfficeCreate.Request request){
        String name = request.getBoi_name().replaceAll(" ","");
        if(mapper.isExistBrokerOffice(name,request.getBoi_reg_number())) { 
            throw new CustomExceptions(ErrorCode.BROKER_OFFICE_DUPLICATION,"이미 등록된 사업장 상호명 혹은 사업장 등록번호입니다.");
        }
        else{
            int result = mapper.insertBrokerOfficeCreateInfo(request);
            if(result==1){return true;}
            else{return false;}
        }
    }

    public Boolean checkBrokerOfficeExist(String name, String reg_no) {
        name = name.replaceAll(" ","");
        if(mapper.isExistBrokerOffice(name,reg_no)) { 
            throw new CustomExceptions(ErrorCode.BROKER_OFFICE_DUPLICATION,"이미 등록된 사업장 상호명 혹은 사업장 등록번호입니다.");
        }
        else{
            return true;
        }
    }

    public BrokerOfficeSearch.Result searchBrokerOfficeInfoList(BrokerOfficeSearch.Request request) {
        Integer page = 1;
        if(request.getPagination().getPage()!=null){page=request.getPagination().getPage();}
        String keyword = "";
        if(request.getKeyword()!=null){keyword=request.getKeyword();}
        Integer limit = 10;
        if(request.getPagination().getLimit()!=null){limit=request.getPagination().getLimit();}
        Integer offset = (page-1)*limit;

        
        Integer totalCnt = mapper.selectBrokerOfficeTotalCount(keyword);
        Integer pageCnt = mapper.selectBrokerOfficePageCount(keyword,limit);
        Pagination.Response pagination = new Pagination.Response(page, pageCnt, totalCnt);
        List<BrokerOfficeInfoVO> searchResult = mapper.selectBrokerOfficeList(keyword, limit, offset);
        BrokerOfficeSearch.Result result = new BrokerOfficeSearch.Result(keyword, searchResult, pagination);
        return result;
    }
    
    public Boolean modifyBrokerOfficeInfo(BrokerOfficeModify.Request request) {
        if(!mapper.isExistBrokerOfficeBySeq(request.getBoi_seq())){
            throw new CustomExceptions(ErrorCode.NOT_EXISTED_OFFICE, "해당 중개사무소는 존재하지 않습니다.");
        }
        else{
            if(mapper.updateBrokerOfficeInfo(request)==1){
                return true;
            }
            else{   // ##로그처리 필요
                throw new CustomExceptions(ErrorCode.OFFICE_MODIFICATION_FAILED, "중개사무소 수정이 실패하였습니다.");
            }
        }
    }

    public Boolean removeBrokerOfficeBySeq(Integer office_no) {
        if(!mapper.isExistBrokerOfficeBySeq(office_no)) {
            throw new CustomExceptions(ErrorCode.NOT_EXISTED_OFFICE, "해당 중개사무소는 존재하지 않습니다.");
        }
        else{
            if(mapper.deleteBrokerOfficeBySeq(office_no)==1){
                return true;
            }
            else{   // ##로그처리 필요
                throw new CustomExceptions(ErrorCode.OFFICE_DELETION_FAILED, "중개사무소 삭제가 실패하였습니다.");
            }
        }
    }
    
    public BrokerOfficeInfoVO findBrokerOfficeInfoBySeq(Integer office_no){
        BrokerOfficeInfoVO result = mapper.selectBrokerOfficeBySeq(office_no);
        if(result!=null) {
            return result;
        }
        throw new CustomExceptions(ErrorCode.NOT_EXISTED_OFFICE, "해당 중개사무소는 존재하지 않습니다.");
    }

}