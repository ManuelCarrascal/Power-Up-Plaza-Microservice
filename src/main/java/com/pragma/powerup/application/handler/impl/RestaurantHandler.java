package com.pragma.powerup.application.handler.impl;

import com.pragma.powerup.application.dto.request.RestaurantRequestDto;
import com.pragma.powerup.application.handler.IRestaurantHandler;
import com.pragma.powerup.application.mapper.IRestaurantRequestMapper;
import com.pragma.powerup.domain.api.IRestaurantServicePort;
import com.pragma.powerup.domain.exception.DoesNotOwnerException;
import com.pragma.powerup.domain.spi.IUserPersistencePort;
import com.pragma.powerup.domain.utils.constants.UserUseCaseConstants;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class RestaurantHandler implements IRestaurantHandler {

    private final IRestaurantServicePort restaurantServicePort;
    private final IRestaurantRequestMapper restaurantRequestMapper;
    private final IUserPersistencePort userPersistencePort;

    @Override
    public void createRestaurant(RestaurantRequestDto restaurantRequestDto) {
        if (!userPersistencePort.isOwner(restaurantRequestDto.getIdOwner())) {
            throw new DoesNotOwnerException(UserUseCaseConstants.DOES_NOT_HAVE_OWNER_OWNER_ROLE);
        }
        restaurantServicePort.createRestaurant(restaurantRequestMapper.toRestaurant(restaurantRequestDto));

    }
}
