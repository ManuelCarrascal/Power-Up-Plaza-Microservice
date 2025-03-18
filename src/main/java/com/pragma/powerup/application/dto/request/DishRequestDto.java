package com.pragma.powerup.application.dto.request;

import com.pragma.powerup.application.utils.constants.DishRequestDtoConstants;
import com.pragma.powerup.application.utils.constants.openapi.OpenApiDishRequestDtoConstants;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.*;
import java.util.List;

@Getter
@Setter
@Schema(name = OpenApiDishRequestDtoConstants.DTO_NAME, description = OpenApiDishRequestDtoConstants.DTO_DESCRIPTION)
public class DishRequestDto {

    @Schema(description = OpenApiDishRequestDtoConstants.NAME_DESCRIPTION,
            example = OpenApiDishRequestDtoConstants.NAME_EXAMPLE,
            required = true)
    @NotBlank(message = DishRequestDtoConstants.NAME_IS_MANDATORY)
    private String name;

    @Schema(description = OpenApiDishRequestDtoConstants.PRICE_DESCRIPTION,
            example = OpenApiDishRequestDtoConstants.PRICE_EXAMPLE,
            required = true)
    @NotNull(message = DishRequestDtoConstants.PRICE_IS_MANDATORY)
    @Positive(message = DishRequestDtoConstants.PRICE_MUST_BE_POSITIVE)
    @Digits(integer = 10, fraction = 0, message = "The price must be an integer")
    private double price;

    @Schema(description = OpenApiDishRequestDtoConstants.DESCRIPTION_DESCRIPTION,
            example = OpenApiDishRequestDtoConstants.DESCRIPTION_EXAMPLE,
            required = true)
    @NotBlank(message = DishRequestDtoConstants.DESCRIPTION_IS_MANDATORY)
    private String description;

    @Schema(description = OpenApiDishRequestDtoConstants.URL_IMAGE_DESCRIPTION,
            example = OpenApiDishRequestDtoConstants.URL_IMAGE_EXAMPLE,
            required = true)
    @NotBlank(message = DishRequestDtoConstants.URL_IMAGE_IS_MANDATORY)
    private String urlImage;

    @Schema(description = OpenApiDishRequestDtoConstants.CATEGORIES_DESCRIPTION,
            example = OpenApiDishRequestDtoConstants.CATEGORIES_EXAMPLE,
            required = true)
    @NotEmpty(message = DishRequestDtoConstants.CATEGORIES_ARE_MANDATORY)
    private List<String> categories;

    @Schema(description = OpenApiDishRequestDtoConstants.ID_RESTAURANT_DESCRIPTION,
            example = OpenApiDishRequestDtoConstants.ID_RESTAURANT_EXAMPLE,
            required = true)
    @NotNull(message = DishRequestDtoConstants.ID_RESTAURANT_IS_MANDATORY)
    private Long idRestaurant;
}