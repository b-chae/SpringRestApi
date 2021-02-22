package com.rest.api.controller.v1.theme;

import com.rest.api.entity.reservation.Category;
import com.rest.api.entity.reservation.Store;
import com.rest.api.entity.reservation.Theme;
import com.rest.api.model.Dto.CategoryResponse;
import com.rest.api.model.Dto.ThemeResponse;
import com.rest.api.model.response.ListResult;
import com.rest.api.model.response.SingleResult;
import com.rest.api.service.CategoryService;
import com.rest.api.service.ResponseService;
import com.rest.api.service.StoreService;
import com.rest.api.service.ThemeService;
import io.swagger.annotations.*;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.stream.Collectors;

@Api(tags = {"5-2. Theme"})
@RestController
@RequiredArgsConstructor
@RequestMapping("/v1")
public class ThemeController {

    private final ThemeService themeService;
    private final StoreService storeService;
    private final CategoryService categoryService;
    private final ResponseService responseService;

    @ApiImplicitParams({
            @ApiImplicitParam(name = "X-AUTH-TOKEN", value = "로그인 성공 후 access_token", required = true, dataType = "String", paramType = "header")
    })
    @ApiOperation(value = "테마 리스트 조회", notes = "모든 태마를 조회한다")
    @GetMapping(value = "/themes")
    public ListResult<ThemeResponse> findAllThemes() {
        List<Theme> themes = themeService.findAll();
        List<ThemeResponse> res = themes.stream()
                .map(t->new ThemeResponse(t))
                .collect(Collectors.toList());
        return responseService.getListResult(res);
    }

    @ApiImplicitParams({
            @ApiImplicitParam(name = "X-AUTH-TOKEN", value = "로그인 성공 후 access_token", required = true, dataType = "String", paramType = "header")
    })
    @ApiOperation(value = "특정 테마 상세 조회", notes = "특정 테마를 조회한다")
    @GetMapping(value = "/themes/{themeId}")
    public SingleResult<ThemeResponse> findThmeme(
            @ApiParam(value="테마 정보", required=true) @PathVariable Long id
    ) {
        Theme theme = themeService.findTheme(id);
        return responseService.getSingleResult(new ThemeResponse(theme));
    }

    @ApiImplicitParams({
            @ApiImplicitParam(name = "X-AUTH-TOKEN", value = "로그인 성공 후 access_token", required = true, dataType = "String", paramType = "header")
    })
    @ApiOperation(value = "테마 추가", notes = "테마를 추가한다")
    @PostMapping(value = "/themes")
    public SingleResult<ThemeResponse> saveTheme(
            @ApiParam(value="테마 정보", required=true) @Valid @RequestBody ThemeRequest request
    ) {
        Store store = storeService.findStore(request.getStore_id());
        Theme theme = new Theme(request.getName(), store);
        store.addTheme(themeService.save(theme));
        return responseService.getSingleResult(new ThemeResponse(theme));
    }

    @ApiImplicitParams({
            @ApiImplicitParam(name = "X-AUTH-TOKEN", value = "로그인 성공 후 access_token", required = true, dataType = "String", paramType = "header")
    })
    @ApiOperation(value = "테마 업데이트", notes = "테마 상세 정보를 업데이트한다")
    @PostMapping(value = "/themes/{themeId}")
    public SingleResult<ThemeResponse> saveTheme(
            @ApiParam(value="테마 아이디", required=true) @PathVariable Long themeId,
            @ApiParam(value="테마 정보", required=true) @Valid @RequestBody ThemeUpdateRequest request
    ) {
        Store store = storeService.findStore(request.getStore_id());
        Theme theme = themeService.findTheme(themeId);
        theme.setUpdate(request.getName(), store);
        for(Long category_id:request.getCategories_id()){
            theme.addCategory(categoryService.findCategory(category_id));
        }
        return responseService.getSingleResult(new ThemeResponse(themeService.save(theme)));
    }

    @Data
    static class ThemeRequest {

        @NotEmpty
        @NotNull
        private String name;

        @NotNull
        private Long store_id;
    }

    @Data
    static class ThemeUpdateRequest {

        @NotEmpty
        private String name;

        @NotNull
        private Long store_id;

        private List<Long> categories_id;
    }
}
