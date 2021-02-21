package com.rest.api.model.Dto;

import com.rest.api.entity.board.Board;
import lombok.Data;

@Data
public class BoardResponse {
    private Long id;
    private String name;
    private int postCount;

    public BoardResponse(Board board){
        id = board.getBoardId();
        name = board.getName();
        postCount = board.getPosts().size();
    }
}
