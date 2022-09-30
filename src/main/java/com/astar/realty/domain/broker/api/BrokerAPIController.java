package com.astar.realty.domain.broker.api;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.astar.realty.domain.broker.application.BrokerService;
import com.astar.realty.domain.broker.data.BrokerCreate;
import com.astar.realty.domain.broker.data.BrokerLogin;
import com.astar.realty.domain.broker.data.BrokerModify;
import com.astar.realty.domain.broker.data.BrokerOfficeCreate;
import com.astar.realty.domain.broker.data.BrokerOfficeFind;
import com.astar.realty.domain.broker.data.BrokerOfficeModify;
import com.astar.realty.domain.broker.data.BrokerOfficeSearch;
import com.astar.realty.global.common.Response;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.extern.slf4j.Slf4j;
import springfox.documentation.annotations.ApiIgnore;

@Slf4j // log test
@Validated
@RestController @RequestMapping("/api")
public class BrokerAPIController {
    @Autowired BrokerService service;

    @ApiOperation(value="공인중개사 회원 등록", notes="")
    @ApiResponses({
        @ApiResponse(code=201, message="CREATED 공인중개사 회원 정보가 추가되었습니다."),
        @ApiResponse(code=400, message="INVALID_FIELD 부적절한 필드 값입니다. / ID_DUPLICATTION 이미 가입된 아이디입니다."),
        @ApiResponse(code=500, message="AES_ALGORITHM_ERROR AES 암호화 오류")
    })
    @PostMapping("/broker")
    public ResponseEntity<BrokerCreate.Response> createBroker(
        @Valid @RequestBody BrokerCreate.Request request
        ) {
            log.info("BrokerAPIController.createBroker.request : {}", request);
            BrokerCreate.Response response = BrokerCreate.Response.builder()
                                            .status(HttpStatus.CREATED)
                                            .message("공인중개사 회원 정보가 추가되었습니다.")
                                            .result(service.createBroker(request))
                                            .build();
            log.info("BrokerAPIController.createBroker.response : {}", response);
            return new ResponseEntity<>(response,response.getStatus());
    }

    @ApiOperation(value="공인중개사 회원 ID 중복 체크", notes="회원 ID로 중복 검사")
    @ApiResponses({
        @ApiResponse(code=200, message="OK 사용할 수 있는 아이디입니다."),
        @ApiResponse(code=400, message="ID_DUPLICATTION 이미 가입된 아이디입니다.")
    })
    @GetMapping("/broker/check")
    public ResponseEntity<Response> checkBrokerIdDuplication(
        @ApiParam(value="공인중개사 회원 ID (대소문자 구분)", example="Broker01")
        @Valid @NotBlank(message="아이디를 입력해 주세요.") @Size(min=6, max=20, message="아이디는 6자 이상 20자 이하만 입력 가능합니다.")
        @RequestParam String id
        ) {
            Response response = Response.builder()
                                        .status(HttpStatus.OK)
                                        .message(id+"은/는 사용할 수 있는 아이디입니다.")
                                        .result(service.checkBrokerIdDuplication(id))
                                        .build();
            return new ResponseEntity<>(response,response.getStatus());
    }

    @ApiOperation(value="공인중개사 회원 로그인", notes="")
    @ApiResponses({
        @ApiResponse(code=201, message="CREATED 로그인-세션 정보가 등록되엇습니다.(key: sessionInfo)"),
        @ApiResponse(code=400, message="INVALID_FIELD 부적절한 필드 값입니다."),
        @ApiResponse(code=401, message="INCORRECT_LOGIN_ARGUMENT 아이디 혹은 비밀번호를 확인해주세요."),
        @ApiResponse(code=500, message="AES_ALGORITHM_ERROR AES 암호화 오류")
    })
    @PostMapping("/broker/login")
    public ResponseEntity<BrokerLogin.Response> makeBrokerLogin(
        @Valid @RequestBody BrokerLogin.Request request, 
        @ApiIgnore HttpSession session
        ) {
            BrokerLogin.Response response = BrokerLogin.Response.builder()
                                        .status(HttpStatus.CREATED)
                                        .message("로그인-세션 정보가 등록되엇습니다.(key: sessionInfo)")
                                        .result(service.makeBrokerLogin(request, session))
                                        .build();
            return new ResponseEntity<>(response,response.getStatus());
    }

    @ApiOperation(value="공인중개사 회원 로그아웃", notes="")
    @ApiResponse(code=204, message="NO_CONTENT 로그아웃-세션 정보가 말소되었습니다.")
    @GetMapping("/broker/logout")
    public ResponseEntity<Response> makeBrokerLogout(
        @ApiIgnore HttpSession session
        ) {
            service.makeBrokerLogout(session);
            Response response = Response.builder()
                                        .status(HttpStatus.NO_CONTENT)
                                        .message("로그아웃-세션 정보가 말소되었습니다.")
                                        .build();
            return new ResponseEntity<>(response,response.getStatus());
    }

    @ApiOperation(value="공인중개사 회원 정보 수정", notes="회원ID로 회원 정보 수정")
    @ApiResponses({
        @ApiResponse(code=200, message="OK 공인중개사 회원 정보가 수정되었습니다."),
        @ApiResponse(code=400, message="INVALID_FIELD 부적절한 필드 값입니다. / NOT_EXIST_BROKER_ID 존재하지 않는 ID입니다. / NOT_EXIST_OFFICE_NO 존재하지 않는 공인중개사 사무소 번호입니다."),
        @ApiResponse(code=500, message="AES_ALGORITHM_ERROR AES 암호화 오류")
    })
    @PatchMapping("/broker")
    public ResponseEntity<BrokerModify.Response> modifyBrokerInfoById(
        @Valid @RequestBody BrokerModify.Request request
        ) {
            BrokerModify.Response response = BrokerModify.Response.builder()
                                        .status(HttpStatus.OK)
                                        .message("공인중개사 회원 정보가 수정되었습니다.")
                                        .result(service.modifyBrokerInfoById(request))
                                        .build();
            return new ResponseEntity<>(response,response.getStatus());
    }

    @ApiOperation(value="공인중개사 회원 상태 변경", notes="")
    @ApiResponses({
        @ApiResponse(code=200, message="OK 공인중개사 회원 상태가 변경되었습니다."),
        @ApiResponse(code=400, message="NOT_EXIST_BROKER_NO 존재하지 않는 공인중개사 번호입니다. / INVALID_STATUS 잘못된 요청입니다. / INVALID_GRADE 잘못된 요청입니다.")
    })
    @PatchMapping("/broker/status:{status}/grade:{grade}")
    public ResponseEntity<Response> modifyBrokerStatus(
        @ApiParam(value="회원 상태[1:정상,2:정지,3:탈퇴대기,4:탈퇴]", allowableValues="1,2,3,4") @Valid @NotNull(message="status를 입력해 주세요.[1:정상,2:정지,3:탈퇴대기,4:탈퇴]") 
        @PathVariable Integer status, 
        @ApiParam(value="회원 등급[broker,admin]", allowableValues="broker,admin") @Valid @NotBlank(message="grade를 입력해 주세요.[broker,admin]") 
        @PathVariable String grade, 
        @ApiParam(value="회원 번호") @Valid @NotNull(message="broker_no를 입력해 주세요.") 
        @RequestParam Integer broker_no, 
        @ApiIgnore HttpSession session
        ) {
            Response response = Response.builder()
                                        .status(HttpStatus.OK)
                                        .message("공인중개사 회원 상태가 변경되었습니다.")
                                        .result(service.modifyBrokerStatus(status, grade, broker_no, session))
                                        .build();
            return new ResponseEntity<>(response,response.getStatus());
    }

    @ApiOperation(value="공인중개사 사무소 등록", notes="")
    @ApiResponses({
        @ApiResponse(code=201, message="CREATED 공인중개사 사무소 정보가 추가되었습니다."),
        @ApiResponse(code=400, message="INVALID_FIELD 부적절한 필드 값입니다. / BROKER_OFFICE_DUPLICATION 이미 등록된 사업장 상호명 혹은 사업장 등록번호입니다."),
    })
    @PostMapping("/broker/office")
    public ResponseEntity<BrokerOfficeCreate.Response> createBrokerOffice(
        @Valid @RequestBody BrokerOfficeCreate.Request request
        ) {
            BrokerOfficeCreate.Response response = BrokerOfficeCreate.Response.builder()
                                        .status(HttpStatus.CREATED)
                                        .message("공인중개사 사무소 정보가 추가되었습니다.")
                                        .result(service.createBrokerOffice(request))
                                        .build();
            return new ResponseEntity<>(response,response.getStatus());
    }

    @ApiOperation(value="공인중개사 사무소 중복 검사", notes="ConstraintViolationException @vaild handler전역에러처리")
    @ApiResponses({
        @ApiResponse(code=200, message="OK 등록할 수 있는 사무소 정보입니다."),
        @ApiResponse(code=400, message="BROKER_OFFICE_DUPLICATION 이미 등록된 사업장 상호명 혹은 사업장 등록번호입니다.")
    })
    @GetMapping("/broker/office/check")
    public ResponseEntity<Response> checkBrokerOfficeDuplication(
        @ApiParam(value="상호명", name="name", example="스프링 공인중개사")
        @Valid @Size(min=3, max=50, message="상호명은 3자 이상 50자 이하만 입력 가능합니다.")
        @RequestParam String name, 
        @ApiParam(value="사업자 등록 번호", name="reg_no", example="123-12-12345")
        @Valid @Pattern(regexp="([0-9]{3,3})+[-]+([0-9]{2,2})+[-]+([0-9]{5,5})", message="사업자 등록번호는 하이픈(-)포함 12자리입니다.")
        @RequestParam String reg_no
        ) {
            Response response = Response.builder()
                                        .status(HttpStatus.OK)
                                        .message("등록할 수 있는 사무소 정보입니다.")
                                        .result(service.checkBrokerOfficeExist(name,reg_no))
                                        .build();
            return new ResponseEntity<>(response,response.getStatus());
    }

    @ApiOperation(value="공인중개사 사무소 조회", notes="상호명으로 공인중개사 사무소 정보 조회")
    @ApiResponse(code=200, message="OK 공인중개사 사무소 정보가 조회되었습니다.")
    @PostMapping("/broker/office/list")
    public ResponseEntity<BrokerOfficeSearch.Response> searchBrokerOfficeInfoList(
        @Valid @RequestBody BrokerOfficeSearch.Request request
        ) {
            BrokerOfficeSearch.Response response = BrokerOfficeSearch.Response.builder()
                                        .status(HttpStatus.OK)
                                        .message("공인중개사 사무소 정보가 조회되었습니다.")
                                        .result(service.searchBrokerOfficeInfoList(request))
                                        .build();
            return new ResponseEntity<>(response,response.getStatus());
    }

    @ApiOperation(value="공인중개사 사무소 정보 수정", notes="(주소/전화번호/대표자 이름/이미지)")
    @ApiResponses({
        @ApiResponse(code=200, message="OK 공인중개사 사무소 정보가 수정되었습니다."),
        @ApiResponse(code=400, message="INVALID_FIELD 부적절한 필드 값입니다. / NOT_EXIST_OFFICE_NO 해당 중개사무소 번호는 존재하지 않습니다."),
        @ApiResponse(code=500, message="OFFICE_MODIFICATION_FAILED 중개사무소 수정이 실패하였습니다.")
    })
    @PatchMapping("/broker/office/update")
    public ResponseEntity<BrokerOfficeModify.Response> modifyBrokerOfficeInfo(
        @Valid @RequestBody BrokerOfficeModify.Request request
        ) {

            BrokerOfficeModify.Response response = BrokerOfficeModify.Response.builder()
                                        .status(HttpStatus.OK)
                                        .message("공인중개사 사무소 정보가 수정되었습니다.")
                                        .result(service.modifyBrokerOfficeInfo(request))
                                        .build();
            return new ResponseEntity<>(response,response.getStatus());   
    }

    @ApiOperation(value="공인중개사 사무소 정보 삭제", notes="사무소 번호로 공인중개사 사무소 정보 삭제")
    @ApiResponses({
        @ApiResponse(code=200, message="OK 공인중개사 사무소 정보가 삭제되었습니다."),
        @ApiResponse(code=400, message="NOT_EXIST_OFFICE_NO 해당 중개사무소 번호는 존재하지 않습니다."),
        @ApiResponse(code=500, message="OFFICE_DELETION_FAILED 중개사무소 삭제가 실패하였습니다.")
    })
    @DeleteMapping("/broker/office/no:{office_no}")
    public ResponseEntity<Response> removeBrokerOfficeBySeq(
        @ApiParam(value="사무소 번호", name="office_no", example="1")
        @PathVariable Integer office_no
        ) {
            Response response = Response.builder()
                                        .status(HttpStatus.OK)
                                        .message("공인중개사 사무소 정보가 삭제되었습니다.")
                                        .result(service.removeBrokerOfficeBySeq(office_no))
                                        .build();
            return new ResponseEntity<>(response,response.getStatus());
    }

    @ApiOperation(value="공인중개사 사무소 조회", notes="사무소 번호로 특정 사무소 1개 정보 조회")
    @ApiResponses({
        @ApiResponse(code=200, message="OK 공인중개사 사무소 정보가 삭제되었습니다."),
        @ApiResponse(code=400, message="NOT_EXIST_OFFICE_NO 해당 중개사무소 번호는 존재하지 않습니다.")
    })
    @GetMapping("/broker/office/no:{office_no}")
    public ResponseEntity<BrokerOfficeFind.Response> findBrokerOfficeInfoBySeq(
        @ApiParam(value="사무소 번호", name="office_no", example="1")
        @PathVariable Integer office_no
        ) {
            BrokerOfficeFind.Response response = BrokerOfficeFind.Response.builder()
                                        .status(HttpStatus.OK)
                                        .message("공인중개사 사무소 정보가 조회되었습니다.")
                                        .result(service.findBrokerOfficeInfoBySeq(office_no))
                                        .build();
            return new ResponseEntity<>(response,response.getStatus());
    }

}
