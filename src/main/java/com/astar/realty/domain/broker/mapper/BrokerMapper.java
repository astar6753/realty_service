package com.astar.realty.domain.broker.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.astar.realty.domain.broker.data.BrokerCreate;
import com.astar.realty.domain.broker.data.BrokerLogin;
import com.astar.realty.domain.broker.data.BrokerModify;
import com.astar.realty.domain.broker.data.BrokerOfficeCreate;
import com.astar.realty.domain.broker.data.BrokerOfficeInfoVO;
import com.astar.realty.domain.broker.data.BrokerOfficeModify;

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
    public Integer selectBrokerOfficePageCount(String keyword, Integer limit);
    public Integer selectBrokerOfficeTotalCount(String keyword);
    public List<BrokerOfficeInfoVO> selectBrokerOfficeList(String keyword, Integer limit, Integer offset);

    //BrokerOfiiceModify
    public int updateBrokerOfficeInfo(BrokerOfficeModify.Request request);
    
    //BrokerOfiiceRemove
    public int deleteBrokerOfficeBySeq(Integer office_no);

    public BrokerOfficeInfoVO selectBrokerOfficeBySeq(Integer office_no);

}
