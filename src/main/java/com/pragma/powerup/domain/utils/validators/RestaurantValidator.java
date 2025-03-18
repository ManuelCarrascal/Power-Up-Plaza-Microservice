package com.pragma.powerup.domain.utils.validators;

import com.pragma.powerup.domain.exception.*;
import com.pragma.powerup.domain.model.Restaurant;
import com.pragma.powerup.domain.utils.constants.UserValidatorConstants;

public class RestaurantValidator {

    public void validate(Restaurant restaurant) {
        validName(restaurant.getName());
        validNit(restaurant.getNit());
        validAddress(restaurant.getAddress());
        validPhone(restaurant.getPhone());
        validUrlLogo(restaurant.getUrlLogo());
        validIdOwner(restaurant.getIdOwner());
    }

    private void validName(String name) {
        if (name == null || name.isEmpty()) {
            throw new CustomValidationException(UserValidatorConstants.NAME_IS_REQUIRED);
        }
        if (name.matches(UserValidatorConstants.NAME_REGEX)) {
            throw new CustomValidationException(UserValidatorConstants.NAME_REGEX_MESSAGE);
        }
    }

    private void validNit(String nit) {
        if (nit == null || nit.isEmpty()) {
            throw new CustomValidationException(UserValidatorConstants.NIT_IS_REQUIRED);
        }
        if (!nit.matches(UserValidatorConstants.NIT_REGEX)) {
            throw new CustomValidationException(UserValidatorConstants.NIT_REGEX_MESSAGE);
        }
    }

    private void validAddress(String address) {
        if (address == null || address.isEmpty()) {
            throw new CustomValidationException(UserValidatorConstants.ADDRESS_IS_REQUIRED);
        }
    }

    private void validPhone(String phone) {
        if (phone == null || phone.isEmpty()) {
            throw new CustomValidationException(UserValidatorConstants.PHONE_IS_REQUIRED);
        }
        if (!phone.matches(UserValidatorConstants.PHONE_REGEX)) {
            throw new CustomValidationException(UserValidatorConstants.PHONE_REGEX_MESSAGE);
        }
    }

    private void validUrlLogo(String urlLogo) {
        if (urlLogo == null || urlLogo.isEmpty()) {
            throw new CustomValidationException(UserValidatorConstants.URL_LOGO_IS_REQUIRED);
        }
    }

    private void validIdOwner(Long idOwner) {
        if (idOwner == null) {
            throw new DoesNotOwnerException(UserValidatorConstants.ID_OWNER_IS_REQUIRED);
        }
    }
}