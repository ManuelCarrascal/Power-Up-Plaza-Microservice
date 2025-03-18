package com.pragma.powerup.domain.utils.constants;

public class DishValidatorConstants {
    private DishValidatorConstants() {}
    public static final String NAME_REQUIRED = "The dish name is mandatory.";
    public static final String PRICE_MUST_BE_POSITIVE = "The price must be a positive integer.";
    public static final String PRICE_MUST_BE_INTEGER = "The price must be an integer.";
    public static final String DESCRIPTION_REQUIRED = "The dish description is mandatory.";
    public static final String URL_IMAGE_REQUIRED = "The dish image URL is mandatory.";
    public static final String CATEGORY_REQUIRED = "The dish must have an associated category.";
    public static final String RESTAURANT_REQUIRED = "The dish must be associated with a restaurant.";
    public static final String ACTIVE_MUST_BE_TRUE = "The dish must be active by default.";
    public static final String INVALID_CATEGORY = "The category list contains an invalid or null category.";
}

