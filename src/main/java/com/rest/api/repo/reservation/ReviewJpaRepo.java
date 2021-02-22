package com.rest.api.repo.reservation;

import com.rest.api.entity.reservation.Review;
import com.rest.api.entity.reservation.Theme;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ReviewJpaRepo extends JpaRepository<Review, Long> {
}
