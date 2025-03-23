package com.pragma.powerup.domain.usecase;

import com.pragma.powerup.domain.api.IRestaurantServicePort;
import com.pragma.powerup.domain.exception.DoesNotOwnerException;
import com.pragma.powerup.domain.exception.RestaurantNotFoundException;
import com.pragma.powerup.domain.exception.UserIsNotOwnerOfRestaurantException;
import com.pragma.powerup.domain.model.Pagination;
import com.pragma.powerup.domain.model.Restaurant;
import com.pragma.powerup.domain.spi.IRestaurantPersistencePort;
import com.pragma.powerup.domain.spi.IUserPersistencePort;
import com.pragma.powerup.domain.utils.constants.RestaurantUseCaseConstants;
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

    @Override
    public Pagination<Restaurant> restaurantList(String orderDirection, Integer currentPage, Integer limitForPage) {
        String normalizedDirection = validateAndNormalizeOrderDirection(orderDirection);
        return restaurantPersistencePort.listRestaurants(
                normalizedDirection,
                currentPage,
                limitForPage
        );
    }

    private String validateAndNormalizeOrderDirection(String orderDirection) {
        if (!orderDirection.equalsIgnoreCase(RestaurantUseCaseConstants.ORDER_DIRECTION_ASC) &&
                !orderDirection.equalsIgnoreCase(RestaurantUseCaseConstants.ORDER_DIRECTION_DESC)) {
            throw new IllegalArgumentException(RestaurantUseCaseConstants.INVALID_ORDER_DIRECTION_MESSAGE);
        }
        return orderDirection.toUpperCase();
    }

}
