package com.rest.api.controller.v1.store;

import com.rest.api.controller.v1.SignController;
import com.rest.api.entity.User;
import com.rest.api.entity.reservation.Store;
import com.rest.api.model.Dto.StoreResponse;
import com.rest.api.model.Dto.UserResponse;
import com.rest.api.model.response.ListResult;
import com.rest.api.model.response.SingleResult;
import com.rest.api.service.ResponseService;
import com.rest.api.service.StoreService;
import io.swagger.annotations.*;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@Api(tags = {"4. Store"})
@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/v1")
public class StoreController {

    private final StoreService storeService;
    private final ResponseService responseService;

    @ApiImplicitParams({
            @ApiImplicitParam(name = "X-AUTH-TOKEN", value = "로그인 성공 후 access_token", required = true, dataType = "String", paramType = "header")
    })
    @ApiOperation(value = "상점 리스트 조회", notes = "모든 상점을 조회한다")
    @GetMapping(value = "/stores")
    public ListResult<StoreResponse> findAllStores() {
        List<Store> stores = storeService.findAll();
        List<StoreResponse> res = stores.stream()
                .map(s->new StoreResponse(s))
                .collect(Collectors.toList());
        return responseService.getListResult(res);
    }

    @ApiImplicitParams({
            @ApiImplicitParam(name = "X-AUTH-TOKEN", value = "로그인 성공 후 access_token", required = true, dataType = "String", paramType = "header")
    })
    @ApiOperation(value = "상점 추가", notes = "새로운 상점을 추가한다")
    @PostMapping(value = "/stores")
    public SingleResult<StoreResponse> saveStore(
            @ApiParam(value="상점의 이름", required=true) @RequestBody StoreRequest storeRequest) {
        Store store = new Store(storeRequest.getName());
        storeService.save(store);
        return responseService.getSingleResult(new StoreResponse(store));
    }

    @ApiImplicitParams({
            @ApiImplicitParam(name = "X-AUTH-TOKEN", value = "로그인 성공 후 access_token", required = true, dataType = "String", paramType = "header")
    })
    @ApiOperation(value = "특정 상점 조회", notes = "특점 상점을 조회한다")
    @GetMapping(value = "/stores/{storeId}")
    public SingleResult<StoreResponse> findStore(
            @ApiParam(value="상점 아이디", required=true) @PathVariable Long storeId) {

        Store store = storeService.findStore(storeId);
        return responseService.getSingleResult(new StoreResponse(store));
    }

    @Data
    @RequiredArgsConstructor
    static class StoreRequest {
        private String name;
    }
}
