package com.astar.realty.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.Nullable;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.astar.realty.data.BuildingInfoVO;
import com.astar.realty.data.RealtyTotalInfoVO;
import com.astar.realty.data.response.Response;
import com.astar.realty.service.RealtyService;

import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import io.swagger.v3.oas.annotations.parameters.RequestBody;

@RestController
@RequestMapping("/api/realty")
public class RealtyAPIController {
    @Autowired RealtyService service;

    @ApiOperation(
        value="건물 등록", 
        notes="CREATED/REALTY_ADDRESS_DUPICATION"
    )
    @PutMapping("/building/add")
    public ResponseEntity<Response> addBuildingInfo(@RequestBody BuildingInfoVO request) {
        service.insertBrokerInfo(request);
        Response response = Response.builder()
                                .status(HttpStatus.CREATED)
                                .message("건물 정보가 등록되었습니다.")
                                .build();
        return new ResponseEntity<>(response,response.getStatus());
    }

    @ApiOperation(
        value="건물 주소 중복 체크", 
        notes="OK/REALTY_ADDRESS_DUPICATION"
    )
    @GetMapping("/building/check/addr")
    public ResponseEntity<Response> isExistBrokerId(@RequestParam String address) {
        service.isExistBuildingAddress(address);
        Response response = Response.builder()
                                .status(HttpStatus.OK)
                                .message("아직 등록되지 않은 주소지입니다.")
                                .build();
        return new ResponseEntity<>(response,response.getStatus());
    }

    @ApiOperation(
        value="건물 정보 조회", 
        notes="OK"
    )
    @ApiImplicitParams({
        @ApiImplicitParam(name = "keyword", dataType = "String", required = false),
        @ApiImplicitParam(name = "page", dataType = "Integer", required = false)
    })
    @GetMapping("/building/list")
    public ResponseEntity<Response> selectBuildingInfoList(
        @RequestParam @Nullable String keyword, @RequestParam @Nullable Integer page
        ) {
        Response response = Response.builder()
                                .status(HttpStatus.OK)
                                .message("건물 정보가 조회되었습니다.")
                                .result(service.selectBuildingInfoList(keyword, page))
                                .build();
        return new ResponseEntity<>(response,response.getStatus());
    }

    @ApiOperation(
        value="부동산 매매/임대 정보를 추가", 
        notes="OK"
    )
    @Transactional
    @PutMapping("/post/add")
    public ResponseEntity<Response> insertRealtyTotalInfo(@RequestBody RealtyTotalInfoVO request){
        service.insertRealtyTotalInfo(request);
        Response response = Response.builder()
                                .status(HttpStatus.OK)
                                .message("부동산 매매/임대 정보를 추가하였습니다.")
                                .build();
        return new ResponseEntity<>(response,response.getStatus());
    }

}
