package com.pragma.powerup.application.dto.request;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

@Getter
@Setter
public class DishStatusDto {

    @NotNull
    @Positive
    private Long dishId;
    @NotNull
    private Boolean status;
}
