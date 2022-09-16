package com.astar.realty.data.broker;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import org.springframework.http.HttpStatus;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;

public class BrokerLogin {

    @Data
    @ApiModel(value="BrokerLogin.Request : 공인중개사 회원 로그인 요청", description="공인중개사 회원 로그인 요청")
    public static class Request {
        
        @ApiModelProperty(value="회원 아이디", example = "Broker01")
        @NotBlank(message = "아이디를 입력해 주세요.")
        @Size(min=6, max=20, message = "아이디는 6자 이상 20자 이하만 입력 가능합니다.")
        private String id;

        @ApiModelProperty(value="회원 비밀번호", example = "qwer1234")
        @NotBlank(message = "비밀번호를 입력해 주세요.")
        @Size(min=8, max=20, message = "비밀번호는 8자 이상 20자 이하만 입력 가능합니다.")
        private String pwd;
    }

    @Getter @Builder
    @ApiModel(value="BrokerLogin.Response : 공인중개사 회원 로그인 응답", description="공인중개사 회원 로그인 응답")
    public static class Response {

        @ApiModelProperty(value="HttpStatus", example = "CREATED")
        private HttpStatus status;
        
        @ApiModelProperty(value="응답 메세지", example = "공인중개사 회원 정보가 추가되었습니다.")
        private String message;
        
        private Result result;
    }

    @Getter
    @ApiModel(value="BrokerLogin.Result : 공인중개사 회원 로그인 결과", description="공인중개사 회원 로그인 결과")
    public static class Result {
        
        @ApiModelProperty(value="회원 아이디", example = "Broker01")
        private String id;
        
        @ApiModelProperty(value="회원 이름", example = "홍길동")
        private String name;
        
        @ApiModelProperty(value="회원 프로필이미지 파일이름", example = "profileImage.jpg")
        private String profileImg;

        // private String token;
    }

}
