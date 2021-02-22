package com.rest.api.model.Dto;

import com.rest.api.entity.reservation.Store;
import lombok.Data;

@Data
public class StoreResponse {
    private Long id;
    private String name;
    private int themeCount;

    public StoreResponse(Store store){
        this.id = store.getStore_id();
        this.name = store.getName();
        this.themeCount = store.getThemes().size();
    }
}
