package com.pragma.powerup.application.utils.constants.openapi;

public class OpenApiOrderListRequestDto {

    private OpenApiOrderListRequestDto() {
    }

    public static final String DTO_NAME = "OrderListRequestDto";
    public static final String DTO_DESCRIPTION = "Request DTO for listing orders with pagination";

    public static final String ORDER_DIRECTION_DESCRIPTION = "Sort direction (ASC or DESC)";
    public static final String ORDER_DIRECTION_EXAMPLE = "ASC";
    public static final String ORDER_DIRECTION_DEFAULT = "ASC";

    public static final String CURRENT_PAGE_DESCRIPTION = "Current page number";
    public static final String CURRENT_PAGE_EXAMPLE = "0";

    public static final String LIMIT_FOR_PAGE_DESCRIPTION = "Number of items per page";
    public static final String LIMIT_FOR_PAGE_EXAMPLE = "10";
    public static final String LIMIT_FOR_PAGE_DEFAULT = "10";

    public static final String STATUS_DESCRIPTION = "Filter orders by status (optional)";
    public static final String STATUS_EXAMPLE = "PENDING";

    public static final String RESTAURANT_ID_DESCRIPTION = "Restaurant ID to list orders from";
    public static final String RESTAURANT_ID_EXAMPLE = "1";
    public static final String CURRENT_PAGE_DEFAULT = "0";
}