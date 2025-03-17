package com.pragma.powerup.infrastructure.exceptionhandler;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ErrorResponse {
    private String timestamp;
    private int status;
    private String message;
    private String path;

    public ErrorResponse(int status, String message, String path) {
        this.timestamp = java.time.LocalDateTime.now().toString();
        this.status = status;
        this.message = message;
        this.path = path;
    }

}