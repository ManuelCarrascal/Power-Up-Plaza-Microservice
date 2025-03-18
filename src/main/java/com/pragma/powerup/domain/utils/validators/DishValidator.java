package com.pragma.powerup.domain.utils.validators;

import com.pragma.powerup.domain.exception.CustomValidationException;
import com.pragma.powerup.domain.model.Category;
import com.pragma.powerup.domain.model.Dish;
import com.pragma.powerup.domain.utils.constants.DishValidatorConstants;

import java.util.List;

public class DishValidator {

    public void validate(Dish dish) {
        validName(dish.getName());
        validPrice(dish.getPrice());
        validDescription(dish.getDescription());
        validUrlImage(dish.getUrlImage());
        validCategory(dish.getCategories());
        validRestaurant(dish.getIdRestaurant());
        ensureActiveDefault(dish.isActive());
    }

    public void validateUpdate(Dish dish) {
        if (dish.getPrice() != null) {
            validPrice(dish.getPrice());
        }
        if (dish.getDescription() != null && !dish.getDescription().isEmpty()) {
            validDescription(dish.getDescription());
        }
    }

    private void validName(String name) {
        if (name == null || name.isEmpty()) {
            throw new CustomValidationException(DishValidatorConstants.NAME_REQUIRED);
        }
    }

    private void validPrice(double price) {
        if (price <= 0) {
            throw new CustomValidationException(DishValidatorConstants.PRICE_MUST_BE_POSITIVE);
        }
        if (price != Math.floor(price)) {
            throw new CustomValidationException(DishValidatorConstants.PRICE_MUST_BE_INTEGER);
        }
    }

    private void validDescription(String description) {
        if (description == null || description.trim().isEmpty()) {
            throw new CustomValidationException(DishValidatorConstants.DESCRIPTION_REQUIRED);
        }
    }

    private void validUrlImage(String urlImage) {
        if (urlImage == null || urlImage.trim().isEmpty()) {
            throw new CustomValidationException(DishValidatorConstants.URL_IMAGE_REQUIRED);
        }
    }

    private void validCategory(List<Category> categories) {
        if (categories == null || categories.isEmpty()) {
            throw new CustomValidationException(DishValidatorConstants.CATEGORY_REQUIRED);
        }

        for (Category category : categories) {
            if (category == null || category.getName() == null || category.getName().trim().isEmpty()) {
                throw new CustomValidationException(DishValidatorConstants.INVALID_CATEGORY);
            }
        }
    }

    private void validRestaurant(Long restaurantId) {
        if (restaurantId == null) {
            throw new CustomValidationException(DishValidatorConstants.RESTAURANT_REQUIRED);
        }
    }

    private void ensureActiveDefault(boolean isActive) {
        if (!isActive) {
            throw new CustomValidationException(DishValidatorConstants.ACTIVE_MUST_BE_TRUE);
        }
    }


}