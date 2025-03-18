package com.pragma.powerup.application.utils.constants.openapi;

public class OpenApiDishUpdateRequestDtoConstants {
    public static final String DTO_NAME = "DishUpdateRequestDto";
    public static final String DTO_DESCRIPTION = "DTO for updating a dish's price and description.";

    public static final String PRICE_DESCRIPTION = "The new price of the dish. It must be a positive integer value.";
    public static final String PRICE_EXAMPLE = "1200";

    public static final String DESCRIPTION_DESCRIPTION = "Updated description of the dish.";
    public static final String DESCRIPTION_EXAMPLE = "A delicious spicy chicken dish.";

    private OpenApiDishUpdateRequestDtoConstants() {
    }
}
