package com.pragma.powerup.application.utils.constants.openapi;

public class OpenApiOrderRequestDto {
    private OpenApiOrderRequestDto(){}

    public static final String DESCRIPTION = "DTO para crear una nueva orden";
    public static final String RESTAURANT_ID_DESCRIPTION = "ID del restaurante";
    public static final String RESTAURANT_ID_EXAMPLE = "1";
    public static final String DISHES_DESCRIPTION = "Lista de platos en la orden";
    public static final String DATE_DESCRIPTION = "Fecha de la orden (opcional, se asigna automáticamente)";
    public static final String DATE_EXAMPLE = "2023-10-12";
}