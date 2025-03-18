package com.pragma.powerup.infrastructure.out.jpa.adapter;

import com.pragma.powerup.domain.model.Category;
import com.pragma.powerup.domain.spi.ICategoryPersistencePort;
import com.pragma.powerup.infrastructure.out.jpa.mapper.ICategoryEntityMapper;
import com.pragma.powerup.infrastructure.out.jpa.repository.ICategoryRepository;

import java.util.List;
import java.util.stream.Collectors;

public class CategoryJpaAdapter implements ICategoryPersistencePort {

    private final ICategoryRepository categoryRepository;
    private final ICategoryEntityMapper categoryEntityMapper;

    public CategoryJpaAdapter(ICategoryRepository categoryRepository, ICategoryEntityMapper categoryEntityMapper) {
        this.categoryRepository = categoryRepository;
        this.categoryEntityMapper = categoryEntityMapper;
    }

    @Override
    public List<Category> getCategoriesByNames(List<Category> categories) {
        return categories.stream()
                .map(category -> categoryRepository.findByName(category.getName())
                        .map(categoryEntityMapper::toDomain)
                        .orElseGet(() -> categoryEntityMapper.toDomain(
                                categoryRepository.save(categoryEntityMapper.toEntity(category)))
                        ))
                .collect(Collectors.toList());
    }
}
