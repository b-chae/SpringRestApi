package com.rest.api.repo.reservation;

import com.rest.api.entity.reservation.Category;
import com.rest.api.entity.reservation.Store;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryJpaRepo extends JpaRepository<Category, Long> {

}
