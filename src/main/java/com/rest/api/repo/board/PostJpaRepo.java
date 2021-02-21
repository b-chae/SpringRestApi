package com.rest.api.repo.board;

import com.rest.api.entity.board.Board;
import com.rest.api.entity.board.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

public interface PostJpaRepo extends JpaRepository<Post, Long> {
    List<Post> findByBoardOrderByPostIdDesc(Board board);

    /*@Query("select distinct p from post p join fetch p.user join fetch p.board where p.id = %?1%")
    Post findByIdWithUserAndBoardCustom(Long id);*/
}