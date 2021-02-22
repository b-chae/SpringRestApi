package com.rest.api.service;

import com.rest.api.advice.exception.CResourceNotExistException;
import com.rest.api.entity.reservation.Review;
import com.rest.api.entity.reservation.Theme;
import com.rest.api.repo.reservation.ReviewJpaRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
public class ReviewService {

    private final ReviewJpaRepo reviewJpaRepo;

    public Review findReview(Long id){
        return reviewJpaRepo.findById(id).orElseThrow(CResourceNotExistException::new);
    }

}
