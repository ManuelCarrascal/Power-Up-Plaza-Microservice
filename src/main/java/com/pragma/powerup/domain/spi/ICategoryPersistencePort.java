package com.pragma.powerup.domain.spi;

import com.pragma.powerup.domain.model.Category;

import java.util.List;

public interface ICategoryPersistencePort {
    List<Category> getCategoriesByNames(List<Category> categories);
}
