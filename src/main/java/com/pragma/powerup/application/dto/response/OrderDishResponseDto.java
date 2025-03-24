package com.pragma.powerup.application.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Schema(description = "DTO for order dish in order list")
public class OrderDishResponseDto {
    @Schema(description = "Dish ID")
    private Long dishId;

    @Schema(description = "Dish name")
    private String name;

    @Schema(description = "Quantity ordered")
    private Integer quantity;

    @Schema(description = "Unit price")
    private Double price;
}