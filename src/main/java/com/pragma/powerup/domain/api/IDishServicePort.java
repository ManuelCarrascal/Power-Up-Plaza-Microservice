package com.pragma.powerup.domain.api;

import com.pragma.powerup.domain.model.Dish;

public interface IDishServicePort {
    void createDish(Dish dish);
    void updateDish(Long id, Dish dish);
}
