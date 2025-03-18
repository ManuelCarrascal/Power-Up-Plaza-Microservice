package com.pragma.powerup.application.dto.request;

import com.pragma.powerup.application.utils.constants.DishRequestDtoConstants;
import com.pragma.powerup.application.utils.constants.openapi.OpenApiDishUpdateRequestDtoConstants;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Digits;
import javax.validation.constraints.Positive;

@Getter
@Setter
@Schema(
        name = OpenApiDishUpdateRequestDtoConstants.DTO_NAME,
        description = OpenApiDishUpdateRequestDtoConstants.DTO_DESCRIPTION
)
public class DishUpdateRequestDto {
    @Schema(
            description = OpenApiDishUpdateRequestDtoConstants.PRICE_DESCRIPTION,
            example = OpenApiDishUpdateRequestDtoConstants.PRICE_EXAMPLE,
            required = false
    )
    @Positive(message = DishRequestDtoConstants.PRICE_MUST_BE_POSITIVE)
    @Digits(
            integer = DishRequestDtoConstants.PRICE_INTEGER_PART,
            fraction = DishRequestDtoConstants.PRICE_FRACTION_PART,
            message = DishRequestDtoConstants.PRICE_MUST_BE_INTEGER
    )
    private Double price;

    @Schema(
            description = OpenApiDishUpdateRequestDtoConstants.DESCRIPTION_DESCRIPTION,
            example = OpenApiDishUpdateRequestDtoConstants.DESCRIPTION_EXAMPLE,
            required = false
    )
    private String description;
}