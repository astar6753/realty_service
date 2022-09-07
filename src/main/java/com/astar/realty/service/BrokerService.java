package com.astar.realty.service;


import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.astar.realty.data.BrokerInfoVO;
import com.astar.realty.data.BrokerLoginVO;
import com.astar.realty.data.LoginSessionVO;
import com.astar.realty.exception.CustomExceptions;
import com.astar.realty.exception.ErrorCode;
import com.astar.realty.mapper.BrokerMapper;
import com.astar.realty.utils.AESAlgorithm;

@Service
public class BrokerService {
    @Autowired BrokerMapper mapper;
    public void insertBrokerInfo(BrokerInfoVO request) {
        if(mapper.isExistBrokerId(request.getBork_id())) {
            throw new CustomExceptions(ErrorCode.ID_DUPLICATTION,request.getBork_id()+"은/는 이미 가입된 아이디입니다.");
        }
        else if(request.getBork_pwd()==null||request.getBork_pwd().length()<6){
            throw new CustomExceptions(ErrorCode.INVALID_PWD,"비밀번호는 6자 이상 입력해 주세요");
        }
        else {
            try {
                request.setBork_pwd(AESAlgorithm.Encrypt(request.getBork_pwd()));
            } catch (Exception e) {
                throw new CustomExceptions(ErrorCode.AES_ALGORITHM_ERROR,"AES 암호화 오류");
            }
            mapper.insertBrokerInfo(request);
        }
    }

    public Boolean isExistBrokerId(String id) {
        Boolean result;
        if(mapper.isExistBrokerId(id)) {
            result = true;
            throw new CustomExceptions(ErrorCode.ID_DUPLICATTION,id+"은/는 이미 가입된 아이디입니다.");
        }
        else{result = false;}
        return result;
    }

    public LoginSessionVO loginBroker(BrokerLoginVO login, HttpSession session) {
        
        if(login.getId()==null||login.getId().length()<=0){
            throw new CustomExceptions(ErrorCode.INVALID_ID,"ID를 입력해 주세요");
        }
        else if(login.getPwd()==null||login.getPwd().length()<=0){
            throw new CustomExceptions(ErrorCode.INVALID_PWD,"비밀번호는 6자 이상 입력해 주세요");
        }
        else{
            try {
                login.setPwd(AESAlgorithm.Encrypt(login.getPwd()));
            } catch (Exception e) {
                throw new CustomExceptions(ErrorCode.AES_ALGORITHM_ERROR,"AES 암호화 오류");
            }
        }

        if(!mapper.loginBroker(login)) {
            throw new CustomExceptions(ErrorCode.LOGIN_INPUT_INVALID,"아이디 혹은 비밀번호를 확인해주세요");
        }
        else{
            LoginSessionVO loginUser = mapper.getLoginBrokerInfo(login);

            // session // SPRING-BOOT-FE
            session.setAttribute("sessionInfo", loginUser);
            return loginUser;
        }
    }

    // session // SPRING-BOOT-FE
    public void logoutBroker(HttpSession session) {
        session.invalidate();
    }

    public void updateBrokerInfo(BrokerInfoVO request) {
        if(!mapper.isExistBrokerId(request.getBork_id())){
            throw new CustomExceptions(ErrorCode.INVALID_ID,"존재하지 않는 ID입니다");
        }
        else if(request.getBork_pwd()==null||request.getBork_pwd().length()<6){
            throw new CustomExceptions(ErrorCode.INVALID_PWD,"비밀번호는 6자 이상 입력해 주세요");
        }
        else{
            try {
                request.setBork_pwd(AESAlgorithm.Encrypt(request.getBork_pwd()));
            } catch (Exception e) {
                throw new CustomExceptions(ErrorCode.AES_ALGORITHM_ERROR,"AES 암호화 오류");
            }
            mapper.updateBrokerInfo(request);
        }
    }

    public String updateBrokerStatus(String value, Integer status, Integer user_no, HttpSession session) {
        String[] arr_msg= {
            "탈퇴 철회가 완료되었습니다.",
            "중개인 정지 처리가 완료되었습니다.",
            "탈퇴 신청이 완료되었습니다.",
            "중개인 즉시 탈퇴 처리가 완료되었습니다.",
        };
        Integer current = mapper.selectBrokerStatus(user_no);
        String msg;
        if(current==status) {
            throw new CustomExceptions(ErrorCode.INVALID_STATUS,"현재 status와 입력한 status가 동일합니다. (current:"+current+"incoming:"+status+")");
        }
        if(value.equals("broker")) {
            if(status==1||status==3){
                msg = arr_msg[status-1];
            }
            else{
                throw new CustomExceptions(ErrorCode.INVALID_STATUS,"중개인 계정은 탈퇴 및 탈퇴 철회만 가능합니다.");    
            }
        }
        else if(value.equals("admin")){
            if(status<=4 && status>0){
                msg = arr_msg[status-1];
            }
            else{
                throw new CustomExceptions(ErrorCode.INVALID_STATUS,"잘못된 요청입니다. (usage: status:[1,2,3,4])");
            }
        }
        else{
            throw new CustomExceptions(ErrorCode.INVALID_VALUE,"잘못된 요청입니다. (usage: /api/broker/[broker,admin])");
        }
        mapper.updateBrokerStatus(user_no, status);
        return msg;
    }
}
