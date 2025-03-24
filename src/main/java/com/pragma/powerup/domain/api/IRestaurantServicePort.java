package com.pragma.powerup.domain.api;

import com.pragma.powerup.domain.model.Pagination;
import com.pragma.powerup.domain.model.Restaurant;

public interface IRestaurantServicePort {
    void createRestaurant(Restaurant restaurant);
    void createEmployee(Long userId,Long restaurantId);
    boolean isOwnerOfRestaurant(Long userId, Long restaurantId);
    Pagination<Restaurant> restaurantList(String orderDirection, Integer currentPage, Integer limitForPage);
}
