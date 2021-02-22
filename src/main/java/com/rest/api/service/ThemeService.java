package com.rest.api.service;

import com.rest.api.advice.exception.CResourceNotExistException;
import com.rest.api.entity.reservation.Category;
import com.rest.api.entity.reservation.Store;
import com.rest.api.entity.reservation.Theme;
import com.rest.api.repo.reservation.StoreJpaRepo;
import com.rest.api.repo.reservation.ThemeJpaRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
public class ThemeService {

    private final ThemeJpaRepo themeJpaRepo;

    public Theme findTheme(Long id) {
        return themeJpaRepo.findById(id).orElseThrow(CResourceNotExistException::new);
    }

    public List<Theme> findAll() {
        return themeJpaRepo.findAll();
    }

    public Theme save(Theme theme){
        return themeJpaRepo.save(theme);
    }
}
