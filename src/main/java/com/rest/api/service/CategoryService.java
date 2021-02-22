package com.rest.api.service;

import com.rest.api.advice.exception.CResourceNotExistException;
import com.rest.api.entity.reservation.Category;
import com.rest.api.entity.reservation.Theme;
import com.rest.api.repo.reservation.CategoryJpaRepo;
import com.rest.api.repo.reservation.ThemeJpaRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
public class CategoryService {

    private final CategoryJpaRepo categoryJpaRepo;

    public Category findCategory(Long id) {
        return categoryJpaRepo.findById(id).orElseThrow(CResourceNotExistException::new);
    }

    public List<Category> findAll(){
        return categoryJpaRepo.findAll();
    }

    public Category save(Category category){
        return categoryJpaRepo.save(category);
    }

}
