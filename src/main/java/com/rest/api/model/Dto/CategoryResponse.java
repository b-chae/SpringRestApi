package com.rest.api.model.Dto;

import com.rest.api.entity.reservation.Category;
import lombok.Data;

@Data
public class CategoryResponse {
    private Long id;
    private String name;
    private Long themeCount = 0L;

    public CategoryResponse(Category category){
        this.id = category.getCategory_id();
        this.name = category.getName();
        this.themeCount = category.getCount();
    }
}
