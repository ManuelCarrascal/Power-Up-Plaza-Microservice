package com.pragma.powerup.application.dto.request;

import com.pragma.powerup.application.utils.constants.openapi.OpenApiOrderRequestDto;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@Schema(description = OpenApiOrderRequestDto.DESCRIPTION)
public class OrderRequestDto {
    @Schema(description = OpenApiOrderRequestDto.RESTAURANT_ID_DESCRIPTION,
            example = OpenApiOrderRequestDto.RESTAURANT_ID_EXAMPLE,
            required = true)
    private Long restaurantId;

    @Schema(description = OpenApiOrderRequestDto.DISHES_DESCRIPTION,
            required = true)
    private List<OrderDishRequestDto> dishes;

    @Schema(description = OpenApiOrderRequestDto.DATE_DESCRIPTION,
            example = OpenApiOrderRequestDto.DATE_EXAMPLE)
    private LocalDate date;
}