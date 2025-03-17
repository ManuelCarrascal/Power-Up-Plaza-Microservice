package com.pragma.powerup.domain.exception;

public class DoesNotOwnerException extends RuntimeException {
    public DoesNotOwnerException(String message) {
        super(message);
    }
}
