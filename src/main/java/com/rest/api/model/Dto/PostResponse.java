package com.rest.api.model.Dto;

import com.rest.api.entity.User;
import com.rest.api.entity.board.Board;
import com.rest.api.entity.board.Post;
import lombok.Data;

@Data
public class PostResponse {

    private Long postId;
    private String author;
    private String title;
    private String content;

    private BoardResponse board; // 게시글 - 게시판의 관계 - N:1

    private UserResponse user;  // 게시글 - 회원의 관계 - N:1

    public PostResponse(Post post){
        postId = post.getPostId();
        author = post.getAuthor();
        title = post.getTitle();
        content = post.getContent();
        board = new BoardResponse(post.getBoard());
        user = new UserResponse(post.getUser().getName(), post.getUser().getUid());
    }
}
