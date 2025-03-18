package com.pragma.powerup.domain.usecase;

import com.pragma.powerup.domain.api.IDishServicePort;
import com.pragma.powerup.domain.model.Dish;
import com.pragma.powerup.domain.spi.ICategoryPersistencePort;
import com.pragma.powerup.domain.spi.IDishPersistencePort;
import com.pragma.powerup.domain.utils.validators.DishValidator;

public class DishUseCase implements IDishServicePort {

    private final IDishPersistencePort dishPersistencePort;
    private final ICategoryPersistencePort categoryPersistencePort;
    private final DishValidator  dishValidator;

    public DishUseCase(IDishPersistencePort dishPersistencePort, ICategoryPersistencePort categoryPersistencePort, DishValidator dishValidator) {
        this.dishPersistencePort = dishPersistencePort;
        this.categoryPersistencePort = categoryPersistencePort;
        this.dishValidator = dishValidator;
    }

    @Override
    public void createDish(Dish dish) {
        dish.setActive(true);
        dishValidator.validate(dish);
        dish.setCategories(categoryPersistencePort.getCategoriesByNames(dish.getCategories()));
        dishPersistencePort.createDish(dish);
    }
}
