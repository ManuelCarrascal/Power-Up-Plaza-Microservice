package com.pragma.powerup.domain.usecase;

import com.pragma.powerup.domain.exception.DoesNotOwnerException;
import com.pragma.powerup.domain.model.Restaurant;
import com.pragma.powerup.domain.spi.IRestaurantPersistencePort;
import com.pragma.powerup.domain.spi.IUserPersistencePort;
import com.pragma.powerup.domain.utils.constants.UserUseCaseConstants;
import com.pragma.powerup.domain.utils.validators.RestaurantValidator;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

class RestaurantUseCaseTest {

    @Test
    void createRestaurant_validData_createsRestaurantSuccessfully() {
        IRestaurantPersistencePort mockPersistencePort = mock(IRestaurantPersistencePort.class);
        RestaurantValidator mockValidator = mock(RestaurantValidator.class);
        IUserPersistencePort mockUserPort = mock(IUserPersistencePort.class);

        RestaurantUseCase restaurantUseCase = new RestaurantUseCase(mockPersistencePort, mockValidator, mockUserPort);
        Restaurant restaurant = new Restaurant(1L, "Test Restaurant", "123456", "Test Address", "1234567890", "http://testlogo.com", 1L);

        when(mockUserPort.isOwner(restaurant.getIdOwner())).thenReturn(true);

        assertDoesNotThrow(() -> restaurantUseCase.createRestaurant(restaurant));

        verify(mockValidator, times(1)).validate(restaurant);
        verify(mockUserPort, times(1)).isOwner(restaurant.getIdOwner());
        verify(mockPersistencePort, times(1)).createRestaurant(restaurant);
    }

    @Test
    void createRestaurant_invalidOwner_throwsDoesNotOwnerException() {
        IRestaurantPersistencePort mockPersistencePort = mock(IRestaurantPersistencePort.class);
        RestaurantValidator mockValidator = mock(RestaurantValidator.class);
        IUserPersistencePort mockUserPort = mock(IUserPersistencePort.class);

        RestaurantUseCase restaurantUseCase = new RestaurantUseCase(mockPersistencePort, mockValidator, mockUserPort);
        Restaurant restaurant = new Restaurant(1L, "Test Restaurant", "123456", "Test Address", "1234567890", "http://testlogo.com", 1L);

        when(mockUserPort.isOwner(restaurant.getIdOwner())).thenReturn(false);

        DoesNotOwnerException exception = assertThrows(DoesNotOwnerException.class, () -> restaurantUseCase.createRestaurant(restaurant));

        verify(mockValidator, times(1)).validate(restaurant);
        verify(mockUserPort, times(1)).isOwner(restaurant.getIdOwner());
        verify(mockPersistencePort, never()).createRestaurant(restaurant);

        assert exception.getMessage().equals(UserUseCaseConstants.DOES_NOT_HAVE_OWNER_OWNER_ROLE);
    }

    @Test
    void createRestaurant_validationFails_doesNotProceedToCreation() {
        IRestaurantPersistencePort mockPersistencePort = mock(IRestaurantPersistencePort.class);
        RestaurantValidator mockValidator = mock(RestaurantValidator.class);
        IUserPersistencePort mockUserPort = mock(IUserPersistencePort.class);

        RestaurantUseCase restaurantUseCase = new RestaurantUseCase(mockPersistencePort, mockValidator, mockUserPort);
        Restaurant restaurant = new Restaurant(null, null, null, null, null, null, null);

        doThrow(new IllegalArgumentException("Invalid restaurant")).when(mockValidator).validate(restaurant);

        assertThrows(IllegalArgumentException.class, () -> restaurantUseCase.createRestaurant(restaurant));

        verify(mockValidator, times(1)).validate(restaurant);
        verify(mockUserPort, never()).isOwner(any());
        verify(mockPersistencePort, never()).createRestaurant(any());
    }
}