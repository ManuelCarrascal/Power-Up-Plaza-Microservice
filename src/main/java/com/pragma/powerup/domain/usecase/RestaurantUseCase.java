package com.pragma.powerup.domain.usecase;

import com.pragma.powerup.domain.api.IRestaurantServicePort;
import com.pragma.powerup.domain.model.Restaurant;
import com.pragma.powerup.domain.spi.IRestaurantPersistencePort;
import com.pragma.powerup.domain.utils.validators.RestaurantValidator;

public class RestaurantUseCase implements IRestaurantServicePort {
    private final IRestaurantPersistencePort restaurantPersistencePort;
    private final RestaurantValidator restaurantValidator;

    public RestaurantUseCase(IRestaurantPersistencePort restaurantPersistencePort, RestaurantValidator restaurantValidator) {
        this.restaurantPersistencePort = restaurantPersistencePort;
        this.restaurantValidator = restaurantValidator;
    }

    @Override
    public void createRestaurant(Restaurant restaurant) {
        restaurantValidator.validate(restaurant);
        restaurantPersistencePort.createRestaurant(restaurant);
    }
}
