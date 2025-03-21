package com.pragma.powerup.application.utils.constants.openapi;

public class OpenApiDishStatusDtoConstants {
    private OpenApiDishStatusDtoConstants() {
        throw new IllegalStateException("Utility class");
    }

    public static final String DTO_NAME = "DishStatusDto";
    public static final String DTO_DESCRIPTION = "DTO to change the status of a dish";

    public static final String DISH_ID_DESCRIPTION = "ID of the dish to change status";
    public static final String DISH_ID_EXAMPLE = "1";

    public static final String STATUS_DESCRIPTION = "New status for the dish (true = active, false = inactive)";
    public static final String STATUS_EXAMPLE = "true";
}