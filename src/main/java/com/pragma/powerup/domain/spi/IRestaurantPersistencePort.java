package com.pragma.powerup.domain.spi;

import com.pragma.powerup.domain.model.Pagination;
import com.pragma.powerup.domain.model.Restaurant;

import java.util.Optional;

public interface IRestaurantPersistencePort {
    void createRestaurant(Restaurant restaurant);

    Optional<Restaurant> findById(Long restaurantId);

    void addEmployeeToRestaurant(Long userId, Long restaurantId);

    boolean isOwnerOfRestaurant(Long ownerId, Long restaurantId);

    Pagination<Restaurant> listRestaurants(String orderDirection, Integer currentPage, Integer limitForPage);
}
