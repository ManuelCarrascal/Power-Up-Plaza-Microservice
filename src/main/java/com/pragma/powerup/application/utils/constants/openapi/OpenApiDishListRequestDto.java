package com.pragma.powerup.application.utils.constants.openapi;

public class OpenApiDishListRequestDto {

    private OpenApiDishListRequestDto (){}

    public static final String DTO_NAME = "DishListRequestDto";
    public static final String DTO_DESCRIPTION = "DTO for dish listing request parameters";

    public static final String ID_RESTAURANT_DESCRIPTION = "ID of the restaurant to filter dishes";
    public static final String ID_RESTAURANT_EXAMPLE = "1";

    public static final String ID_CATEGORY_DESCRIPTION = "ID of the category to filter dishes (optional)";
    public static final String ID_CATEGORY_EXAMPLE = "1";

    public static final String ACTIVE_DESCRIPTION = "Filter dishes by active status";
    public static final String ACTIVE_EXAMPLE = "true";

    public static final String ORDER_DIRECTION_DESCRIPTION = "Sort direction for the results (ASC or DESC)";
    public static final String ORDER_DIRECTION_EXAMPLE = "ASC";

    public static final String LIMIT_FOR_PAGE_DESCRIPTION = "Number of items per page";
    public static final String LIMIT_FOR_PAGE_EXAMPLE = "10";
    public static final String LIMIT_FOR_PAGE_MINIMUM = "1";

    public static final String CURRENT_PAGE_DESCRIPTION = "Current page number (zero-based)";
    public static final String CURRENT_PAGE_EXAMPLE = "0";
    public static final String CURRENT_PAGE_MINIMUM = "0";
}
