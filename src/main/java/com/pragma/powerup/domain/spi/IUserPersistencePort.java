package com.pragma.powerup.domain.spi;

public interface IUserPersistencePort {
    boolean isOwner(Long ownerId);

    Long getCurrentUserId();

    boolean isEmployeeOfRestaurant(Long employeeId, Long restaurantId);

}
