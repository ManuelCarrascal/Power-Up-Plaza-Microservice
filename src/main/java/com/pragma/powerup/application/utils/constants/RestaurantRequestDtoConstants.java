package com.pragma.powerup.application.utils.constants;

public class RestaurantRequestDtoConstants {
    public static final String NAME_IS_MANDATORY = "Name is mandatory";
    public static final String NAME_CANNOT_CONTAIN_ONLY_NUMERIC_CHARACTERS = "Name cannot contain only numeric characters";
    public static final String NAME_REGEX = "^(?!\\d+$).+";
    public static final String NIT_IS_MANDATORY = "Nit is mandatory";
    public static final String NIT_REGEX = "^\\d{10}$";
    public static final String NIT_PATTERN_MESSAGE = "Nit must be exactly 10 numeric digits";
    public static final String ADDRESS_IS_MANDATORY = "Address is mandatory";
    public static final String PHONE_IS_MANDATORY = "Phone is mandatory";
    public static final String URL_LOGO_IS_MANDATORY = "Url logo is mandatory";
    public static final String ID_OWNER_IS_MANDATORY = "Id owner is mandatory";
    public static final String PHONE_PATTERN_MESSAGE = "Phone must be numeric, up to 13 characters, and can include the '+' symbol";
    public static final String PHONE_PATTERN = "^[+]?\\d{1,13}$";

    private RestaurantRequestDtoConstants() {}
}
