package com.pragma.powerup.domain.api;

import com.pragma.powerup.application.dto.request.RestaurantListRequestDto;
import com.pragma.powerup.domain.model.Pagination;
import com.pragma.powerup.domain.model.Restaurant;

public interface IRestaurantServicePort {
    void createRestaurant(Restaurant restaurant);
    void createEmployee(Long userId,Long restaurantId);
    boolean isOwnerOfRestaurant(Long userId, Long restaurantId);
    Pagination<Restaurant> restaurantList(RestaurantListRequestDto restaurantListRequestDto);
}
