package com.rest.api.entity.reservation;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

@Entity
@RequiredArgsConstructor
@Getter
public class Theme {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long theme_id;

    @Column(name="name", nullable = false)
    private String name;

    @ManyToOne(targetEntity = Store.class, fetch = FetchType.LAZY)
    @JoinColumn(name = "store_id", nullable = false)
    private Store store;

    @OneToMany(mappedBy = "theme")
    private List<Review> reviews = new ArrayList<>();

    @ManyToMany
    @JoinTable(name="theme_category",
            joinColumns = @JoinColumn(name = "theme_id"),
            inverseJoinColumns = @JoinColumn(name = "category_id"))
    private List<Category> categories = new ArrayList<>();

    public Theme(String name, Store store){
        this.name = name;
        this.store = store;
    }

    public void addReview(Review review){
        reviews.add(review);
    }

    public double getRatings(){
        if(reviews.isEmpty()){
            return 0.0;
        }

        int total = 0;
        for(Review review : reviews){
            total += review.getRating();
        }

        return total/reviews.size();
    }

    public void addCategory(Category category){
        categories.add(category);
        category.addCount();
    }

    public void setUpdate(String name, Store store){
        this.name = name;
        this.store = store;
    }
}
