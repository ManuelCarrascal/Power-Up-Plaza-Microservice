package com.pragma.powerup.domain.usecase;

import com.pragma.powerup.domain.model.Category;
import com.pragma.powerup.domain.model.Dish;
import com.pragma.powerup.domain.spi.ICategoryPersistencePort;
import com.pragma.powerup.domain.spi.IDishPersistencePort;
import com.pragma.powerup.domain.utils.validators.DishValidator;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.*;

class DishUseCaseTest {



    @Test
    void shouldCreateDishSuccessfullyWithValidData() {
        IDishPersistencePort dishPersistencePort = Mockito.mock(IDishPersistencePort.class);
        ICategoryPersistencePort categoryPersistencePort = Mockito.mock(ICategoryPersistencePort.class);
        DishValidator dishValidator = spy(new DishValidator());
        DishUseCase dishUseCase = new DishUseCase(dishPersistencePort, categoryPersistencePort, dishValidator);

        Dish dish = new Dish();
        dish.setName("Test Dish");
        dish.setPrice(20);
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
