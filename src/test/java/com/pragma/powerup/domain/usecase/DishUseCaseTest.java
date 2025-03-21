package com.pragma.powerup.domain.usecase;

import com.pragma.powerup.domain.exception.CustomValidationException;
import com.pragma.powerup.domain.model.Category;
import com.pragma.powerup.domain.model.Dish;
import com.pragma.powerup.domain.spi.ICategoryPersistencePort;
import com.pragma.powerup.domain.spi.IDishPersistencePort;
import com.pragma.powerup.domain.spi.IRestaurantPersistencePort;
import com.pragma.powerup.domain.spi.IUserPersistencePort;
import com.pragma.powerup.domain.utils.validators.DishValidator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class DishUseCaseTest {

    @Mock
    private IDishPersistencePort dishPersistencePort;

    @Mock
    private ICategoryPersistencePort categoryPersistencePort;

    @Mock
    private DishValidator dishValidator;

    @Mock
    private IRestaurantPersistencePort restaurantPersistencePort;

    @Mock
    private IUserPersistencePort userPersistencePort;

    @InjectMocks
    private DishUseCase dishUseCase;

    private Dish dish;

    @BeforeEach
    void setUp() {
        dish = new Dish();
        dish.setId(1L);
        dish.setName("Test Dish");
        dish.setPrice(10000.0);
        dish.setDescription("Test Description");
        dish.setUrlImage("http://example.com/image.jpg");
        dish.setIdRestaurant(1L);

        List<Category> categories = new ArrayList<>();
        categories.add(new Category("test", "Italian"));
        dish.setCategories(categories);
    }

    @Test
    void createDish_ShouldSetActiveAndCallPersistencePort() {
        when(categoryPersistencePort.getCategoriesByNames(any())).thenReturn(dish.getCategories());

        dishUseCase.createDish(dish);

        assertTrue(dish.isActive());
        verify(dishValidator).validate(dish);
        verify(categoryPersistencePort).getCategoriesByNames(dish.getCategories());
        verify(dishPersistencePort).createDish(dish);
    }

    @Test
    void updateDish_WhenDishExists_ShouldUpdateAndCallPersistencePort() {
        Dish existingDish = new Dish();
        existingDish.setId(1L);
        existingDish.setName("Original Dish");
        existingDish.setPrice(5000.0);
        existingDish.setDescription("Original Description");

        Dish updateData = new Dish();
        updateData.setPrice(15000.0);
        updateData.setDescription("Updated Description");

        when(dishPersistencePort.findDishById(1L)).thenReturn(existingDish);

        dishUseCase.updateDish(1L, updateData);

        verify(dishValidator).validateUpdate(updateData);
        assertEquals(15000L, existingDish.getPrice());
        assertEquals("Updated Description", existingDish.getDescription());
        verify(dishPersistencePort).updateDish(existingDish);
    }

    @Test
    void updateDish_WhenDishNotFound_ShouldThrowException() {
        when(dishPersistencePort.findDishById(anyLong())).thenReturn(null);

        Exception exception = assertThrows(CustomValidationException.class, () ->
                dishUseCase.updateDish(1L, dish)
        );

        assertTrue(exception.getMessage().contains("not found"));
        verify(dishPersistencePort).findDishById(1L);
        verify(dishValidator, never()).validateUpdate(any());
        verify(dishPersistencePort, never()).updateDish(any());
    }

    @Test
    void changeDishStatus_WhenDishExistsAndUserIsOwner_ShouldUpdateStatus() {
        Long ownerId = 5L;
        when(dishPersistencePort.findDishById(1L)).thenReturn(dish);
        when(userPersistencePort.getCurrentUserId()).thenReturn(ownerId);
        when(restaurantPersistencePort.isOwnerOfRestaurant(ownerId, dish.getIdRestaurant())).thenReturn(true);

        dishUseCase.changeDishStatus(1L, false);

        assertFalse(dish.isActive());
        verify(dishPersistencePort).changeDishStatus(dish, ownerId);
    }

    @Test
    void changeDishStatus_WhenDishNotFound_ShouldThrowException() {
        when(dishPersistencePort.findDishById(anyLong())).thenReturn(null);

        Exception exception = assertThrows(CustomValidationException.class, () ->
                dishUseCase.changeDishStatus(1L, false)
        );

        assertTrue(exception.getMessage().contains("not found"));
        verify(dishPersistencePort, never()).changeDishStatus(any(), anyLong());
    }

    @Test
    void changeDishStatus_WhenUserIsNotOwner_ShouldThrowException() {
        Long ownerId = 5L;
        when(dishPersistencePort.findDishById(1L)).thenReturn(dish);
        when(userPersistencePort.getCurrentUserId()).thenReturn(ownerId);
        when(restaurantPersistencePort.isOwnerOfRestaurant(ownerId, dish.getIdRestaurant())).thenReturn(false);

        Exception exception = assertThrows(CustomValidationException.class, () ->
                dishUseCase.changeDishStatus(1L, false)
        );

        assertTrue(exception.getMessage().contains("not the owner"));
        verify(dishPersistencePort, never()).changeDishStatus(any(), anyLong());
    }
}