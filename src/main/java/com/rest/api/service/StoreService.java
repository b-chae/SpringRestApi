package com.rest.api.service;

import com.rest.api.advice.exception.CResourceNotExistException;
import com.rest.api.entity.reservation.Store;
import com.rest.api.repo.reservation.StoreJpaRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
public class StoreService {

    private final StoreJpaRepo storeJpaRepo;

    public Store findStore(Long id){
        return storeJpaRepo.findById(id).orElseThrow(CResourceNotExistException::new);
    }

    public Store save(Store store){
        return storeJpaRepo.save(store);
    }

    public List<Store> findAll(){
        return storeJpaRepo.findAll();
    }

}
