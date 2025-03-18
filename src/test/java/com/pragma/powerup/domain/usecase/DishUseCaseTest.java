package com.pragma.powerup.domain.usecase;

import com.pragma.powerup.domain.exception.CustomValidationException;
import com.pragma.powerup.domain.model.Category;
import com.pragma.powerup.domain.model.Dish;
import com.pragma.powerup.domain.spi.ICategoryPersistencePort;
import com.pragma.powerup.domain.spi.IDishPersistencePort;
import com.pragma.powerup.domain.utils.validators.DishValidator;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class DishUseCaseTest {

    @Test
    void shouldUpdateDishSuccessfullyWithValidData() {
        IDishPersistencePort dishPersistencePort = Mockito.mock(IDishPersistencePort.class);
        DishValidator dishValidator = spy(new DishValidator());
        DishUseCase dishUseCase = new DishUseCase(dishPersistencePort, null, dishValidator);

        Long dishId = 1L;
        Dish existingDish = new Dish();
        existingDish.setId(dishId);
        existingDish.setPrice(15.0);
        existingDish.setDescription("Old description");

        Dish updatedDish = new Dish();
        updatedDish.setPrice(20.0);
        updatedDish.setDescription("New description");

        when(dishPersistencePort.findDishById(dishId)).thenReturn(existingDish);

        dishUseCase.updateDish(dishId, updatedDish);

        assertEquals(20.0, existingDish.getPrice());
        assertEquals("New description", existingDish.getDescription());
        verify(dishValidator).validateUpdate(updatedDish);
        verify(dishPersistencePort).updateDish(existingDish);
    }

    @Test
    void shouldThrowExceptionWhenDishNotFound() {
        IDishPersistencePort dishPersistencePort = Mockito.mock(IDishPersistencePort.class);
        DishValidator dishValidator = spy(new DishValidator());
        DishUseCase dishUseCase = new DishUseCase(dishPersistencePort, null, dishValidator);

        Long dishId = 1L;
        Dish updatedDish = new Dish();
        updatedDish.setPrice(20.0);
        updatedDish.setDescription("New description");

        when(dishPersistencePort.findDishById(dishId)).thenReturn(null);

        CustomValidationException exception = assertThrows(
                CustomValidationException.class,
                () -> dishUseCase.updateDish(dishId, updatedDish)
        );

        assertFalse(exception.getMessage().contains("Dish not found"));
        verify(dishPersistencePort).findDishById(dishId);
        verifyNoMoreInteractions(dishPersistencePort, dishValidator);
    }

    @Test
    void shouldFailValidationOnUpdateDishWithInvalidData() {
        IDishPersistencePort dishPersistencePort = Mockito.mock(IDishPersistencePort.class);
        DishValidator dishValidator = spy(new DishValidator());
        DishUseCase dishUseCase = new DishUseCase(dishPersistencePort, null, dishValidator);

        Long dishId = 1L;
        Dish existingDish = new Dish();
        existingDish.setId(dishId);
        existingDish.setPrice(15.0);
        existingDish.setDescription("Old description");

        Dish invalidDish = new Dish(); // Invalid values depending on the DishValidator logic

        when(dishPersistencePort.findDishById(dishId)).thenReturn(existingDish);
        doThrow(new CustomValidationException("Validation failed")).when(dishValidator).validateUpdate(invalidDish);

        CustomValidationException exception = assertThrows(
                CustomValidationException.class,
                () -> dishUseCase.updateDish(dishId, invalidDish)
        );

        assertTrue(exception.getMessage().contains("Validation failed"));
        verify(dishPersistencePort).findDishById(dishId);
        verify(dishValidator).validateUpdate(invalidDish);
        verifyNoMoreInteractions(dishPersistencePort);
    }


    @Test
    void shouldCreateDishSuccessfullyWithValidData() {
        IDishPersistencePort dishPersistencePort = Mockito.mock(IDishPersistencePort.class);
        ICategoryPersistencePort categoryPersistencePort = Mockito.mock(ICategoryPersistencePort.class);
        DishValidator dishValidator = spy(new DishValidator());
        DishUseCase dishUseCase = new DishUseCase(dishPersistencePort, categoryPersistencePort, dishValidator);

        Dish dish = new Dish();
        dish.setName("Test Dish");
        dish.setPrice(20.0);
        dish.setDescription("Delicious test dish");
        dish.setUrlImage("https://test.image.com/test.png");
        dish.setIdRestaurant(1L);
        dish.setActive(true);

        Category mainCategory = new Category();
        mainCategory.setId(1L);
        mainCategory.setName("Main");
        dish.setCategories(Collections.singletonList(mainCategory));

        when(categoryPersistencePort.getCategoriesByNames(dish.getCategories()))
                .thenReturn(List.of(mainCategory));

        dishUseCase.createDish(dish);
        assertTrue(dish.isActive());
        verify(dishValidator).validate(dish);
        verify(categoryPersistencePort).getCategoriesByNames(dish.getCategories());
        verify(dishPersistencePort).createDish(dish);
        verifyNoMoreInteractions(dishPersistencePort, categoryPersistencePort, dishValidator);
    }
}
