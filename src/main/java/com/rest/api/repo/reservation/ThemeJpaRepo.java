package com.rest.api.repo.reservation;

import com.rest.api.entity.reservation.Category;
import com.rest.api.entity.reservation.Theme;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ThemeJpaRepo extends JpaRepository<Theme, Long> {

}
