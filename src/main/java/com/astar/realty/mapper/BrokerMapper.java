package com.astar.realty.mapper;

import org.apache.ibatis.annotations.Mapper;

import com.astar.realty.data.BrokerInfoVO;
import com.astar.realty.data.BrokerLoginVO;
import com.astar.realty.data.BrokerOfficeInfoVO;
import com.astar.realty.data.LoginSessionVO;
import com.astar.realty.data.request.BrokerDTO;

@Mapper
public interface BrokerMapper {
    public int insertBrokerInfo(BrokerDTO.Request request);
    public Boolean isExistBrokerId(String id);
    public Boolean loginBroker(BrokerLoginVO login);
    public LoginSessionVO getLoginBrokerInfo(BrokerLoginVO login);
    public void updateBrokerInfo(BrokerInfoVO data);

    public Integer selectBrokerStatus(Integer user_no);
    public void updateBrokerStatus(Integer user_no, Integer status);
    
    public void insertBrokerOfficeInfo(BrokerOfficeInfoVO data);
    public Boolean isExistBrokerOffice(String name, Integer reg_no);

    public Integer selectBrokerOfficeTotalPages(String keyword);
    public Integer selectBrokerOfficeTotalCount(String keyword);
    public BrokerOfficeInfoVO selectBrokerOfficeList(String keyword, Integer offset);

    public int updateBrokerOfficeInfo(BrokerOfficeInfoVO data);
    public int deleteBrokerOfficeInfo(Integer no);
    public BrokerOfficeInfoVO selectBrokerOfficeBySeq(Integer no);

}
