package com.pragma.powerup.application.utils.constants.openapi;

public class OpenApiRestaurantListRequestDto {
    private OpenApiRestaurantListRequestDto() {}

    public static final String DTO_NAME = "RestaurantListRequestDto";
    public static final String DTO_DESCRIPTION = "DTO for restaurant listing request parameters";

    public static final String ORDER_DIRECTION_DESCRIPTION = "Sort direction for the results (ASC or DESC)";
    public static final String ORDER_DIRECTION_EXAMPLE = "ASC";
    public static final String ORDER_DIRECTION_ASC = "ASC";
    public static final String ORDER_DIRECTION_DESC = "DESC";

    public static final String LIMIT_FOR_PAGE_DESCRIPTION = "Number of items per page";
    public static final String LIMIT_FOR_PAGE_EXAMPLE = "10";
    public static final String LIMIT_FOR_PAGE_MINIMUM = "1";

    public static final String CURRENT_PAGE_DESCRIPTION = "Current page number (zero-based)";
    public static final String CURRENT_PAGE_EXAMPLE = "0";
    public static final String CURRENT_PAGE_MINIMUM = "0";
}
