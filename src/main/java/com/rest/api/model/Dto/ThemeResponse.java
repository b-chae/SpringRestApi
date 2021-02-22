package com.rest.api.model.Dto;

import com.rest.api.entity.reservation.Category;
import com.rest.api.entity.reservation.Theme;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Data
@RequiredArgsConstructor
public class ThemeResponse {
    private Long id;
    private String name;
    private StoreResponse store;
    private List<CategoryResponse> categories = new ArrayList<>();

    public ThemeResponse(Theme theme){
        this.id = theme.getTheme_id();
        this.name = theme.getName();
        this.store = new StoreResponse(theme.getStore());
        this.categories = theme.getCategories().stream().map(c -> new CategoryResponse(c)).collect(Collectors.toList());
    }
}
