package com.pragma.powerup.application.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Schema(description = "DTO for dish list response")
public class DishListResponseDto {
    @Schema(description = "Dish name")
    private String name;

    @Schema(description = "Dish price")
    private Double price;

    @Schema(description = "Dish description")
    private String description;

    @Schema(description = "Dish image URL")
    private String urlImage;

    @Schema(description = "Dish categories")
    private List<String> categories;

    @Schema(description = "Dish status")
    private boolean active;

    @Schema(description = "Restaurant ID")
    private Long idRestaurant;
}