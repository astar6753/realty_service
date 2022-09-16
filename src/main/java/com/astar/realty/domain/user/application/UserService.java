package com.astar.realty.domain.user.application;

import java.util.Calendar;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.astar.realty.domain.user.data.UserCreate;
import com.astar.realty.domain.user.data.UserLogin;
import com.astar.realty.domain.user.data.UserModify;
import com.astar.realty.domain.user.data.UserRealtyLikeVO;
import com.astar.realty.domain.user.data.UserRealtyLookupVO;
import com.astar.realty.domain.user.mapper.UserMapper;
import com.astar.realty.global.exception.CustomExceptions;
import com.astar.realty.global.exception.ErrorCode;
import com.astar.realty.global.util.AESAlgorithm;

@Service
public class UserService {
    @Autowired UserMapper mapper;

    public Boolean createUserInfo(UserCreate.Request request){
        if(mapper.isExistUserAccountId(request.getUi_id())) {
            throw new CustomExceptions(ErrorCode.ID_DUPLICATTION,request.getUi_id()+"은/는 이미 가입된 아이디입니다.");
        }
        else {
            try {
                request.setUi_pwd(AESAlgorithm.Encrypt(request.getUi_pwd()));
            } catch (Exception e) {
                throw new CustomExceptions(ErrorCode.AES_ALGORITHM_ERROR,"AES 암호화 오류.");
            }

            int result = mapper.insertUserCreateInfo(request);
            if(result==1) {return true;}
            else {return false;}
        }
    }
    public Boolean checkUserIdDuplication(String id){
        if(mapper.isExistUserAccountId(id)) {
            throw new CustomExceptions(ErrorCode.ID_DUPLICATTION,id+"은/는 이미 가입된 아이디입니다.");
        }
        else{return false;}
    }
    
    public UserLogin.Result makeUserLogin(UserLogin.Request request, HttpSession session){
        try {
            request.setPwd(AESAlgorithm.Encrypt(request.getPwd()));
        } catch (Exception e) {
            throw new CustomExceptions(ErrorCode.AES_ALGORITHM_ERROR,"AES 암호화 오류.");
        }
        if(!mapper.isValidUserLoginInfo(request)) {
            throw new CustomExceptions(ErrorCode.INCORRECT_LOGIN_ARGUMENT,"아이디 혹은 비밀번호를 확인해주세요.");
        }
        else{
            UserLogin.Result result = mapper.selectUserLoginInfo(request);

            // session // SPRING-BOOT-FE
            session.setAttribute("sessionInfo", result);
            return result;
        }
    }    
    // session // SPRING-BOOT-FE
    public void makeUserLogout(HttpSession session) {
        session.invalidate();
    }

    public Boolean modifyUserInfoBySeq(UserModify.Request request){
        if(!mapper.isExistUserAccountId(request.getUi_id())){
            throw new CustomExceptions(ErrorCode.NOT_EXISTED_USER_ID,"존재하지 않는 ID입니다.");
        }
        else if(!mapper.isExistUserInfoBySeq(request.getUi_seq())){
            throw new CustomExceptions(ErrorCode.NOT_EXISTED_USER_NO,"존재하지 않는 서비스 회원 번호입니다.");
        }
        else{
            try {
                request.setUi_pwd(AESAlgorithm.Encrypt(request.getUi_pwd()));
            } catch (Exception e) {
                throw new CustomExceptions(ErrorCode.AES_ALGORITHM_ERROR,"AES 암호화 오류.");
            }
            int result = mapper.updateUserInfoBySeq(request);
            if(result==1) {return true;}
            else {return false;}
        }
    }

    public Map<String,Object> modifyUserStatus(String id, Integer status){
        Map<String,Object> map = new LinkedHashMap<String,Object>();
        if(mapper.isExistUserAccountId(id)){
            if(0<status&&status<=5){
                mapper.updateUserStatus(id, status);
            }
            else{
                //validation range
                map.put("msg","잘못된 회원 상태 값입니다. (1:정상/2:정지/3:탈퇴대기/4:탈퇴/5:영구정지)");
            }
        }
        return map;
    }

    public Boolean toggleUserLikeInfo(UserRealtyLikeVO request){
        if(!mapper.isExistUserRealtyLikeInfo(request)){
            if(mapper.insertUserRealtyLikeInfo(request)==1){
                return true;    // created 사용자 좋아요 정보 등록
            }
            else{
                return false;   // insert 실패
            }            
        }
        else{
            if(mapper.deleteUserRealtyLikeInfo(request)==1){
                return true;    // no content 사용자 좋아요 정보 삭제
            }
            else{
                return false;    // delete 실패
            }
        }

        // UserRealtyLike.Result {status,create/remove, msg} > UserRealtyLike.Response
    }
    
    public Map<String,Object> createUserRealtyLookupInfo(UserRealtyLookupVO data){
        Map<String,Object> resultMap = new LinkedHashMap<String,Object>();

        //가장 최근 검색 이력 시간 조회 > Calendar타입 형변환
        Calendar now = Calendar.getInstance();

        Date latestDt = mapper.selectLatestRealtyLookupTime(data);
        
        Calendar latestTime = Calendar.getInstance();
        try {
            latestTime.setTime(latestDt);    
        } catch (NullPointerException e) {
            mapper.insertUserRealtyLookupInfo(data);
            // Created 매물 조회 이력이 추가되었습니다.
            // latestDt가 null이면 첫번재 조회
        }        
        
        Long diff = now.getTimeInMillis() - latestTime.getTimeInMillis();

        if(diff >=30000){
            mapper.insertUserRealtyLookupInfo(data);
            // Created 매물 조회 이력이 추가되었습니다.
        }
        else{
            // OK 최근 조회 시간과 30초 이내입니다.
        }
        return resultMap;
    }
    
    public Map<String,Object> deleteUserInfo(Integer seq){
        Map<String,Object> resultMap = new LinkedHashMap<String,Object>();
        return resultMap;
    }
}
