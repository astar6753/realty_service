package com.astar.realty.domain.realty.data;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiParam;
import lombok.Data;

@Data
@ApiModel(value="RealtyPostInfoVO: 부동산 매물 게시글정보", description="부동산 매물 게시글정보")
public class RealtyPostInfoVO {

    @ApiParam(value="매물 게시글 번호")
    private Integer rpi_seq;

    @ApiParam(value="게시글 제목", example="초저렴.채광눈부십니다.혼자살기딱")
    private String rpi_title;

    @ApiParam(value="게시글 내용", example="*방 구조 좋고 넓은 집 입니다.\n*풀옵션 1.5룸입니다.\n*즉시입주가능해요!")
    private String rpi_content;

    @ApiParam(value="게시글 작성자 번호", example="1")
    private Integer rpi_bork_seq;

    @ApiParam(value="매물 기본정보 번호")
    private Integer rpi_rbi_seq;
}
