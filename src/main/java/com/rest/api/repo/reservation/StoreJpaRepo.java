package com.rest.api.repo.reservation;

import com.rest.api.entity.reservation.Review;
import com.rest.api.entity.reservation.Store;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StoreJpaRepo extends JpaRepository<Store, Long> {

}
