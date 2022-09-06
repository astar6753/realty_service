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
}
