package com.pragma.powerup.domain.usecase;

import com.pragma.powerup.application.dto.request.RestaurantListRequestDto;
import com.pragma.powerup.domain.exception.DoesNotOwnerException;
import com.pragma.powerup.domain.exception.RestaurantNotFoundException;
import com.pragma.powerup.domain.exception.UserIsNotOwnerOfRestaurantException;
import com.pragma.powerup.domain.model.Pagination;
import com.pragma.powerup.domain.model.Restaurant;
import com.pragma.powerup.domain.spi.IRestaurantPersistencePort;
import com.pragma.powerup.domain.spi.IUserPersistencePort;
import com.pragma.powerup.domain.utils.constants.RestaurantUseCaseConstants;
import com.pragma.powerup.domain.utils.validators.RestaurantValidator;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class RestaurantUseCaseTest {

    @Test
    void createRestaurant_ValidInput_ShouldPersistRestaurant() {
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

        restaurantUseCase.createRestaurant(restaurant);

        verify(restaurantValidator).validate(restaurant);
        verify(userPersistencePort).isOwner(1L);
        verify(restaurantPersistencePort).createRestaurant(restaurant);
    }

    @Test
    void createRestaurant_InvalidOwner_ShouldThrowDoesNotOwnerException() {
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

        assertThrows(DoesNotOwnerException.class, () -> restaurantUseCase.createRestaurant(restaurant));

        verify(restaurantValidator).validate(restaurant);
        verify(userPersistencePort).isOwner(1L);
        verifyNoInteractions(restaurantPersistencePort);
    }

    @Test
    void createEmployee_ValidInput_ShouldAddEmployeeToRestaurant() {
        IRestaurantPersistencePort restaurantPersistencePort = mock(IRestaurantPersistencePort.class);
        IUserPersistencePort userPersistencePort = mock(IUserPersistencePort.class);

        RestaurantUseCase restaurantUseCase = new RestaurantUseCase(restaurantPersistencePort, null, userPersistencePort);

        Restaurant restaurant = new Restaurant.Builder()
                .id(1L)
                .idOwner(2L)
                .build();

        when(restaurantPersistencePort.findById(1L)).thenReturn(java.util.Optional.of(restaurant));
        when(userPersistencePort.isOwner(2L)).thenReturn(true);

        restaurantUseCase.createEmployee(10L, 1L);

        verify(restaurantPersistencePort).addEmployeeToRestaurant(10L, 1L);
    }

    @Test
    void createEmployee_InvalidRestaurantId_ShouldThrowRestaurantNotFoundException() {
        IRestaurantPersistencePort restaurantPersistencePort = mock(IRestaurantPersistencePort.class);
        IUserPersistencePort userPersistencePort = mock(IUserPersistencePort.class);

        RestaurantUseCase restaurantUseCase = new RestaurantUseCase(restaurantPersistencePort, null, userPersistencePort);

        when(restaurantPersistencePort.findById(1L)).thenReturn(java.util.Optional.empty());

        assertThrows(RestaurantNotFoundException.class, () -> restaurantUseCase.createEmployee(10L, 1L));
    }

    @Test
    void createEmployee_UserNotOwner_ShouldThrowUserIsNotOwnerOfRestaurantException() {
        IRestaurantPersistencePort restaurantPersistencePort = mock(IRestaurantPersistencePort.class);
        IUserPersistencePort userPersistencePort = mock(IUserPersistencePort.class);

        RestaurantUseCase restaurantUseCase = new RestaurantUseCase(restaurantPersistencePort, null, userPersistencePort);

        Restaurant restaurant = new Restaurant.Builder()
                .id(1L)
                .idOwner(2L)
                .build();

        when(restaurantPersistencePort.findById(1L)).thenReturn(java.util.Optional.of(restaurant));
        when(userPersistencePort.isOwner(2L)).thenReturn(false);

        assertThrows(UserIsNotOwnerOfRestaurantException.class, () -> restaurantUseCase.createEmployee(10L, 1L));
    }

    @Test
    void isOwnerOfRestaurant_ShouldDelegateToRepository() {
        IRestaurantPersistencePort restaurantPersistencePort = mock(IRestaurantPersistencePort.class);
        IUserPersistencePort userPersistencePort = mock(IUserPersistencePort.class);

        RestaurantUseCase restaurantUseCase = new RestaurantUseCase(restaurantPersistencePort, null, userPersistencePort);

        Long userId = 1L;
        Long restaurantId = 2L;

        when(restaurantPersistencePort.isOwnerOfRestaurant(userId, restaurantId)).thenReturn(true);

        boolean result = restaurantUseCase.isOwnerOfRestaurant(userId, restaurantId);

        assertTrue(result);
        verify(restaurantPersistencePort).isOwnerOfRestaurant(userId, restaurantId);
    }

    @Test
    void isOwnerOfRestaurant_WhenNotOwner_ShouldReturnFalse() {
        IRestaurantPersistencePort restaurantPersistencePort = mock(IRestaurantPersistencePort.class);
        IUserPersistencePort userPersistencePort = mock(IUserPersistencePort.class);

        RestaurantUseCase restaurantUseCase = new RestaurantUseCase(restaurantPersistencePort, null, userPersistencePort);

        Long userId = 1L;
        Long restaurantId = 2L;

        when(restaurantPersistencePort.isOwnerOfRestaurant(userId, restaurantId)).thenReturn(false);

        boolean result = restaurantUseCase.isOwnerOfRestaurant(userId, restaurantId);

        assertFalse(result);
        verify(restaurantPersistencePort).isOwnerOfRestaurant(userId, restaurantId);
    }

    @Test
    void restaurantList_ValidInput_ShouldReturnPagination() {
        IRestaurantPersistencePort restaurantPersistencePort = mock(IRestaurantPersistencePort.class);
        RestaurantUseCase restaurantUseCase = new RestaurantUseCase(restaurantPersistencePort, null, null);

        String orderDirection = "ASC";
        Integer currentPage = 0;
        Integer limitForPage = 10;

        Pagination<Restaurant> expectedPagination = new Pagination<>(true, 0, 1, 5L, new ArrayList<>());

        when(restaurantPersistencePort.listRestaurants("ASC", 0, 10)).thenReturn(expectedPagination);

        Pagination<Restaurant> result = restaurantUseCase.restaurantList(orderDirection, currentPage, limitForPage);

        assertEquals(expectedPagination, result);
        verify(restaurantPersistencePort).listRestaurants("ASC", 0, 10);
    }

    @Test
    void restaurantList_ValidDescendingOrder_ShouldReturnPagination() {
        IRestaurantPersistencePort restaurantPersistencePort = mock(IRestaurantPersistencePort.class);
        RestaurantUseCase restaurantUseCase = new RestaurantUseCase(restaurantPersistencePort, null, null);

        String orderDirection = "DESC";
        Integer currentPage = 0;
        Integer limitForPage = 10;

        Pagination<Restaurant> expectedPagination = new Pagination<>(false, 0, 1, 5L, new ArrayList<>());

        when(restaurantPersistencePort.listRestaurants("DESC", 0, 10)).thenReturn(expectedPagination);

        Pagination<Restaurant> result = restaurantUseCase.restaurantList(orderDirection, currentPage, limitForPage);

        assertEquals(expectedPagination, result);
        verify(restaurantPersistencePort).listRestaurants("DESC", 0, 10);
    }

    @Test
    void restaurantList_InvalidOrderDirection_ShouldThrowIllegalArgumentException() {
        IRestaurantPersistencePort restaurantPersistencePort = mock(IRestaurantPersistencePort.class);
        RestaurantUseCase restaurantUseCase = new RestaurantUseCase(restaurantPersistencePort, null, null);

        String invalidOrderDirection = "INVALID";
        Integer currentPage = 0;
        Integer limitForPage = 10;

        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class,
                () -> restaurantUseCase.restaurantList(invalidOrderDirection, currentPage, limitForPage));
        assertEquals(RestaurantUseCaseConstants.INVALID_ORDER_DIRECTION_MESSAGE, exception.getMessage());
        verifyNoInteractions(restaurantPersistencePort);
    }


    @Test
    void validateAndNormalizeOrderDirection_LowercaseAsc_ShouldReturnUppercase() {
        IRestaurantPersistencePort restaurantPersistencePort = mock(IRestaurantPersistencePort.class);
        RestaurantUseCase restaurantUseCase = new RestaurantUseCase(restaurantPersistencePort, null, null);

        String orderDirection = "asc";
        Integer currentPage = 0;
        Integer limitForPage = 10;

        Pagination<Restaurant> expectedPagination = new Pagination<>(true, 0, 1, 5L, new ArrayList<>());

        when(restaurantPersistencePort.listRestaurants("ASC", 0, 10)).thenReturn(expectedPagination);

        Pagination<Restaurant> result = restaurantUseCase.restaurantList(orderDirection, currentPage, limitForPage);

        assertEquals(expectedPagination, result);
        verify(restaurantPersistencePort).listRestaurants("ASC", 0, 10);
    }

    @Test
    void validateAndNormalizeOrderDirection_MixedCaseDesc_ShouldReturnUppercase() {
        IRestaurantPersistencePort restaurantPersistencePort = mock(IRestaurantPersistencePort.class);
        RestaurantUseCase restaurantUseCase = new RestaurantUseCase(restaurantPersistencePort, null, null);

        String orderDirection = "DeSc";
        Integer currentPage = 0;
        Integer limitForPage = 10;

        Pagination<Restaurant> expectedPagination = new Pagination<>(false, 0, 1, 5L, new ArrayList<>());

        when(restaurantPersistencePort.listRestaurants("DESC", 0, 10)).thenReturn(expectedPagination);

        Pagination<Restaurant> result = restaurantUseCase.restaurantList(orderDirection, currentPage, limitForPage);

        assertEquals(expectedPagination, result);
        verify(restaurantPersistencePort).listRestaurants("DESC", 0, 10);
    }
}