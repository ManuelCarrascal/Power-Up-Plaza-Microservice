package com.pragma.powerup.domain.usecase;

import com.pragma.powerup.domain.exception.DoesNotOwnerException;
import com.pragma.powerup.domain.model.Restaurant;
import com.pragma.powerup.domain.spi.IRestaurantPersistencePort;
import com.pragma.powerup.domain.spi.IUserPersistencePort;
import com.pragma.powerup.domain.utils.validators.RestaurantValidator;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

class RestaurantUseCaseTest {

    @Test
    void createRestaurant_ValidInput_ShouldPersistRestaurant() {
        // Arrange
        IRestaurantPersistencePort restaurantPersistencePort = mock(IRestaurantPersistencePort.class);
        RestaurantValidator restaurantValidator = mock(RestaurantValidator.class);
        IUserPersistencePort userPersistencePort = mock(IUserPersistencePort.class);

        RestaurantUseCase restaurantUseCase = new RestaurantUseCase(restaurantPersistencePort, restaurantValidator, userPersistencePort);

        Restaurant restaurant = new Restaurant.Builder()
                .id(1L)
                .name("Test Restaurant")
                .nit("1234567890")
                .address("123 Main St")
                .phone("555-1234")
                .urlLogo("httpg://example.com/logo.jpg")
                .idOwner(1L)
                .build();

        when(userPersistencePort.isOwner(1L)).thenReturn(true);

        // Act
        restaurantUseCase.createRestaurant(restaurant);

        // Assert
        verify(restaurantValidator).validate(restaurant);
        verify(userPersistencePort).isOwner(1L);
        verify(restaurantPersistencePort).createRestaurant(restaurant);
    }

    @Test
    void createRestaurant_InvalidOwner_ShouldThrowDoesNotOwnerException() {
        // Arrange
        IRestaurantPersistencePort restaurantPersistencePort = mock(IRestaurantPersistencePort.class);
        RestaurantValidator restaurantValidator = mock(RestaurantValidator.class);
        IUserPersistencePort userPersistencePort = mock(IUserPersistencePort.class);

        RestaurantUseCase restaurantUseCase = new RestaurantUseCase(restaurantPersistencePort, restaurantValidator, userPersistencePort);

        Restaurant restaurant = new Restaurant.Builder()
                .id(1L)
                .name("Test Restaurant")
                .nit("1234567890")
                .address("123 Main St")
                .phone("555-1234")
                .urlLogo("httpg://example.com/logo.jpg")
                .idOwner(1L)
                .build();

        when(userPersistencePort.isOwner(1L)).thenReturn(false);

        // Act & Assert
        assertThrows(DoesNotOwnerException.class, () -> restaurantUseCase.createRestaurant(restaurant));

        verify(restaurantValidator).validate(restaurant);
        verify(userPersistencePort).isOwner(1L);
        verifyNoInteractions(restaurantPersistencePort);
    }
}