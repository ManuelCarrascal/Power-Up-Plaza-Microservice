package com.pragma.powerup.application.utils.constants.openapi;

public class OpenApiAssignEmployeeRequestDto {
    private OpenApiAssignEmployeeRequestDto() {
    }

    public static final String DTO_NAME = "AssignEmployeeRequest";
    public static final String DTO_DESCRIPTION = "Data transfer object for assigning an employee to an order";

    public static final String ID_RESTAURANT_DESCRIPTION = "Restaurant ID where the order was placed";
    public static final String ID_RESTAURANT_EXAMPLE = "1";

    public static final String ID_ORDER_DESCRIPTION = "Order ID to which the employee will be assigned";
    public static final String ID_ORDER_EXAMPLE = "1";
}