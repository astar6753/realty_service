package com.astar.realty.domain.user.data;

import java.util.Date;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.Range;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;

public class UserCreate {

    @Data
    @ApiModel(value="UserCreate.Request : 서비스 회원 등록 요청", description="서비스 회원 등록 요청")
    public static class Request {
        
        @ApiModelProperty(value="회원 아이디", example = "User01")
        @NotBlank(message = "아이디를 입력해 주세요.")
        @Size(min=6, max=20, message = "아이디는 6자 이상 20자 이하만 입력 가능합니다.")
        private String ui_id;

        @ApiModelProperty(value="회원 비밀번호", example = "qwer1234")
        @NotBlank(message = "비밀번호를 입력해 주세요.")
        @Size(min=8, max=20, message = "비밀번호는 8자 이상 20자 이하만 입력 가능합니다.")
        private String ui_pwd;

        @ApiModelProperty(value="회원 이름", example = "홍길동")
        @NotBlank(message = "이름를 입력해 주세요.")
        @Size(min=3, max=50, message = "이름은 3자 이상 50자 이하만 입력 가능합니다.")
        private String ui_name;

        @ApiModelProperty(value="회원 생년월일", example = "2001-01-01")
        @DateTimeFormat(pattern = "yyyy-MM-dd")
        private Date ui_birth;

        @ApiModelProperty(value="회원 성별", example = "1", allowableValues = "1:남,2:여")
        @Range(min=1,max=2, message = "성별은 (1:남 2:여)만 입력 가능합니다.")
        private Integer ui_gen;
        
        @ApiModelProperty(value="회원 전화번호 (하이픈(-)제외)", example = "01012345678")
        @Pattern(regexp = "[0-9]{10,11}", message = "10~11자리의 숫자만 입력가능합니다.")
        private String ui_phone;

        @ApiModelProperty(value="회원 프로필이미지 파일이름", example = "profileImage.jpg")
        private String ui_image_file;

    }

    @Getter @Builder
    @ApiModel(value="UserCreate.Response : 서비스 회원 추가 응답", description="서비스 회원 추가 응답")
    public static class Response {

        @ApiModelProperty(value="HttpStatus", example = "CREATED")
        private HttpStatus status;
        
        @ApiModelProperty(value="응답 메세지", example = "서비스 회원 정보가 추가되었습니다.")
        private String message;

        @ApiModelProperty(value="성공 여부", allowableValues="true,false")
        private Boolean result;
    }
}
