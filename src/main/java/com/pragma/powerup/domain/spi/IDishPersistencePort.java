package com.pragma.powerup.domain.spi;

import com.pragma.powerup.domain.model.Dish;
import com.pragma.powerup.domain.model.Pagination;


public interface IDishPersistencePort {
    void createDish(Dish dish);

    Dish findDishById(Long id);

    void updateDish(Dish dish);

    void changeDishStatus(Dish dish, Long idOwner);

    Pagination<Dish> listDishes(Long idRestaurant, Long categoryId, Boolean active,
                                String orderDirection, Integer currentPage, Integer limitForPage);

    Long getRestaurantIdByDishId(Long dishId);
}
