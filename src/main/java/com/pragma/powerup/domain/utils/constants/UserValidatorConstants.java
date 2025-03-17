package com.pragma.powerup.domain.utils.constants;

public class UserValidatorConstants {
    public static final String NAME_IS_REQUIRED = "Name is required";
    public static final String NAME_REGEX_MESSAGE = "Name cannot contain only numeric characters";
    public static final String NIT_IS_REQUIRED = "Nit is required";
    public static final String NIT_REGEX_MESSAGE = "Nit must be exactly 10 numeric digits";
    public static final String ADDRESS_IS_REQUIRED = "Address is required";
    public static final String PHONE_IS_REQUIRED = "Phone is required";
    public static final String URL_LOGO_IS_REQUIRED = "UrlLogo is required";
    public static final String ID_OWNER_IS_REQUIRED = "IdOwner is required";
    public static final String NAME_REGEX = "\\d+";
    public static final String NIT_REGEX = "\\d{10}";
    public static final String PHONE_REGEX_MESSAGE = "Phone must be numeric, up to 13 characters, and can include the '+' symbol";
    public static final String PHONE_REGEX = "^[+]?\\d{1,13}$";
}
