package com.astar.realty.api;

import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.astar.realty.data.BrokerInfoVO;
import com.astar.realty.data.BrokerLoginVO;
import com.astar.realty.data.RealtyTotalInfoVO;
import com.astar.realty.data.response.Response;
import com.astar.realty.service.BrokerService;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/api/broker")
public class BrokerAPIController {
    @Autowired BrokerService service;

    @ApiOperation(
        value="공인중개사 회원 정보 추가", 
        notes="OK/ID_DUPLICATED/PWD_NOT_ALLOWED/AES_ALGORITHM_ERROR"
    )
    @PostMapping("/add")
    public ResponseEntity<Response> brokerInfoAdd(@RequestBody BrokerInfoVO request) {
        service.insertBrokerInfo(request);
        Response response = Response.builder()
                                .status(HttpStatus.CREATED)
                                .message("공인중개사 회원 정보가 추가되었습니다.")
                                .build();
        return new ResponseEntity<>(response,response.getStatus());
    }

    @ApiOperation(
        value="공인중개사 회원 ID중복 체크", 
        notes="OK/ID_DUPLICATED"
    )
    @GetMapping("idcheck")
    public ResponseEntity<Response> isExistBrokerId(@RequestParam String id) {
        Response response = Response.builder()
                                .status(HttpStatus.OK)
                                .message(id+"은/는 사용할 수 있는 아이디입니다.")
                                .result(service.isExistBrokerId(id))
                                .build();
        return new ResponseEntity<>(response,response.getStatus());
    }

    @ApiOperation(
        value="공인중개사 회원 로그인", 
        notes="ACCEPTED/INVALID_ID/INVALID_PWD/AES_ALGORITHM_ERROR/LOGIN_INPUT_INVALID"
    )
    @PostMapping("/login")
    public ResponseEntity<Response> loginBroker(@RequestBody BrokerLoginVO login, HttpSession session) {
        Response response = Response.builder()
                                .status(HttpStatus.ACCEPTED)
                                .message("로그인-세션 정보가 등록되엇습니다.")
                                .result(service.loginBroker(login, session))
                                .build();
        return new ResponseEntity<>(response,response.getStatus());
    }

    @ApiOperation(
        value="공인중개사 회원 로그아웃", 
        notes="ACCEPTED"
    )
    @GetMapping("/logout")
    public ResponseEntity<Response> logoutBroker(HttpSession session){
        service.logoutBroker(session);
        Response response = Response.builder()
                                .status(HttpStatus.ACCEPTED)
                                .message("로그아웃-세션 정보가 말소되었습니다.")
                                .build();
        return new ResponseEntity<>(response,response.getStatus());
    }

    @ApiOperation(
        value="공인중개사 회원 정보 수정", 
        notes="ACCEPTED/INVALID_ID/INVALID_PWD/AES_ALGORITHM_ERROR"
    )
    @PatchMapping("/update")
    public ResponseEntity<Response> updateBroker(@RequestBody BrokerInfoVO request) {
        service.updateBrokerInfo(request);
        Response response = Response.builder()
                                .status(HttpStatus.ACCEPTED)
                                .message("공인중개사 회원 정보가 수정되었습니다.")
                                .build();
        return new ResponseEntity<>(response,response.getStatus());
    }

    @ApiOperation(
        value="공인중개사 회원 상태 변경(1:2:3:4:)", 
        notes="ACCEPTED/INVALID_STATUS/INVALID_VALUE"
    )
    @PatchMapping("/status/{value}")
    public ResponseEntity<Response> updateBrokerStatus(
        @PathVariable String value, @RequestParam Integer status, @RequestParam Integer user_no, HttpSession session
        ) {
            Response response = Response.builder()
                                    .status(HttpStatus.ACCEPTED)
                                    .message("공인중개사 회원 상태가 변경되었습니다.")
                                    .result(service.updateBrokerStatus(value, status, user_no, session))
                                    .build();
            return new ResponseEntity<>(response,response.getStatus());
    }

}
