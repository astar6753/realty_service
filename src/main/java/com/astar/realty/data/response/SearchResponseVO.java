package com.astar.realty.data.response;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class SearchResponseVO {
    private Integer currentPage;
    private Object searchRequest;
    private Object searchResult;
    private Integer totalCnt;
    private Integer pageCnt;
}
