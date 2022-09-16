package com.astar.realty.domain.realty.data;

import io.swagger.annotations.ApiParam;
import lombok.Data;

@Data
public class RealtyPostInfoVO {
    @ApiParam(value = "매물 게시글 번호", required=true)
    private Integer rpi_seq;
    @ApiParam(value = "게시글 제목", required=true)
    private String rpi_title;
    @ApiParam(value = "게시글 내용", required=true)
    private String rpi_content;
    @ApiParam(value = "게시글 작성자 번호", required=true)
    private Integer rpi_bork_seq;
    @ApiParam(value = "매물 기본정보 번호", required=true)
    private Integer rpi_rbi_seq;
}
