package com.astar.realty.domain.broker.data;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import org.springframework.http.HttpStatus;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;

public class BrokerOfficeModify {
    
    @Data
    @ApiModel(value="BrokerModify.Request : 공인중개사 사무소 수정 요청", description="공인중개사 사무소 수정 요청")
    public static class Request {
        
        @ApiModelProperty(value="사업장 번호", example="1")
        @NotNull(message = "사업장 번호는 필수 입력값입니다.")
        private Integer boi_seq;

        @ApiModelProperty(value="사업장 주소", example="자바시 스프링구 마이바티스동 123-123")
        @Size(min=3, max=50, message = "주소는 3자 이상 50자 이하만 입력 가능합니다.")
        private String boi_address;
        
        @ApiModelProperty(value="사업장 대표 전화번호 (하이픈(-)제외)", example = "01012345678")
        @Pattern(regexp = "[0-9]{10,11}", message = "10~11자리의 숫자만 입력가능합니다.")
        private String boi_phone;

        @ApiModelProperty(value="사업장 대표자 이름", example="홍길동")
        @Size(min=3, max=50, message = "이름은 3자 이상 50자 이하만 입력 가능합니다.")
        private String boi_master_name;

        @ApiModelProperty(value="사업장 대표 이미지 파일이름", example = "officeImage.jpg")
        private String boi_image_file;
    }

    @Getter @Builder
    @ApiModel(value="BrokerModify.Response : 공인중개사 사무소 수정 요청", description="공인중개사 사무소 수정 응답")
    public static class Response {

        @ApiModelProperty(value="HttpStatus", example = "OK")
        private HttpStatus status;
        
        @ApiModelProperty(value="응답 메세지", example = "공인중개사 사무소 정보가 수정되었습니다.")
        private String message;

        @ApiModelProperty(value="성공 여부", allowableValues="true,false")
        private Boolean result;
    }
}
