package com.pragma.powerup.application.handler;

import com.pragma.powerup.application.dto.request.RestaurantRequestDto;

public interface IRestaurantHandler {
    void createRestaurant(RestaurantRequestDto restaurantRequestDto);
    void createEmployee(Long userId, Long restaurantId);
    boolean isOwnerOfRestaurant(Long userId, Long restaurantId);

}
