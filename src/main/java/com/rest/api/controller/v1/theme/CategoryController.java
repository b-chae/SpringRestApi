package com.rest.api.controller.v1.theme;

import com.rest.api.controller.v1.store.StoreController;
import com.rest.api.entity.reservation.Category;
import com.rest.api.entity.reservation.Store;
import com.rest.api.model.Dto.CategoryResponse;
import com.rest.api.model.Dto.StoreResponse;
import com.rest.api.model.response.ListResult;
import com.rest.api.model.response.SingleResult;
import com.rest.api.service.CategoryService;
import com.rest.api.service.ResponseService;
import io.swagger.annotations.*;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@Api(tags = {"5-1. Category"})
@RestController
@RequiredArgsConstructor
@RequestMapping("/v1")
public class CategoryController {

    private final CategoryService categoryService;
    private final ResponseService responseService;

    @ApiImplicitParams({
            @ApiImplicitParam(name = "X-AUTH-TOKEN", value = "로그인 성공 후 access_token", required = true, dataType = "String", paramType = "header")
    })
    @ApiOperation(value = "카테고리 리스트 조회", notes = "모든 카테고리를 조회한다")
    @GetMapping(value = "/categories")
    public ListResult<CategoryResponse> findAllCategories() {
        List<Category> categories = categoryService.findAll();
        List<CategoryResponse> res = categories.stream()
                .map(c->new CategoryResponse(c))
                .collect(Collectors.toList());
        return responseService.getListResult(res);
    }

    @ApiImplicitParams({
            @ApiImplicitParam(name = "X-AUTH-TOKEN", value = "로그인 성공 후 access_token", required = true, dataType = "String", paramType = "header")
    })
    @ApiOperation(value = "카테고리 리스트 조회", notes = "모든 카테고리를 조회한다")
    @PostMapping(value = "/categories")
    public SingleResult<CategoryResponse> saveCategory(
            @ApiParam(value="카테고리 이름", required=true) @RequestBody CategoryRequest request) {
        Category category = new Category(request.getName());
        CategoryResponse res = new CategoryResponse(categoryService.save(category));
        return responseService.getSingleResult(res);
    }

    @Data
    @RequiredArgsConstructor
    static class CategoryRequest {
        private String name;
    }
}
