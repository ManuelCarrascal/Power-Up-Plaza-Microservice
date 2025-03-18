package com.pragma.powerup.application.utils.constants.openapi;

public class OpenApiDishRequestDtoConstants {
    
    private OpenApiDishRequestDtoConstants() {}

    public static final String DTO_NAME = "DishRequestDto";
    public static final String DTO_DESCRIPTION = "DTO used to create a new dish";

    public static final String NAME_DESCRIPTION = "Dish name";
    public static final String NAME_EXAMPLE = "Pasta Alfredo";

    public static final String PRICE_DESCRIPTION = "Dish price (only positive values allowed) and stored as integers in the database";
    public static final String PRICE_EXAMPLE = "15";

    public static final String DESCRIPTION_DESCRIPTION = "Dish description";
    public static final String DESCRIPTION_EXAMPLE = "Alfredo pasta with chicken";

    public static final String URL_IMAGE_DESCRIPTION = "URL of the dish image";
    public static final String URL_IMAGE_EXAMPLE = "https://images.com/dish.jpg";

    public static final String CATEGORIES_DESCRIPTION = "List of categories associated with the dish";
    public static final String CATEGORIES_EXAMPLE = "[ \"Italian\", \"Vegetarian\" ]";

    public static final String ID_RESTAURANT_DESCRIPTION = "The ID of the restaurant the dish belongs to";
    public static final String ID_RESTAURANT_EXAMPLE = "1";
}