package com.pragma.powerup.application.dto.request;

import com.pragma.powerup.application.utils.constants.openapi.OpenApiDishStatusDtoConstants;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

@Getter
@Setter
@Schema(
        name = OpenApiDishStatusDtoConstants.DTO_NAME,
        description = OpenApiDishStatusDtoConstants.DTO_DESCRIPTION
)
public class DishStatusDto {

    @Schema(
            description = OpenApiDishStatusDtoConstants.DISH_ID_DESCRIPTION,
            example = OpenApiDishStatusDtoConstants.DISH_ID_EXAMPLE,
            required = true
    )
    @NotNull
    @Positive
    private Long dishId;

    @Schema(
            description = OpenApiDishStatusDtoConstants.STATUS_DESCRIPTION,
            example = OpenApiDishStatusDtoConstants.STATUS_EXAMPLE,
            required = true
    )
    @NotNull
    private Boolean status;
}