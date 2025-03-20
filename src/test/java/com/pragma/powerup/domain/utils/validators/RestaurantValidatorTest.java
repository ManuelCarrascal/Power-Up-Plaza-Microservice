package com.pragma.powerup.domain.utils.validators;

import com.pragma.powerup.domain.exception.CustomValidationException;
import com.pragma.powerup.domain.model.Restaurant;
import com.pragma.powerup.domain.utils.constants.UserValidatorConstants;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class RestaurantValidatorTest {

    private final RestaurantValidator restaurantValidator = new RestaurantValidator();

    @Test
    void shouldNotThrowExceptionWhenAllFieldsAreValid() {
        Restaurant restaurant = new Restaurant.Builder()
                .name("ValidName")
                .nit("1234567890")
                .address("Valid Address")
                .phone("1234567890")
                .urlLogo("http://validurl.com/logo.png")
                .idOwner(1L)
                .build();

        assertDoesNotThrow(() -> restaurantValidator.validate(restaurant));
    }

    @Test
    void shouldThrowExceptionWhenNameIsNull() {
        // Given
        Restaurant restaurant = new Restaurant.Builder()
                .name(null)
                .nit("1234567890")
                .address("Valid Address")
                .phone("1234567890")
                .urlLogo("http://validurl.com/logo.png")
                .idOwner(1L)
                .build();

        // When & Then
        assertThrows(CustomValidationException.class,
                () -> restaurantValidator.validate(restaurant),
                UserValidatorConstants.NAME_IS_REQUIRED);
    }

    @Test
    void shouldThrowExceptionWhenNameIsEmpty() {
        // Given
        Restaurant restaurant = new Restaurant.Builder()
                .name("")
                .nit("1234567890")
                .address("Valid Address")
                .phone("1234567890")
                .urlLogo("http://validurl.com/logo.png")
                .idOwner(1L)
                .build();

        // When & Then
        assertThrows(CustomValidationException.class,
                () -> restaurantValidator.validate(restaurant),
                UserValidatorConstants.NAME_IS_REQUIRED);
    }

    @Test
    void shouldThrowExceptionWhenNitIsInvalid() {
        // Given
        Restaurant restaurant = new Restaurant.Builder()
                .name("Valid Name")
                .nit("invalid-nit!")
                .address("Valid Address")
                .phone("1234567890")
                .urlLogo("http://validurl.com/logo.png")
                .idOwner(1L)
                .build();

        // When & Then
        assertThrows(CustomValidationException.class,
                () -> restaurantValidator.validate(restaurant),
                UserValidatorConstants.NIT_REGEX_MESSAGE);
    }

    @Test
    void shouldThrowExceptionWhenAddressIsNull() {
        // Given
        Restaurant restaurant = new Restaurant.Builder()
                .name("Valid Name")
                .nit("1234567890")
                .address(null)
                .phone("1234567890")
                .urlLogo("http://validurl.com/logo.png")
                .idOwner(1L)
                .build();

        // When & Then
        assertThrows(CustomValidationException.class,
                () -> restaurantValidator.validate(restaurant),
                UserValidatorConstants.ADDRESS_IS_REQUIRED);
    }

    @Test
    void shouldThrowExceptionWhenPhoneIsInvalid() {
        // Given
        Restaurant restaurant = new Restaurant.Builder()
                .name("Valid Name")
                .nit("1234567890")
                .address("Valid Address")
                .phone("invalid-phone")
                .urlLogo("http://validurl.com/logo.png")
                .idOwner(1L)
                .build();

        // When & Then
        assertThrows(CustomValidationException.class,
                () -> restaurantValidator.validate(restaurant),
                UserValidatorConstants.PHONE_REGEX_MESSAGE);
    }

    @Test
    void shouldThrowExceptionWhenUrlLogoIsNull() {
        // Given
        Restaurant restaurant = new Restaurant.Builder()
                .name("Valid Name")
                .nit("1234567890")
                .address("Valid Address")
                .phone("1234567890")
                .urlLogo(null)
                .idOwner(1L)
                .build();

        // When & Then
        assertThrows(CustomValidationException.class,
                () -> restaurantValidator.validate(restaurant),
                UserValidatorConstants.URL_LOGO_IS_REQUIRED);
    }


}