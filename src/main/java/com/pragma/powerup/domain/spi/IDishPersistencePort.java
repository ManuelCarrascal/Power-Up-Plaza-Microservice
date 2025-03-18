package com.pragma.powerup.domain.spi;

import com.pragma.powerup.domain.model.Dish;


public interface IDishPersistencePort {
    void createDish(Dish dish);
    Dish findDishById(Long id);
    void updateDish(Dish dish);
}
