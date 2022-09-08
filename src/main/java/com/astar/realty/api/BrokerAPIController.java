package com.astar.realty.api;

import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.Nullable;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.astar.realty.data.BrokerInfoVO;
import com.astar.realty.data.BrokerLoginVO;
import com.astar.realty.data.BrokerOfficeInfoVO;
import com.astar.realty.data.request.BrokerDTO;
import com.astar.realty.data.response.Response;
import com.astar.realty.service.BrokerService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@RequestMapping("/api/broker")
public class BrokerAPIController {
    @Autowired BrokerService service;

    @ApiOperation(
        value="공인중개사 회원 정보 추가", 
        notes="CREATED/ID_DUPLICATTION/INVALID_PWD/AES_ALGORITHM_ERROR"
    )
    @ApiResponses({
        @ApiResponse(code = 201, message = "CREATED 공인중개사 회원 정보가 추가되었습니다."),
        @ApiResponse(code = 400, message = "B001 ID_DUPLICATTION 이미 가입된 아이디입니다."),
        @ApiResponse(code = 400, message = "B003 INVALID_PWD 비밀번호는 6자 이상 입력해 주세요"),
        @ApiResponse(code = 500, message = "B005 AES_ALGORITHM_ERROR AES 암호화 오류")
    })
    @PostMapping("/add")
    public ResponseEntity<Response> brokerInfoAdd(@RequestBody BrokerDTO.Request request) {
        service.insertBrokerInfo(request);
        Response response = Response.builder()
                                    .status(HttpStatus.CREATED)
                                    .message("공인중개사 회원 정보가 추가되었습니다.")
                                    .build();
        return new ResponseEntity<>(response,response.getStatus());
    }

    @ApiOperation(
        value="공인중개사 회원 ID중복 체크", 
        notes="id형식/체크 필요"
    )
    @ApiResponses({
        @ApiResponse(code = 200, message = "OK 사용할 수 있는 아이디입니다."),
        @ApiResponse(code = 400, message = "B001 ID_DUPLICATTION 이미 가입된 아이디입니다."),
        @ApiResponse(code = 400, message = "B002 INVALID_ID ###")
    })
    @GetMapping("idcheck/{id}")
    public ResponseEntity<Response> isExistBrokerId(@PathVariable String id) {
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
        @PathVariable String value, @RequestParam Integer status, @RequestParam Integer user_no, HttpSession session) {
        Response response = Response.builder()
                                    .status(HttpStatus.ACCEPTED)
                                    .message("공인중개사 회원 상태가 변경되었습니다.")
                                    .result(service.updateBrokerStatus(value, status, user_no, session))
                                    .build();
        return new ResponseEntity<>(response,response.getStatus());
    }


    //공인중개사 사무소
    //공인중개사 추가전에 동일 존재 여부 확인
    @PostMapping("/office/add")
    public ResponseEntity<Response> brokerOfficeadd(@RequestBody BrokerOfficeInfoVO request) {
        // servivce.insertBrokerOfficeInfo(request);
        Response response = Response.builder()
                                    .status(HttpStatus.CREATED)
                                    .message("공인중개사 사무소가 추가되었습니다.")
                                    .build();
        return new ResponseEntity<>(response,response.getStatus());
    }

    //공인중개사 사무소 등록시 확인 버튼 기능
    @GetMapping("/office/check")
    public ResponseEntity<Response> checkBrokerOfficeInfo(
        @RequestParam String name, @RequestParam Integer reg_no){
        service.checkBrokerOfiiceInfo(name,reg_no);
        Response response = Response.builder()
                                    .status(HttpStatus.ACCEPTED)
                                    .message("등록할 수 있는 사무소 정보입니다.")
                                    .build();
        return new ResponseEntity<>(response,response.getStatus());
    }
    
    //공인중개사 사무소 조회
    @GetMapping("/office/list")
    public ResponseEntity<Response> getBrokerOfficeInfoList(
        @RequestParam @Nullable String keyword, @RequestParam @Nullable Integer page){
        service.selectBrokerOfficeInfoList(keyword,page);
        Response response = Response.builder()
                                    .status(HttpStatus.ACCEPTED)
                                    .message("공인중개사 사무소 정보가 조회되었습니다.")
                                    .build();
        return new ResponseEntity<>(response,response.getStatus());
    }

    @ApiOperation(
        value="공인중개사 사무소 정보 변경(주소/전화번호/대표자 이름/이미지)", 
        notes="ACCEPTED"
    )
    @PatchMapping("/office/update")
    public ResponseEntity<Response> patchBrokerOfficeInfo(@RequestBody BrokerOfficeInfoVO request) {
        //각 형식 validation
        //동일 여무 validation
        //update return int로 받아서 성공여부 판정
        service.updateBrokerOfficeInfo(request);
        Response response = Response.builder()
                                    .status(HttpStatus.ACCEPTED)
                                    .message("공인중개사 사무소 정보가 수정되었습니다.")
                                    .build();
        return new ResponseEntity<>(response,response.getStatus());   
    }
    
    @DeleteMapping("/office/delete")
    public ResponseEntity<Response> deleteBrokerOfficeInfo(@RequestParam Integer no) {
        Response response = Response.builder()
                                    .status(HttpStatus.ACCEPTED)
                                    .message("공인중개사 사무소 정보가 삭제되었습니다.")
                                    .result(service.deleteBrokerOfficeInfo(no))
                                    .build();
        return new ResponseEntity<>(response,response.getStatus());
    }

    @ApiOperation(
        value="공인중개사 사무소 조회",
        notes="ACCEPTED"
    )
    @GetMapping("/office")
    public ResponseEntity<Response> getBrokerOfficeInfoBySeq(@RequestParam Integer no){
        Response response = Response.builder()
                                    .status(HttpStatus.ACCEPTED)
                                    .message("공인중개사 사무소 정보가 조회되었습니다.")
                                    .result(service.selectBrokerOfficeInfoBySeq(no))
                                    .build();
        return new ResponseEntity<>(response,response.getStatus());
    }

}
