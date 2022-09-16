package com.astar.realty.data.broker;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import org.springframework.http.HttpStatus;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;

public class BrokerModify {
    
    @Data
    @ApiModel(value="BrokerModify.Request : 공인중개사 회원정보 수정 요청", description="공인중개사 회원정보 수정 요청")
    public static class Request {
        
        @ApiModelProperty(value="회원 아이디", example = "Broker01")
        @NotBlank(message = "아이디를 입력해 주세요.")
        @Size(min=6, max=20, message = "아이디는 6자 이상 20자 이하만 입력 가능합니다.")
        private String bork_id;

        @ApiModelProperty(value="회원 비밀번호", example = "qwer1234")
        @NotBlank(message = "비밀번호를 입력해 주세요.")
        @Size(min=8, max=20, message = "비밀번호는 8자 이상 20자 이하만 입력 가능합니다.")
        private String bork_pwd;

        @ApiModelProperty(value="회원 이름", example = "홍길동")
        @Size(min=3, max=50, message = "이름은 3자 이상 50자 이하만 입력 가능합니다.")
        private String bork_name;

        @ApiModelProperty(value="회원 전화번호 (하이픈(-)제외)", example = "01012345678")
        @Pattern(regexp = "[0-9]{10,11}", message = "10~11자리의 숫자만 입력가능합니다.")
        private String bork_phone;

        @ApiModelProperty(value="회원 프로필이미지 파일이름", example = "profileImage.jpg")
        private String bork_image_file;

        @ApiModelProperty(value="공인중개사 회사 번호", example = "1")
        @NotNull(message = "공인중개사 회사 번호를 입력해 주세요.")
        private Integer bork_boi_seq;
    }

    @Getter @Builder
    @ApiModel(value="BrokerModify.Response : 공인중개사 회원정보 수정 요청", description="공인중개사 회원정보 수정 응답")
    public static class Response {

        @ApiModelProperty(value="HttpStatus", example = "OK")
        private HttpStatus status;
        
        @ApiModelProperty(value="응답 메세지", example = "공인중개사 회원 정보가 수정되었습니다.")
        private String message;

        @ApiModelProperty(value="성공 여부", allowableValues="true,false")
        private Boolean result;
    }
}
