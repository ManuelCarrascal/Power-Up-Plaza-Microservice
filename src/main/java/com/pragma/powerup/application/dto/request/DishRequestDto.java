package com.pragma.powerup.application.dto.request;

import com.pragma.powerup.application.utils.constants.DishRequestDtoConstants;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.util.List;

@Getter
@Setter
public class DishRequestDto {
    @NotBlank(message = DishRequestDtoConstants.NAME_IS_MANDATORY)
    private String name;
    @NotNull(message = DishRequestDtoConstants.PRICE_IS_MANDATORY)
    @Positive(message = DishRequestDtoConstants.PRICE_MUST_BE_POSITIVE)
    private double price;
    @NotBlank(message = DishRequestDtoConstants.DESCRIPTION_IS_MANDATORY)
    private String description;
    @NotBlank(message = DishRequestDtoConstants.URL_IMAGE_IS_MANDATORY)
    private String urlImage;
    @NotEmpty(message = DishRequestDtoConstants.CATEGORIES_ARE_MANDATORY)
    private List<String> categories;
    @NotNull(message = DishRequestDtoConstants.ID_RESTAURANT_IS_MANDATORY)
    private Long idRestaurant;

}
