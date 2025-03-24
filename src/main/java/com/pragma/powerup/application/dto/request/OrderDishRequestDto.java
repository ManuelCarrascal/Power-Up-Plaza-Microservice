package com.pragma.powerup.application.dto.request;

import com.pragma.powerup.application.utils.constants.openapi.OpenApiOrderDishRequestDto;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Schema(description = OpenApiOrderDishRequestDto.DESCRIPTION)
public class OrderDishRequestDto {
    @Schema(description = OpenApiOrderDishRequestDto.DISH_ID_DESCRIPTION,
            example = OpenApiOrderDishRequestDto.DISH_ID_EXAMPLE,
            required = true)
    private Long dishId;

    @Schema(description = OpenApiOrderDishRequestDto.QUANTITY_DESCRIPTION,
            example = OpenApiOrderDishRequestDto.QUANTITY_EXAMPLE,
            required = true,
            minimum = OpenApiOrderDishRequestDto.QUANTITY_MINIMUM)
    private Integer quantity;
}