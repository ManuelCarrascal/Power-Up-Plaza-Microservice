package com.pragma.powerup.application.dto.request;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import javax.validation.constraints.PositiveOrZero;

@Getter
@Setter
public class DishListRequestDto {
    @NotNull
    private Long idRestaurant;
    private Long idCategory;
    @NotNull
    private Boolean active;
    @NotBlank
    private String orderDirection;
    @Positive
    private Integer limitForPage;
    @PositiveOrZero
    private Integer currentPage;
}
