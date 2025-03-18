package com.pragma.powerup.domain.usecase;

import com.pragma.powerup.domain.api.IDishServicePort;
import com.pragma.powerup.domain.exception.CustomValidationException;
import com.pragma.powerup.domain.model.Dish;
import com.pragma.powerup.domain.spi.ICategoryPersistencePort;
import com.pragma.powerup.domain.spi.IDishPersistencePort;
import com.pragma.powerup.domain.utils.constants.DishUseCaseConstants;
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

    @Override
    public void updateDish(Long id, Dish dish) {
        Dish existingDish = dishPersistencePort.findDishById(id);
        if (existingDish == null) {
            throw new CustomValidationException(String.format(DishUseCaseConstants.DISH_NOT_FOUND, id));
        }
        dishValidator.validateUpdate(dish);
        applyUpdates(existingDish, dish);
        dishPersistencePort.updateDish(existingDish);
    }

    private void applyUpdates(Dish existingDish, Dish newDish) {
        if (newDish.getPrice() != null) {
            existingDish.setPrice(newDish.getPrice());
        }
        if (newDish.getDescription() != null && !newDish.getDescription().isEmpty()) {
            existingDish.setDescription(newDish.getDescription());
        }
    }

}
