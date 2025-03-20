package com.pragma.powerup.domain.usecase;

import com.pragma.powerup.domain.api.IRestaurantServicePort;
import com.pragma.powerup.domain.exception.DoesNotOwnerException;
import com.pragma.powerup.domain.exception.RestaurantNotFoundException;
import com.pragma.powerup.domain.exception.UserIsNotOwnerOfRestaurantException;
import com.pragma.powerup.domain.model.Restaurant;
import com.pragma.powerup.domain.spi.IRestaurantPersistencePort;
import com.pragma.powerup.domain.spi.IUserPersistencePort;
import com.pragma.powerup.domain.utils.constants.UserUseCaseConstants;
import com.pragma.powerup.domain.utils.validators.RestaurantValidator;

public class RestaurantUseCase implements IRestaurantServicePort {
    private final IRestaurantPersistencePort restaurantPersistencePort;
    private final RestaurantValidator restaurantValidator;
    private final IUserPersistencePort userPersistencePort;

    public RestaurantUseCase(IRestaurantPersistencePort restaurantPersistencePort, RestaurantValidator restaurantValidator, IUserPersistencePort userPersistencePort) {
        this.restaurantPersistencePort = restaurantPersistencePort;
        this.restaurantValidator = restaurantValidator;
        this.userPersistencePort = userPersistencePort;
    }

    @Override
    public void createRestaurant(Restaurant restaurant) {
        restaurantValidator.validate(restaurant);
        if (!userPersistencePort.isOwner(restaurant.getIdOwner())) {
            throw new DoesNotOwnerException(UserUseCaseConstants.DOES_NOT_HAVE_OWNER_OWNER_ROLE);
        }
        restaurantPersistencePort.createRestaurant(restaurant);
    }

    @Override
    public void createEmployee(Long userId, Long restaurantId) {
        Restaurant restaurant = restaurantPersistencePort.findById(restaurantId)
                .orElseThrow(() -> new RestaurantNotFoundException(UserUseCaseConstants.RESTAURANT_NOT_FOUND));

        if (!userPersistencePort.isOwner(restaurant.getIdOwner())) {
            throw new UserIsNotOwnerOfRestaurantException(UserUseCaseConstants.USER_IS_NOT_OWNER_OF_RESTAURANT);
        }

        restaurantPersistencePort.addEmployeeToRestaurant(userId, restaurantId);
    }

    @Override
    public boolean isOwnerOfRestaurant(Long userId, Long restaurantId) {
        return restaurantPersistencePort.isOwnerOfRestaurant(userId, restaurantId);

    }

}
