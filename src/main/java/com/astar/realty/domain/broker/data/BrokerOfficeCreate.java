package com.astar.realty.domain.broker.data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import org.springframework.http.HttpStatus;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;

public class BrokerOfficeCreate {

    @Data
    @ApiModel(value="BrokerOfficeCreate.Request : 공인중개사 사무소 등록 요청", description="공인중개사 사무소 등록 요청")
    public static class Request {
        
        @ApiModelProperty(value="사업장 상호명", example="스프링 공인중개사")
        @NotBlank(message = "상호명을 입력해 주세요.")
        @Size(min=3, max=50, message = "상호명은 3자 이상 50자 이하만 입력 가능합니다.")
        private String boi_name;

        @ApiModelProperty(value="사업장 주소", example="자바시 스프링구 마이바티스동 123-123")
        @NotBlank(message = "주소를 입력해 주세요.")
        @Size(min=3, max=50, message = "주소는 3자 이상 50자 이하만 입력 가능합니다.")
        private String boi_address;

        @ApiModelProperty(value="사업장 대표 전화번호 (하이픈(-)제외)", example = "01012345678")
        @NotBlank(message = "전화번호를 입력해 주세요.")
        @Pattern(regexp = "[0-9]{10,11}", message = "10~11자리의 숫자만 입력가능합니다.")
        private String boi_phone;

        @ApiModelProperty(value="사업장 대표자 이름", example="홍길동")
        @NotBlank(message = "이름을 입력해 주세요.")
        @Size(min=3, max=50, message = "이름은 3자 이상 50자 이하만 입력 가능합니다.")
        private String boi_master_name;

        @ApiModelProperty(value="사업자 등록번호 10자리 (하이픈(-)포함)", example="123-12-12345")
        @NotBlank(message = "등록번호를 입력해 주세요.")
        @Pattern(regexp = "([0-9]{3,3})+[-]+([0-9]{2,2})+[-]+([0-9]{5,5})", message = "사업자 등록번호는 하이픈(-)포함 12자리입니다.")
        private String boi_reg_number;

        @ApiModelProperty(value="사업장 대표 이미지 파일이름", example = "officeImage.jpg")
        @NotBlank(message = "사업장 이미지를 입력해 주세요.")
        private String boi_image_file;

    }

    @Getter @Builder
    @ApiModel(value="BrokerOfficeCreate.Response : 공인중개사 사무소 등록 응답", description="공인중개사 사무소 등록 응답")
    public static class Response {

        @ApiModelProperty(value="HttpStatus", example = "CREATED")
        private HttpStatus status;
        
        @ApiModelProperty(value="응답 메세지", example = "공인중개사 사무소 정보가 추가되었습니다.")
        private String message;

        @ApiModelProperty(value="성공 여부", allowableValues="true,false")
        private Boolean result;
    }
}
