package com.pragma.powerup.application.dto.response;

import com.pragma.powerup.application.utils.constants.openapi.OpenApiDishListResponseDto;
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
@Schema(name = OpenApiDishListResponseDto.DTO_NAME,
        description = OpenApiDishListResponseDto.DTO_DESCRIPTION)
public class DishListResponseDto {
    @Schema(description = OpenApiDishListResponseDto.NAME_DESCRIPTION)
    private String name;

    @Schema(description = OpenApiDishListResponseDto.PRICE_DESCRIPTION)
    private Double price;

    @Schema(description = OpenApiDishListResponseDto.DESCRIPTION_DESCRIPTION)
    private String description;

    @Schema(description = OpenApiDishListResponseDto.URL_IMAGE_DESCRIPTION)
    private String urlImage;

    @Schema(description = OpenApiDishListResponseDto.CATEGORIES_DESCRIPTION)
    private List<String> categories;

    @Schema(description = OpenApiDishListResponseDto.ACTIVE_DESCRIPTION)
    private boolean active;

    @Schema(description = OpenApiDishListResponseDto.RESTAURANT_ID_DESCRIPTION)
    private Long idRestaurant;
}