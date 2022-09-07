package com.astar.realty.mapper;

import org.apache.ibatis.annotations.Mapper;

import com.astar.realty.data.BrokerInfoVO;
import com.astar.realty.data.BrokerLoginVO;
import com.astar.realty.data.LoginSessionVO;

@Mapper
public interface BrokerMapper {
    public void insertBrokerInfo(BrokerInfoVO data);
    public Boolean isExistBrokerId(String id);
    public Boolean loginBroker(BrokerLoginVO login);
    public LoginSessionVO getLoginBrokerInfo(BrokerLoginVO login);
    public void updateBrokerInfo(BrokerInfoVO data);

    public Integer selectBrokerStatus(Integer user_no);
    public void updateBrokerStatus(Integer user_no, Integer status);
    
}
