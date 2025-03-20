package com.pragma.powerup.application.handler.impl;

import com.pragma.powerup.application.dto.request.RestaurantRequestDto;
import com.pragma.powerup.application.handler.IRestaurantHandler;
import com.pragma.powerup.application.mapper.IRestaurantRequestMapper;
import com.pragma.powerup.domain.api.IRestaurantServicePort;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class RestaurantHandler implements IRestaurantHandler {

    private final IRestaurantServicePort restaurantServicePort;
    private final IRestaurantRequestMapper restaurantRequestMapper;

    @Override
    public void createRestaurant(RestaurantRequestDto restaurantRequestDto) {
        restaurantServicePort.createRestaurant(restaurantRequestMapper.toRestaurant(restaurantRequestDto));
    }

    @Override
    public void createEmployee(Long userId, Long restaurantId) {
        restaurantServicePort.createEmployee(userId, restaurantId);
    }

    @Override
    public boolean isOwnerOfRestaurant(Long userId, Long restaurantId) {
        return restaurantServicePort.isOwnerOfRestaurant(userId, restaurantId);

    }

}
