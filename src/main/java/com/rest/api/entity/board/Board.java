package com.rest.api.entity.board;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.rest.api.entity.common.CommonDateEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Builder
@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Board extends CommonDateEntity implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long boardId;

    @Column(nullable = false, length = 100)
    private String name;

    @JsonIgnore
    @OneToMany(mappedBy = "board")
    @Column(name="posts")
    private List<Post> posts = new ArrayList<>();
}
