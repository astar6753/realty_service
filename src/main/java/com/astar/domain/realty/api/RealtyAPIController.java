package com.astar.domain.realty.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.Nullable;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.astar.domain.broker.data.BuildingInfoUpdateVO;
import com.astar.domain.broker.data.BuildingInfoVO;
import com.astar.domain.realty.application.RealtyService;
import com.astar.domain.realty.data.RealtySearchRequestVO;
import com.astar.domain.realty.data.RealtyTotalInfoVO;
import com.astar.global.common.Response;

import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import io.swagger.v3.oas.annotations.parameters.RequestBody;

@RestController
@RequestMapping("/api/realty")
public class RealtyAPIController {
    @Autowired RealtyService service;

    @ApiOperation(
        value="건물 등록", 
        notes="building info CRUD권한 체크 필요"
    )
    @ApiResponses({
        @ApiResponse(code = 201, message = "CREATED 건물 정보가 등록되었습니다."),
        @ApiResponse(code = 202, message = "REALTY_ADDRESS_DUPICATION 이미 등록된 주소지입니다.")
    })
    @PutMapping("/building/add")
    public ResponseEntity<Response> addBuildingInfo(@RequestBody BuildingInfoVO request) {
        service.insertBrokerInfo(request);
        Response response = Response.builder()
                                    .status(HttpStatus.CREATED)
                                    .message("건물 정보가 등록되었습니다.")
                                    .build();
        return new ResponseEntity<>(response,response.getStatus());
    }

    @ApiOperation(
        value="건물 주소 중복 체크", 
        notes="OK/REALTY_ADDRESS_DUPICATION"
    )
    @GetMapping("/building/check/addr")
    public ResponseEntity<Response> isExistBrokerId(@RequestParam String address) {
        service.isExistBuildingAddress(address);
        Response response = Response.builder()
                                    .status(HttpStatus.OK)
                                    .message("아직 등록되지 않은 주소지입니다.")
                                    .build();
        return new ResponseEntity<>(response,response.getStatus());
    }

    @ApiOperation(
        value="건물 정보 조회", 
        notes="OK"
    )
    @ApiImplicitParams({
        @ApiImplicitParam(name = "keyword", dataType = "String", required = false),
        @ApiImplicitParam(name = "page", dataType = "Integer", required = false)
    })
    @GetMapping("/building/list")
    public ResponseEntity<Response> selectBuildingInfoList(
        @RequestParam @Nullable String keyword, @RequestParam @Nullable Integer page
        ) {
        Response response = Response.builder()
                                    .status(HttpStatus.OK)
                                    .message("건물 정보가 조회되었습니다.")
                                    .result(service.selectBuildingInfoList(keyword, page))
                                    .build();
        return new ResponseEntity<>(response,response.getStatus());
    }

    @ApiOperation(
        value="건물 정보 수정", 
        notes="OK/"
    )
    @PatchMapping("/building/update")
    public ResponseEntity<Response> updateBuildingInfo(@RequestBody BuildingInfoUpdateVO request){
        service.updateBuildingInfo(request);
        Response response = Response.builder()
                                    .status(HttpStatus.OK)
                                    .message("건물 정보가 수정되었습니다.")
                                    .build();
        return new ResponseEntity<>(response,response.getStatus());
    }

    @ApiOperation(
        value="건물 정보 삭제", 
        notes="ACCEPTED/NOT_EXISTED_BUILDING"
    )
    @DeleteMapping("/building/delete")
    public ResponseEntity<Response> deleteBuildingInfo(@RequestParam Integer building_seq){
        service.deleteBuildingInfo(building_seq);
        Response response = Response.builder()
                                    .status(HttpStatus.ACCEPTED)
                                    .message("건물 정보가 삭제되었습니다.")
                                    .build();
        return new ResponseEntity<>(response,response.getStatus());
    }

    @ApiOperation(
        value="부동산 매매/임대 정보 추가", 
        notes="CREATED"
    )
    @Transactional
    @PutMapping("/post/add")
    public ResponseEntity<Response> insertRealtyTotalInfo(@RequestBody RealtyTotalInfoVO request){
        service.insertRealtyTotalInfo(request);
        Response response = Response.builder()
                                    .status(HttpStatus.CREATED)
                                    .message("부동산 매매/임대 정보를 추가하였습니다.")
                                    .build();
        return new ResponseEntity<>(response,response.getStatus());
    }

    @ApiOperation(
        value="부동산 매매/임대 정보 수정", 
        notes="ACCEPTED/BAD_REQUEST"
    )
    @Transactional
    @PatchMapping("/update/{type}")
    public ResponseEntity<Response> updateRealtyTotalInfo(
        @PathVariable String type, @RequestBody RealtyTotalInfoVO request
        ){
            // seq 확인 필요 = 존재하지 않는 seq 업데이트 방지
            // RealtyTotalInfoVO 업데이트 키 처리
            String msg = "";
            if(type.equals("basic")){
                msg = service.updateRealtyBasicInfo(request.getBasic_info());
            }
            else if(type.equals("option")){
                msg = service.updateRealtyOptionInfo(request.getOption_info());
            }
            else if(type.equals("post")){
                msg = service.updateRealtyPostInfo(request.getPost_info());
            }
            else if(type.equals("all")){
                msg = service.updateRealtyAllInfo(request);
            }
            else{
                //exception 처리
                Response response = Response.builder()
                                            .status(HttpStatus.BAD_REQUEST)
                                            .message("타입 정보가 잘못되었습니다. (type:[basic,option,post,all])")
                                            .build();
                return new ResponseEntity<>(response,response.getStatus());
            }
            Response response = Response.builder()
                                        .status(HttpStatus.ACCEPTED)
                                        .message(msg)
                                        .build();
            return new ResponseEntity<>(response,response.getStatus());
    }

    @ApiOperation(
        value="부동산 관리비 항목 추가", 
        notes="CREATED"
    )
    @PutMapping("/maintain/add")
    public ResponseEntity<Response> insertRealtyMaintainItem(@RequestParam String name){
        service.insertRealtyMaintainItem(name);
        Response response = Response.builder()
                                    .status(HttpStatus.CREATED)
                                    .message("부동산 관리비 항목을 추가하였습니다.")
                                    .build();
        return new ResponseEntity<>(response,response.getStatus());
    }

    @ApiOperation(
        value="부동산 관리비 항목 조회", 
        notes="OK/EXISTED_MAINTAIN_ITEM"
    )
    @GetMapping("/maintain/list")
    public ResponseEntity<Response> selectMaintainList(){
        Response response = Response.builder()
                                    .status(HttpStatus.OK)
                                    .message("부동산 관리비 항목 전체를 조회합니다.")
                                    .result(service.selectMaintainList())
                                    .count(service.selectMaintainList().size())
                                    .build();
        return new ResponseEntity<>(response,response.getStatus());
    }

    @ApiOperation(
        value="부동산 관리비 항목 삭제", 
        notes="OK/NOT_EXISTED_MAINTAIN_ITEM"
    )
    @DeleteMapping("/maintain/delete")
    public ResponseEntity<Response> deleteMaintainItem(@RequestParam String name){
        service.deleteMaintainItem(name);
        Response response = Response.builder()
                                    .status(HttpStatus.OK)
                                    .message("부동산 관리비 항목을 삭제했습니다.")
                                    .build();
        return new ResponseEntity<>(response,response.getStatus());
    }
    
    @ApiOperation(
        value="부동산 매매/임대 게시글 검색 결과 리스트 10개 조회", 
        notes="OK"
    )
    @ApiImplicitParams({
        @ApiImplicitParam(name = "page", dataType = "Integer", required = false)
    })
    @PostMapping("/post/list")
    public ResponseEntity<Response> postPostList(@RequestBody RealtySearchRequestVO request, @RequestParam @Nullable Integer page){
        Response response = Response.builder()
                                    .status(HttpStatus.OK)
                                    .message("부동산 매매/임대 게시글을 조회하였습니다.")
                                    .result(service.selectPostList(request,page))
                                    .build();
        return new ResponseEntity<>(response,response.getStatus());
    }


    @ApiOperation(
        value="부동산 매매/임대 게시글 번호로 조회", 
        notes="OK"
    )
    @GetMapping("/post")
    public ResponseEntity<Response> getPostBySeq(@RequestParam Integer seq){
        Response response = Response.builder()
                                    .status(HttpStatus.OK)
                                    .message("부동산 매매/임대 게시글을 조회하였습니다.")
                                    .result(service.selectPostInfoBySeq(seq))
                                    .build();
        return new ResponseEntity<>(response,response.getStatus());
    }


}