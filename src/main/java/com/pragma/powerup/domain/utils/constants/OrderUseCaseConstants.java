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
}