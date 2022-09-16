package com.astar.realty.data.brokerOffice;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import org.springframework.http.HttpStatus;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;

public class BrokerOfficeCheck {
    
    @Data
    @ApiModel(value="BrokerOfficeCheck.Request : 공인중개사 사무소 등록 확인 요청", description="공인중개사 사무소 등록 확인 요청")
    public static class Request {
        
        @ApiModelProperty(value="사업장 상호명", example="스프링 공인중개사")
        @NotBlank(message="상호명을 입력해 주세요.") @Size(min=3, max=50, message="상호명은 3자 이상 50자 이하만 입력 가능합니다.")
        private String office_name;

        @ApiModelProperty(value="사업자 등록번호 10자리 (하이픈(-)포함)", example="123-12-12345")
        @NotBlank(message="등록번호를 입력해 주세요.") @Pattern(regexp = "([0-9]{3,3})+[-]+([0-9]{2,2})+[-]+([0-9]{5,5})", message="사업자 등록번호는 하이픈(-)포함 12자리입니다.")
        private String reg_no;
    }

    @Getter @Builder
    @ApiModel(value="BrokerOfficeCheck.Response : 공인중개사 사무소 등록 확인 응답", description="공인중개사 사무소 등록 확인 응답")
    public static class Response {

        @ApiModelProperty(value="HttpStatus", example = "CREATED")
        private HttpStatus status;
        
        @ApiModelProperty(value="응답 메세지", example = "공인중개사 회원 정보가 추가되었습니다.")
        private String message;
        
        private Result result;
    }

    @Getter
    @ApiModel(value="BrokerOfficeCheck.Result : 공인중개사 사무소 등록 확인 결과", description="공인중개사 사무소 등록 확인 결과")
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
