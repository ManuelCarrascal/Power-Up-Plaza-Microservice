package com.pragma.powerup.application.dto.request;

import com.pragma.powerup.application.utils.constants.openapi.OpenApiDishListRequestDto;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import javax.validation.constraints.PositiveOrZero;

@Getter
@Setter
@Schema(name = OpenApiDishListRequestDto.DTO_NAME,
        description = OpenApiDishListRequestDto.DTO_DESCRIPTION)
public class DishListRequestDto {
    @Schema(description = OpenApiDishListRequestDto.ID_RESTAURANT_DESCRIPTION,
            example = OpenApiDishListRequestDto.ID_RESTAURANT_EXAMPLE,
            required = true)
    @NotNull
    private Long idRestaurant;

    @Schema(description = OpenApiDishListRequestDto.ID_CATEGORY_DESCRIPTION,
            example = OpenApiDishListRequestDto.ID_CATEGORY_EXAMPLE)
    private Long idCategory;

    @Schema(description = OpenApiDishListRequestDto.ACTIVE_DESCRIPTION,
            example = OpenApiDishListRequestDto.ACTIVE_EXAMPLE,
            required = true)
    @NotNull
    private Boolean active;

    @Schema(description = OpenApiDishListRequestDto.ORDER_DIRECTION_DESCRIPTION,
            example = OpenApiDishListRequestDto.ORDER_DIRECTION_EXAMPLE,
            required = true)
    @NotBlank
    private String orderDirection;

    @Schema(description = OpenApiDishListRequestDto.LIMIT_FOR_PAGE_DESCRIPTION,
            example = OpenApiDishListRequestDto.LIMIT_FOR_PAGE_EXAMPLE,
            minimum = OpenApiDishListRequestDto.LIMIT_FOR_PAGE_MINIMUM)
    @Positive
    private Integer limitForPage;

    @Schema(description = OpenApiDishListRequestDto.CURRENT_PAGE_DESCRIPTION,
            example = OpenApiDishListRequestDto.CURRENT_PAGE_EXAMPLE,
            minimum = OpenApiDishListRequestDto.CURRENT_PAGE_MINIMUM)
    @PositiveOrZero
    private Integer currentPage;
}