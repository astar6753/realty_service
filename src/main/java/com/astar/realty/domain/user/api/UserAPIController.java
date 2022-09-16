package com.astar.realty.domain.user.api;

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
import org.springframework.web.bind.annotation.RestController;

import com.astar.realty.domain.user.application.UserService;
import com.astar.realty.domain.user.data.UserCreate;
import com.astar.realty.domain.user.data.UserInfoVO;
import com.astar.realty.domain.user.data.UserLogin;
import com.astar.realty.domain.user.data.UserRealtyLikeVO;
import com.astar.realty.global.common.Response;

import io.swagger.annotations.ApiOperation;
import springfox.documentation.annotations.ApiIgnore;

@RestController
@RequestMapping("/api")
public class UserAPIController {
    @Autowired UserService service;

    @ApiOperation(value="사용자 등록", notes="")
    @PutMapping("/user")
    public ResponseEntity<Response> createUserInfo(@RequestBody UserCreate.Request request) {
        service.createUserInfo(request);
        Response response = Response.builder()
                                    .status(HttpStatus.CREATED)
                                    .message("")
                                    .build();
        return new ResponseEntity<>(response,response.getStatus());
    }

    @ApiOperation(value="사용자 ID 중복 체크", notes="false = 중복 아님")
    @GetMapping("/user/id:{id}/check")
    public ResponseEntity<Response> checkUserIdDuplication(@PathVariable String id) {
        Response response = Response.builder()
                                    .status(HttpStatus.OK)
                                    .message("")
                                    .result(service.checkUserIdDuplication(id))
                                    .build();
        return new ResponseEntity<>(response,response.getStatus());
    }

    @ApiOperation(value="사용자 로그인", notes="")
    @PostMapping("/user/login")
    public ResponseEntity<UserLogin.Response> makeUserLogin(
        @RequestBody UserLogin.Request request, 
        @ApiIgnore HttpSession session
        ) {
            
            UserLogin.Response response = UserLogin.Response.builder()
                                        .status(HttpStatus.OK)
                                        .message("로그인-세션 정보가 등록되엇습니다.(key: sessionInfo)")
                                        .result(service.makeUserLogin(request, session))
                                        .build();
            return new ResponseEntity<>(response,response.getStatus());
    }

    @ApiOperation(value="사용자 로그아웃", notes="")
    @GetMapping("/user/logout")
    public ResponseEntity<Response> makeUserLogout(
        @ApiIgnore HttpSession session
        ) {

            service.makeUserLogout(session);
            Response response = Response.builder()
                                        .status(HttpStatus.OK)
                                        .message("로그아웃-세션 정보가 말소되었습니다.")
                                        .build();
            return new ResponseEntity<>(response,response.getStatus());
    }

    @ApiOperation(value="사용자 번호로 사용자 정보 수정", notes="")
    @PatchMapping("/user/no:{no}")
    public ResponseEntity<Response> modifyUserInfoBySeq(@PathVariable Integer no) {
        Response response = Response.builder()
                                    .status(HttpStatus.OK)
                                    .message("")
                                    .build();
        return new ResponseEntity<>(response,response.getStatus());
    }

    @ApiOperation(value="사용자 정보 수정", notes="")
    @PatchMapping("/user/status")
    public ResponseEntity<Response> modifyUserStatus(@RequestBody UserInfoVO request) {
        Response response = Response.builder()
                                    .status(HttpStatus.OK)
                                    .message("")
                                    .build();
        return new ResponseEntity<>(response,response.getStatus());
    }

    @ApiOperation(value="사용자 매물 게시글 좋아요 정보 등록", notes="")
    @PostMapping("/user/like")
    public ResponseEntity<Response> toggleUserLikeInfo(
        @RequestBody UserRealtyLikeVO request
    ) {
        Response response = Response.builder()
                                    .status(HttpStatus.CREATED)
                                    .message("")
                                    .build();
        return new ResponseEntity<>(response,response.getStatus());
    }
    
    @ApiOperation(value="사용자 매물 조회 이력 등록", notes="")
    @PutMapping("/user/lookup")
    public ResponseEntity<Response> createUserRealtyLookupInfo() {
        Response response = Response.builder()
                                    .status(HttpStatus.OK)
                                    .message("")
                                    .build();
        return new ResponseEntity<>(response,response.getStatus());
    }

}
