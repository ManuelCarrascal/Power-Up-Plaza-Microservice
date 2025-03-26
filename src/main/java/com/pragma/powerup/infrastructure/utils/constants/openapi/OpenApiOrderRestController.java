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

    public static final String ASSIGN_EMPLOYEE_SUMMARY = "Assign an employee to an order";
    public static final String ASSIGN_EMPLOYEE_DESCRIPTION = "Assigns the authenticated employee to the specified order and changes the order status to IN_PREPARATION";
    public static final String ASSIGN_EMPLOYEE_200_DESCRIPTION = "Employee successfully assigned to order";
    public static final String ASSIGN_EMPLOYEE_400_DESCRIPTION = "Invalid request - Order not found, order not in PENDING state, or order does not belong to restaurant";
    public static final String ASSIGN_EMPLOYEE_401_DESCRIPTION = "Unauthorized - Authentication required";
    public static final String ASSIGN_EMPLOYEE_403_DESCRIPTION = "Forbidden - User is not an employee of the restaurant";
    public static final String EMPLOYEE_ASSIGNED_SUCCESSFULLY = "Employee successfully assigned to order";

    public static final String ORDER_READY_SUMMARY = "Mark order as ready for delivery";
    public static final String ORDER_READY_DESCRIPTION = "Updates the order status to READY and sends a notification to the client";
    public static final String ORDER_READY_200_DESCRIPTION = "Order status successfully updated to READY";
    public static final String ORDER_READY_400_DESCRIPTION = "Invalid request - order cannot be marked as ready";
    public static final String ORDER_READY_401_DESCRIPTION = "Authentication credentials are missing or invalid";
    public static final String ORDER_READY_403_DESCRIPTION = "User does not have permission to mark this order as ready";
    public static final String ORDER_READY_404_DESCRIPTION = "Order not found or not in the correct state";
    public static final String ORDER_READY_SUCCESSFULLY = "Order is ready";

    public static final String DELIVER_ORDER_SUMMARY = "Deliver order to customer";
    public static final String DELIVER_ORDER_DESCRIPTION = "Marks an order as delivered after validating the security PIN provided by the customer";
    public static final String DELIVER_ORDER_200_DESCRIPTION = "Order successfully delivered";
    public static final String DELIVER_ORDER_400_DESCRIPTION = "Invalid request or incorrect PIN";
    public static final String DELIVER_ORDER_401_DESCRIPTION = "Authentication credentials are missing or invalid";
    public static final String DELIVER_ORDER_404_DESCRIPTION = "Order not found or not in READY state";
    public static final String ORDER_DELIVERED_SUCCESSFULLY = "Order delivered successfully";

    public static final String CANCEL_ORDER_SUMMARY = "Cancel an order";
    public static final String CANCEL_ORDER_DESCRIPTION = "Allows a client to cancel an order that is in Pending state";
    public static final String CANCEL_ORDER_200_DESCRIPTION = "Order successfully canceled";
    public static final String CANCEL_ORDER_400_DESCRIPTION = "Invalid request or order cannot be canceled";
    public static final String CANCEL_ORDER_401_DESCRIPTION = "Authentication credentials are missing or invalid";
    public static final String CANCEL_ORDER_403_DESCRIPTION = "User does not have permission to cancel this order";
    public static final String CANCEL_ORDER_404_DESCRIPTION = "Order not found";
    public static final String ORDER_CANCELED_SUCCESSFULLY = "Order has been successfully canceled";
}