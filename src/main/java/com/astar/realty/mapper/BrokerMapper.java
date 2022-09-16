package com.astar.realty.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.astar.realty.data.broker.BrokerLogin;
import com.astar.realty.data.broker.BrokerModify;
import com.astar.realty.data.brokerOffice.BrokerOfficeCreate;
import com.astar.realty.data.brokerOffice.BrokerOfficeInfoVO;
import com.astar.realty.data.brokerOffice.BrokerOfficeModify;
import com.astar.realty.data.broker.BrokerCreate;

@Mapper
public interface BrokerMapper {
    //BrokerCreate
    public int insertBrokerCreateInfo(BrokerCreate.Request request);
    public Boolean isExistBrokerAccountId(String id);

    //BrokerLogin
    public Boolean isValidBrokerLoginInfo(BrokerLogin.Request request);
    public BrokerLogin.Result selectBrokerLoginInfo(BrokerLogin.Request request);

    //BrokerModifyInfoById
    public int updateBrokerInfoById(BrokerModify.Request request);
    public Boolean isExistBrokerOfficeBySeq(Integer seq);

    //BrokerModifyStatus
    public Integer selectBrokerStatus(Integer broker_no);
    public void updateBrokerStatus(Integer broker_no, Integer status);

    //BrokerOfficeCreate
    public int insertBrokerOfficeCreateInfo(BrokerOfficeCreate.Request request);
    public Boolean isExistBrokerOffice(String name, String reg_no);

    //BrokerOfiiceSearchList
    public Integer selectBrokerOfficeTotalCount(String keyword);
    public Integer selectBrokerOfficePageCount(String keyword, Integer limit);
    public List<BrokerOfficeInfoVO> selectBrokerOfficeList(String keyword, Integer limit, Integer offset);

    //BrokerOfiiceModify
    public int updateBrokerOfficeInfo(BrokerOfficeModify.Request request);
    
    //BrokerOfiiceRemove
    public int deleteBrokerOfficeBySeq(Integer office_no);

    public BrokerOfficeInfoVO selectBrokerOfficeBySeq(Integer office_no);

}
