package com.pragma.powerup.infrastructure.utils.constants.openapi;

public class OpenApiOrderRestController {
    private OpenApiOrderRestController() {}

    public static final String TAG_NAME = "Orders";
    public static final String TAG_DESCRIPTION = "API for order management";

    public static final String CREATE_ORDER_SUMMARY = "Create a new order";
    public static final String CREATE_ORDER_DESCRIPTION = "Creates a new order with the specified dishes for a restaurant";
    public static final String CREATE_ORDER_201_DESCRIPTION = "Order created successfully";
    public static final String CREATE_ORDER_400_DESCRIPTION = "Invalid order data";
    public static final String CREATE_ORDER_401_DESCRIPTION = "Unauthorized access";
    public static final String CREATE_ORDER_404_DESCRIPTION = "Restaurant or dishes not found";

    public static final String ORDER_CREATED_SUCCESSFULLY = "Order created successfully";

    public static final String LIST_ORDERS_SUMMARY = "List restaurant orders";
    public static final String LIST_ORDERS_DESCRIPTION = "Get paginated list of orders for a specific restaurant";

    public static final String LIST_ORDERS_200_DESCRIPTION = "Orders retrieved successfully";
    public static final String LIST_ORDERS_400_DESCRIPTION = "Bad request: invalid parameters";
    public static final String LIST_ORDERS_401_DESCRIPTION = "Unauthorized: authentication required";
    public static final String LIST_ORDERS_403_DESCRIPTION = "Forbidden: user is not an employee of the restaurant";
}