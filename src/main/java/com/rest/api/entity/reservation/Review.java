package com.rest.api.entity.reservation;

import com.rest.api.entity.User;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;

@Entity
@RequiredArgsConstructor
@Getter
public class Review {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long review_id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user", nullable = false)
    private User user;

    @Column(name = "text", nullable = false)
    private String text;

    @Min(value = 0L)
    @Max(value = 5L)
    @Column(name = "rating", nullable = false)
    private int rating;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="theme_id")
    private Theme theme;

    public Review(User user, String text, int rating, Theme theme){
        this.user = user;
        this.text = text;
        this.rating = rating;
        this.theme = theme;
    }

}
