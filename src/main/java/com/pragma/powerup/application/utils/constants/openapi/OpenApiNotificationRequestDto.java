package com.pragma.powerup.application.utils.constants.openapi;

public class OpenApiNotificationRequestDto {

    private OpenApiNotificationRequestDto (){
    }

    public static final String SCHEMA_DESCRIPTION = "Data transfer object for notification requests";

    public static final String ID_ORDER_DESCRIPTION = "Unique identifier of the order";
    public static final String ID_ORDER_EXAMPLE = "1";
    public static final String ID_ORDER_POSITIVE_MESSAGE = "Order ID must be positive";
    public static final String ID_ORDER_NOT_NULL_MESSAGE = "Order ID cannot be null";

    public static final String PHONE_DESCRIPTION = "Phone number to send the notification to";
    public static final String PHONE_EXAMPLE = "+573001234567";
    public static final String PHONE_NOT_BLANK_MESSAGE = "Phone number cannot be blank";
}