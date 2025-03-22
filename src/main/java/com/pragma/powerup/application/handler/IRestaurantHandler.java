package com.pragma.powerup.application.handler;

import com.pragma.powerup.application.dto.request.RestaurantListRequestDto;
import com.pragma.powerup.application.dto.request.RestaurantRequestDto;
import com.pragma.powerup.application.dto.response.RestaurantListResponseDto;
import com.pragma.powerup.domain.model.Pagination;

public interface IRestaurantHandler {
    void createRestaurant(RestaurantRequestDto restaurantRequestDto);
    void createEmployee(Long userId, Long restaurantId);
    boolean isOwnerOfRestaurant(Long userId, Long restaurantId);
    Pagination<RestaurantListResponseDto> restaurantList(RestaurantListRequestDto restaurantListRequestDto);
}
