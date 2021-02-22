package com.rest.api.entity.reservation;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@RequiredArgsConstructor
@Getter
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long category_id;

    @Column(nullable = false, length = 50)
    private String name;

    @Column(name = "count")
    private Long count = 0L;

    @ManyToMany(mappedBy = "categories")
    private List<Theme> themes = new ArrayList<>();

    public Category(String name){
        this.name = name;
    }

    public void addCount() {
        count++;
    }
}
