package com.astar.realty.domain.realty.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.astar.realty.domain.realty.application.RealtyService;
import com.astar.realty.domain.realty.data.BuildingCreate;
import com.astar.realty.domain.realty.data.BuildingModify;
import com.astar.realty.domain.realty.data.BuildingSearch;
import com.astar.realty.domain.realty.data.RealtyMaintainShow;
import com.astar.realty.domain.realty.data.RealtyPostCreate;
import com.astar.realty.domain.realty.data.RealtyPostModify;
import com.astar.realty.domain.realty.data.RealtyPostSearch;
import com.astar.realty.global.common.Response;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@RequestMapping("/api")
public class RealtyAPIController {
    @Autowired RealtyService service;

    @ApiOperation(value="건물 등록", notes="building info CRUD권한 체크 필요")
    @ApiResponses({
        @ApiResponse(code = 201, message = "CREATED 건물 정보가 등록되었습니다."),
        @ApiResponse(code = 400, message = "BUILDING_ADDRESS_DUPICATION 이미 등록된 주소지입니다.")
    })
    @PutMapping("/building")
    public ResponseEntity<BuildingCreate.Response> createBuildingInfo(
        @RequestBody BuildingCreate.Request request
        ) {
            BuildingCreate.Response response = BuildingCreate.Response.builder()
                                        .status(HttpStatus.CREATED)
                                        .message("건물 정보가 등록되었습니다.")
                                        .result(service.createBuildingInfo(request))
                                        .build();
            return new ResponseEntity<>(response,response.getStatus());
    }

    @ApiOperation(value="건물 주소 중복 체크", notes="")
    @ApiResponses({
        @ApiResponse(code = 201, message = "CREATED 건물 정보가 등록되었습니다."),
        @ApiResponse(code = 400, message = "BUILDING_ADDRESS_DUPICATION 이미 등록된 주소지입니다.")
    })
    @GetMapping("/building/check")
    public ResponseEntity<Response> checkBuildingDuplication(@RequestParam String address) {
        service.checkBuildingDuplication(address);
        Response response = Response.builder()
                                    .status(HttpStatus.OK)
                                    .message("아직 등록되지 않은 주소지입니다.")
                                    .build();
        return new ResponseEntity<>(response,response.getStatus());
    }

    @ApiOperation(value="건물 정보 조회", notes="건물 이름으로 건물 정보 조회")
    @ApiResponse(code = 200, message = "OK 건물 정보가 조회되었습니다.")
    @PostMapping("/building/list")
    public ResponseEntity<BuildingSearch.Response> searchBuildingInfoList(
        @RequestBody BuildingSearch.Request request
        ) {
        BuildingSearch.Response response = BuildingSearch.Response.builder()
                                    .status(HttpStatus.OK)
                                    .message("건물 정보가 조회되었습니다.")
                                    .result(service.searchBuildingInfoList(request))
                                    .build();
        return new ResponseEntity<>(response,response.getStatus());
    }

    //오버페칭
    @ApiOperation(value="건물 정보 수정", notes="건물 번호로 건물 정보 수정")
    @ApiResponses({
        @ApiResponse(code = 200, message = "OK 건물 정보가 수정되었습니다."),
        @ApiResponse(code = 400, message = "NOT_EXIST_BUILDING_ADDRESS 존재하지 않는 주소지입니다. / NOT_EXIST_BUILDING_NO 존재하지 않는 건물 번호입니다.")
    })
    @PatchMapping("/building")
    public ResponseEntity<BuildingModify.Response> modifyBuildingInfo(
        @RequestBody BuildingModify.Request request
        ) {
            BuildingModify.Response response = BuildingModify.Response.builder()
                                        .status(HttpStatus.OK)
                                        .message("건물 정보가 수정되었습니다.")
                                        .result(service.modifyBuildingInfo(request))
                                        .build();
            return new ResponseEntity<>(response,response.getStatus());
    }

    @ApiOperation(value="건물 정보 삭제",notes="건물 번호로 건물 정보 삭제")
    @ApiResponses({
        @ApiResponse(code = 200, message = "OK 건물 정보가 삭제되었습니다."),
        @ApiResponse(code = 400, message = "NOT_EXIST_BUILDING_NO 존재하지 않는 건물 번호입니다.")
    })
    @DeleteMapping("/building/no:{building_no}")
    public ResponseEntity<Response> removeBuildingInfoBySeq(
        @ApiParam(value="건물 번호", example="1")
        @PathVariable Integer building_no
        ) {
            Response response = Response.builder()
                                        .status(HttpStatus.OK)
                                        .message("건물 정보가 삭제되었습니다.")
                                        .result(service.removeBuildingInfoBySeq(building_no))
                                        .build();
            return new ResponseEntity<>(response,response.getStatus());
    }

    //broker번호 세션에서 받아오기
    @ApiOperation(value="부동산 매물 게시글 등록", notes="매물기본정보(건물정보번호/매물옵션정보번호), 매물옵션정보, 게시글정보(매물기본정보번호/중개사정보번호)")
    @ApiResponses({
        @ApiResponse(code = 201, message = "CREATED 부동산 매물 게시글을 등록하였습니다."),        
        @ApiResponse(code = 400, message = "NOT_EXIST_BUILDING_NO 존재하지 않는 건물 번호입니다.")
    })
    @PutMapping("/post/realty")
    public ResponseEntity<RealtyPostCreate.Response> createRealtyPostInfo(
        @RequestBody RealtyPostCreate.Request request
        ) {
            RealtyPostCreate.Response response = RealtyPostCreate.Response.builder()
                                        .status(HttpStatus.CREATED)
                                        .message("부동산 매물 게시글을 등록하였습니다.")
                                        .result(service.createRealtyPostInfo(request))
                                        .build();
            return new ResponseEntity<>(response,response.getStatus());
    }

    @ApiOperation(value="부동산 매물 게시글 수정", notes="")
    @ApiResponses({
        @ApiResponse(code = 200, message = "OK 부동산 매물 게시글을 수정하였습니다."),        
        @ApiResponse(code = 400, message = "INVALID_TYPE 타입이 잘못되었습니다. [basic,option,post,all]/ NOT_EXIST_BUILDING_NO 존재하지 않는 건물 번호입니다. / NOT_EXIST_OPTION_NO 존재하지 않는 옵션 정보 번호입니다. / NOT_EXIST_POST_NO 존재하지 않는 매물 게시글 정보 번호입니다.")
    })
    @PatchMapping("post/realty/type:{type}")
    public ResponseEntity<Response> modifyRealtyPostTotalInfo(
        @ApiParam(value="게시글 항목", example="all", allowableValues="basic,option,post,all" )
        @PathVariable String type, 
        @RequestBody RealtyPostModify.Request request
        ){
            Response response = Response.builder()
                                        .status(HttpStatus.OK)
                                        .message("부동산 매물 게시글을 수정하였습니다.")
                                        .result(service.modifyRealtyPostTotalInfo(type, request))
                                        .build();
            return new ResponseEntity<>(response,response.getStatus());
    }

    @ApiOperation(value="부동산 관리비 항목 추가", notes="부동산 관리비 항목 추가")
    @ApiResponses({
        @ApiResponse(code = 201, message = "CREATED 부동산 관리비 항목을 추가하였습니다."),        
        @ApiResponse(code = 400, message = "MAINTAIN_NAME_DUPLICATION 같은 항목이 이미 존재합니다.")
    })
    @PutMapping("/maintain")
    public ResponseEntity<Response> createRealtyMaintainInfo(
        @ApiParam(value="관리비 항목 이름", example="인터넷")
        @RequestParam String name
        ){
            service.createRealtyMaintainInfo(name);
            Response response = Response.builder()
                                        .status(HttpStatus.CREATED)
                                        .message("부동산 관리비 항목을 추가하였습니다.")
                                        .build();
            return new ResponseEntity<>(response,response.getStatus());
    }

    @ApiOperation(value="부동산 관리비 항목 조회", notes="부동산 관리비 항목 조회")
    @ApiResponse(code = 200, message = "OK 부동산 관리비 항목 전체를 조회합니다")
    @GetMapping("/maintain/list")
    public ResponseEntity<RealtyMaintainShow.Response> showMaintainList(){
        RealtyMaintainShow.Response response = RealtyMaintainShow.Response.builder()
                                    .status(HttpStatus.OK)
                                    .message("부동산 관리비 항목 전체를 조회합니다.")
                                    .result(service.showMaintainList())
                                    .build();
        return new ResponseEntity<>(response,response.getStatus());
    }

    @ApiOperation(value="부동산 관리비 항목 삭제", notes="관리비 항목 번호로 관리비 항목 삭제")
    @ApiResponse(code = 200, message = "OK 부동산 관리비 항목을 삭제했습니다.")
    @DeleteMapping("/maintain")
    public ResponseEntity<Response> removeMaintainItemBySeq(
        @ApiParam(value="관리비 항목 번호", example="1")
        @RequestParam Integer maintain_no
        ){
            Response response = Response.builder()
                                        .status(HttpStatus.OK)
                                        .message("부동산 관리비 항목을 삭제했습니다.")
                                        .result(service.removeMaintainItemBySeq(maintain_no))
                                        .build();
            return new ResponseEntity<>(response,response.getStatus());
    }
    
    @ApiOperation(value="부동산 매물 게시글 검색", notes="부동산 매물 게시글 검색")
    @PostMapping("/post/realty/list")
    public ResponseEntity<RealtyPostSearch.Response> searchRealtyPostList(
        @RequestBody RealtyPostSearch.Request request
        ){
            RealtyPostSearch.Response response = RealtyPostSearch.Response.builder()
                                        .status(HttpStatus.OK)
                                        .message("부동산 매물 게시글을 조회하였습니다.")
                                        .result(service.searchRealtyPostList(request))
                                        .build();
            return new ResponseEntity<>(response,response.getStatus());
    }

    @ApiOperation(value="부동산 매물 게시글 조회", notes="부동산 매물 게시글 번호로 게시글 정보 조회")
    @ApiResponses({
        @ApiResponse(code = 200, message = "OK 부동산 매물 게시글을 조회하였습니다."),
        @ApiResponse(code = 400, message = "NOT_EXIST_POST_NO 존재하지 않는 게시물 번호입니다.")
    })
    @GetMapping("/post/realty")
    public ResponseEntity<Response> findRealtyPostBySeq(
        @RequestParam Integer post_no
        ) {
            Response response = Response.builder()
                                        .status(HttpStatus.OK)
                                        .message("부동산 매물 게시글을 조회하였습니다.")
                                        .result(service.findRealtyPostBySeq(post_no))
                                        .build();
            return new ResponseEntity<>(response,response.getStatus());
    }
}
