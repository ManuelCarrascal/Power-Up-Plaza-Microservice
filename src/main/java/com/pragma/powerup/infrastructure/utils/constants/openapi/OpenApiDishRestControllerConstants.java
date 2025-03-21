package com.pragma.powerup.infrastructure.utils.constants.openapi;

public class OpenApiDishRestControllerConstants {
    private OpenApiDishRestControllerConstants() {}

    public static final String CONTROLLER_DESCRIPTION = "Dish Rest Controller";
    public static final String CONTROLLER_TAG = "Dishes";

    public static final String CREATE_DISH_SUMMARY = "Create a new dish";
    public static final String CREATE_DISH_DESCRIPTION = "Endpoint to add a new dish to the system using the provided dish details.";

    public static final String RESPONSE_200_DESCRIPTION = "Dish successfully created";
    public static final String RESPONSE_400_DESCRIPTION = "Invalid request parameters";
    public static final String RESPONSE_404_DESCRIPTION = "Dish not found for the provided ID.";
    public static final String RESPONSE_500_DESCRIPTION = "Internal server error";

    public static final String UPDATE_DISH_SUMMARY = "Update an existing dish";
    public static final String UPDATE_DISH_DESCRIPTION = "Endpoint to update the price or description of a dish.";

    public static final String CHANGE_DISH_STATUS_SUMMARY = "Change dish status";
    public static final String CHANGE_DISH_STATUS_DESCRIPTION = "Changes the active status of a dish. Only owners of the restaurant can change the status.";

}