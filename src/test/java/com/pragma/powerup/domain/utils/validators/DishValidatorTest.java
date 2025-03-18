package com.pragma.powerup.domain.utils.validators;

import com.pragma.powerup.domain.exception.CustomValidationException;
import com.pragma.powerup.domain.model.Category;
import com.pragma.powerup.domain.model.Dish;
import com.pragma.powerup.domain.utils.constants.DishValidatorConstants;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Collections;
import java.util.List;

class DishValidatorTest {

    private final DishValidator dishValidator = new DishValidator();

    @Test
    void validate_ShouldThrowException_WhenNameIsNull() {
        Dish dish = new Dish();
        dish.setName(null);

        CustomValidationException exception = Assertions.assertThrows(CustomValidationException.class,
                () -> dishValidator.validate(dish));
        Assertions.assertEquals(DishValidatorConstants.NAME_REQUIRED, exception.getMessage());
    }

    @Test
    void validate_ShouldThrowException_WhenNameIsEmpty() {
        Dish dish = new Dish();
        dish.setName("");

        CustomValidationException exception = Assertions.assertThrows(CustomValidationException.class,
                () -> dishValidator.validate(dish));
        Assertions.assertEquals(DishValidatorConstants.NAME_REQUIRED, exception.getMessage());
    }

    @Test
    void validate_ShouldThrowException_WhenPriceIsZeroOrNegative() {
        Dish dish = new Dish();
        dish.setName("Dish Name");
        dish.setPrice(0.0);

        CustomValidationException exception = Assertions.assertThrows(CustomValidationException.class,
                () -> dishValidator.validate(dish));
        Assertions.assertEquals(DishValidatorConstants.PRICE_MUST_BE_POSITIVE, exception.getMessage());
    }

    @Test
    void validate_ShouldThrowException_WhenPriceIsNotInteger() {
        Dish dish = new Dish();
        dish.setName("Dish Name");
        dish.setPrice(10.5);

        CustomValidationException exception = Assertions.assertThrows(CustomValidationException.class,
                () -> dishValidator.validate(dish));
        Assertions.assertEquals(DishValidatorConstants.PRICE_MUST_BE_INTEGER, exception.getMessage());
    }

    @Test
    void validate_ShouldThrowException_WhenDescriptionIsNull() {
        Dish dish = new Dish();
        dish.setName("Dish Name");
        dish.setPrice(10.0);
        dish.setDescription(null);

        CustomValidationException exception = Assertions.assertThrows(CustomValidationException.class,
                () -> dishValidator.validate(dish));
        Assertions.assertEquals(DishValidatorConstants.DESCRIPTION_REQUIRED, exception.getMessage());
    }

    @Test
    void validate_ShouldThrowException_WhenDescriptionIsEmpty() {
        Dish dish = new Dish();
        dish.setName("Dish Name");
        dish.setPrice(10.0);
        dish.setDescription("");

        CustomValidationException exception = Assertions.assertThrows(CustomValidationException.class,
                () -> dishValidator.validate(dish));
        Assertions.assertEquals(DishValidatorConstants.DESCRIPTION_REQUIRED, exception.getMessage());
    }

    @Test
    void validate_ShouldThrowException_WhenUrlImageIsNull() {
        Dish dish = new Dish();
        dish.setName("Dish Name");
        dish.setPrice(10.0);
        dish.setDescription("Description");
        dish.setUrlImage(null);

        CustomValidationException exception = Assertions.assertThrows(CustomValidationException.class,
                () -> dishValidator.validate(dish));
        Assertions.assertEquals(DishValidatorConstants.URL_IMAGE_REQUIRED, exception.getMessage());
    }

    @Test
    void validate_ShouldThrowException_WhenUrlImageIsEmpty() {
        Dish dish = new Dish();
        dish.setName("Dish Name");
        dish.setPrice(10.0);
        dish.setDescription("Description");
        dish.setUrlImage("");

        CustomValidationException exception = Assertions.assertThrows(CustomValidationException.class,
                () -> dishValidator.validate(dish));
        Assertions.assertEquals(DishValidatorConstants.URL_IMAGE_REQUIRED, exception.getMessage());
    }

    @Test
    void validate_ShouldThrowException_WhenCategoriesAreNull() {
        Dish dish = new Dish();
        dish.setName("Dish Name");
        dish.setPrice(10.0);
        dish.setDescription("Description");
        dish.setUrlImage("http://valid-url.com");
        dish.setCategories(null);

        CustomValidationException exception = Assertions.assertThrows(CustomValidationException.class,
                () -> dishValidator.validate(dish));
        Assertions.assertEquals(DishValidatorConstants.CATEGORY_REQUIRED, exception.getMessage());
    }

    @Test
    void validate_ShouldThrowException_WhenCategoriesAreEmpty() {
        Dish dish = new Dish();
        dish.setName("Dish Name");
        dish.setPrice(10.0);
        dish.setDescription("Description");
        dish.setUrlImage("http://valid-url.com");
        dish.setCategories(Collections.emptyList());

        CustomValidationException exception = Assertions.assertThrows(CustomValidationException.class,
                () -> dishValidator.validate(dish));
        Assertions.assertEquals(DishValidatorConstants.CATEGORY_REQUIRED, exception.getMessage());
    }

    @Test
    void validate_ShouldThrowException_WhenCategoryIsInvalid() {
        Category invalidCategory = new Category();
        invalidCategory.setName(null);
        List<Category> categories = List.of(invalidCategory);

        Dish dish = new Dish();
        dish.setName("Dish Name");
        dish.setPrice(10.0);
        dish.setDescription("Description");
        dish.setUrlImage("http://valid-url.com");
        dish.setCategories(categories);

        CustomValidationException exception = Assertions.assertThrows(CustomValidationException.class,
                () -> dishValidator.validate(dish));
        Assertions.assertEquals(DishValidatorConstants.INVALID_CATEGORY, exception.getMessage());
    }

    @Test
    void validate_ShouldNotThrowException_WhenDishIsValid() {
        Category validCategory = new Category();
        validCategory.setName("Category Name");

        Dish dish = new Dish();
        dish.setName("Dish Name");
        dish.setPrice(10.0);
        dish.setDescription("Description");
        dish.setUrlImage("http://valid-url.com");
        dish.setCategories(List.of(validCategory));
        dish.setIdRestaurant(1L);
        dish.setActive(true);

        Assertions.assertDoesNotThrow(() -> dishValidator.validate(dish));
    }
}