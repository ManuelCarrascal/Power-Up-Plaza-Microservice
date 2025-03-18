package com.pragma.powerup.application.dto.request;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Digits;
import javax.validation.constraints.Positive;

@Getter
@Setter
public class DishUpdateRequestDto {
    @Positive(message = "The price must be a positive number")
    @Digits(integer = 10, fraction = 0, message = "The price must be an integer")

    private Double price;

    private String description;
}
