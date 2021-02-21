package com.rest.api.model.Dto;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
public class UserResponse{
    private String name;
    private String uid;

    public UserResponse(String _name, String _uid){
        name = _name;
        uid = _uid;
    }
}