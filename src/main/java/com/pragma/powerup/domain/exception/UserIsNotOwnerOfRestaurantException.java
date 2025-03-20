package com.pragma.powerup.domain.exception;

public class UserIsNotOwnerOfRestaurantException extends RuntimeException {
    public UserIsNotOwnerOfRestaurantException(String message) {
        super(message);
    }
}
