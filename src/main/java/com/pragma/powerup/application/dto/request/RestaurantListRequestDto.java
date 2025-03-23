package com.pragma.powerup.application.dto.request;

import com.pragma.powerup.application.utils.constants.openapi.OpenApiRestaurantListRequestDto;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Positive;
import javax.validation.constraints.PositiveOrZero;

@Getter
@Setter
@Schema(name = OpenApiRestaurantListRequestDto.DTO_NAME,
        description = OpenApiRestaurantListRequestDto.DTO_DESCRIPTION)
public class RestaurantListRequestDto {
    @Schema(description = OpenApiRestaurantListRequestDto.ORDER_DIRECTION_DESCRIPTION,
            example = OpenApiRestaurantListRequestDto.ORDER_DIRECTION_EXAMPLE,
            required = true)
    @NotBlank
    private String orderDirection;

    @Schema(description = OpenApiRestaurantListRequestDto.LIMIT_FOR_PAGE_DESCRIPTION,
            example = OpenApiRestaurantListRequestDto.LIMIT_FOR_PAGE_EXAMPLE,
            minimum = OpenApiRestaurantListRequestDto.LIMIT_FOR_PAGE_MINIMUM)
    @Positive
    private Integer limitForPage;

    @Schema(description = OpenApiRestaurantListRequestDto.CURRENT_PAGE_DESCRIPTION,
            example = OpenApiRestaurantListRequestDto.CURRENT_PAGE_EXAMPLE,
            minimum = OpenApiRestaurantListRequestDto.CURRENT_PAGE_MINIMUM)
    @PositiveOrZero
    private Integer currentPage;
}