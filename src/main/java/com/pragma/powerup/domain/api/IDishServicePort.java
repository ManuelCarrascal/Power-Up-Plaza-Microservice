package com.pragma.powerup.domain.api;

import com.pragma.powerup.domain.model.Dish;
import com.pragma.powerup.domain.model.Pagination;

public interface IDishServicePort {
    void createDish(Dish dish);
    void updateDish(Long id, Dish dish);
    void changeDishStatus(Long dishId, Boolean status);
    Pagination<Dish> listDishes(Long idRestaurant, Long idCategory, Boolean active,
                                String orderDirection, Integer currentPage, Integer limitForPage);
}
