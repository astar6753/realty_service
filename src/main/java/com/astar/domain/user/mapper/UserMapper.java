package com.astar.domain.user.mapper;

import java.util.Date;

import org.apache.ibatis.annotations.Mapper;

import com.astar.domain.user.data.UserCreate;
import com.astar.domain.user.data.UserLogin;
import com.astar.domain.user.data.UserModify;
import com.astar.domain.user.data.UserRealtyLikeVO;
import com.astar.domain.user.data.UserRealtyLookupVO;

@Mapper
public interface UserMapper {

    //UserCreate
    public int insertUserCreateInfo(UserCreate.Request request);
    public Boolean isExistUserAccountId(String id);

    //UserLogin
    public Boolean isValidUserLoginInfo(UserLogin.Request request);
    public UserLogin.Result selectUserLoginInfo(UserLogin.Request request);

    //UserModifyBySeq
    public int updateUserInfoBySeq(UserModify.Request request);
    public Boolean isExistUserInfoBySeq(Integer seq);

    //UserStatusModify
    public void updateUserStatus(String id, Integer status);

    //UserLikeToggle
    public int insertUserRealtyLikeInfo(UserRealtyLikeVO request);
    public int deleteUserRealtyLikeInfo(UserRealtyLikeVO request);
    public Boolean isExistUserRealtyLikeInfo(UserRealtyLikeVO request);

    //UserLookup
    public void insertUserRealtyLookupInfo(UserRealtyLookupVO data);
    public Date selectLatestRealtyLookupTime(UserRealtyLookupVO data);


}
