package com.rest.api.entity.reservation;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@RequiredArgsConstructor
public class Store {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long store_id;

    @Column(name = "name")
    private String name;

    @OneToMany(mappedBy = "store")
    private List<Theme> themes = new ArrayList<>();

    public Store(String name){
        this.name = name;
    }

    public void addTheme(Theme theme){
        themes.add(theme);
    }
}
