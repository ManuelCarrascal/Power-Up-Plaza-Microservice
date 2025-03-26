package com.pragma.powerup.domain.utils.constants;

public class OrderUseCaseConstants {
    private OrderUseCaseConstants() {}

    public static final String STATUS_PENDING = "PENDING";

    public static final int MINIMUM_VALID_ID = 0;
    public static final int MINIMUM_DISH_QUANTITY = 0;

    public static final String CLIENT_HAS_ACTIVE_ORDER = "Client already has an order in progress";
    public static final String RESTAURANT_ID_INVALID = "Restaurant id invalid";
    public static final String RESTAURANT_NOT_FOUND = "Restaurant not found";
    public static final String ORDER_MUST_HAVE_DISHES = "Order must have at least one dish";
    public static final String DISH_ID_INVALID = "Dish id invalid";
    public static final String DISH_QUANTITY_INVALID = "Dish quantity must be greater than zero";
    public static final String DISH_RESTAURANT_MISMATCH = "Dish id mismatch";
    public static final String EMPLOYEE_NOT_RESTAURANT_WORKER = "Employee does not work at this restaurant";

    public static final String STATUS_IN_PREPARATION = "IN_PREPARATION";
    public static final String ORDER_ID_INVALID = "Order ID is invalid";
    public static final String ORDER_NOT_FOUND = "Order not found";
    public static final String ORDER_NOT_RESTAURANT = "Order does not belong to this restaurant";
    public static final String ORDER_NOT_PENDING = "Order is not in PENDING state";

    public static final String STATUS_READY = "READY";
    public static final String ORDER_NOT_IN_PREPARATION = "Order is not in IN_PREPARATION state";
    public static final String STATUS_DELIVERED = "DELIVERED";

    public static final String ORDER_NOT_READY = "Solo se pueden entregar pedidos en estado listo";
    public static final String PIN_REQUIRED = "Se requiere un PIN para entregar el pedido";
    public static final String INVALID_PIN = "El PIN proporcionado no es válido";

}