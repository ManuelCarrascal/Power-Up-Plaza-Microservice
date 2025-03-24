package com.pragma.powerup.application.dto.response;

import com.pragma.powerup.application.utils.constants.openapi.OpenApiOrderDishResponseDto;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Schema(name = OpenApiOrderDishResponseDto.DTO_NAME,
        description = OpenApiOrderDishResponseDto.DTO_DESCRIPTION)
public class OrderDishResponseDto {
    @Schema(description = OpenApiOrderDishResponseDto.DISH_ID_DESCRIPTION)
    private Long dishId;

    @Schema(description = OpenApiOrderDishResponseDto.NAME_DESCRIPTION)
    private String name;

    @Schema(description = OpenApiOrderDishResponseDto.QUANTITY_DESCRIPTION)
    private Integer quantity;

    @Schema(description = OpenApiOrderDishResponseDto.PRICE_DESCRIPTION)
    private Double price;
}