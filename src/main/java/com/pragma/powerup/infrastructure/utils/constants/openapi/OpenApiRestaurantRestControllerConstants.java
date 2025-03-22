package com.pragma.powerup.infrastructure.utils.constants.openapi;

public class OpenApiRestaurantRestControllerConstants {

    private OpenApiRestaurantRestControllerConstants() {
    }

    public static final String TAG_NAME = "Restaurant";
    public static final String TAG_DESCRIPTION = "Restaurant API";
    public static final String RESPONSE_401_DESCRIPTION = "Unauthorized access";

    public static final String OPERATION_SUMMARY = "Create new restaurant";
    public static final String OPERATION_DESCRIPTION = "Creates a new restaurant in the system with the provided information";
    public static final String RESPONSE_200_DESCRIPTION = "Restaurant created successfully";
    public static final String RESPONSE_400_DESCRIPTION = "Invalid input data";
    public static final String RESPONSE_404_DESCRIPTION = "Restaurant owner not found";

    public static final String CREATE_EMPLOYEE_SUMMARY = "Create restaurant employee";
    public static final String CREATE_EMPLOYEE_DESCRIPTION = "Assigns an employee to a specific restaurant";
    public static final String CREATE_EMPLOYEE_201_DESCRIPTION = "Employee successfully assigned to restaurant";
    public static final String CREATE_EMPLOYEE_400_DESCRIPTION = "Invalid user or restaurant data";
    public static final String CREATE_EMPLOYEE_404_DESCRIPTION = "Restaurant or user not found";

    public static final String LIST_RESTAURANTS_SUMMARY = "List restaurants";
    public static final String LIST_RESTAURANTS_DESCRIPTION = "Returns a paginated list of restaurants ordered alphabetically";
    public static final String LIST_RESTAURANTS_200_DESCRIPTION = "Restaurants retrieved successfully";
    public static final String LIST_RESTAURANTS_400_DESCRIPTION = "Invalid pagination parameters";

    public static final String IS_OWNER_SUMMARY = "Check restaurant ownership";
    public static final String IS_OWNER_DESCRIPTION = "Verifies if a user is the owner of a specific restaurant";
    public static final String IS_OWNER_200_DESCRIPTION = "Ownership verification completed successfully";
    public static final String IS_OWNER_400_DESCRIPTION = "Invalid owner or restaurant ID";

    public static final String PARAM_USER_ID_DESCRIPTION = "ID of the user to be assigned as employee";
    public static final String PARAM_RESTAURANT_ID_DESCRIPTION = "ID of the restaurant";

    public static final String PARAM_OWNER_ID_DESCRIPTION = "ID of the owner to verify";
    public static final String PARAM_RESTAURANT_CHECK_DESCRIPTION = "ID of the restaurant to check";
}

