package com.pragma.powerup.application.dto.request;

import com.pragma.powerup.application.utils.constants.RestaurantRequestDtoConstants;
import com.pragma.powerup.application.utils.constants.openapi.OpenApiRestaurantRequestDtoConstants;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import static com.pragma.powerup.application.utils.constants.RestaurantRequestDtoConstants.ID_OWNER_IS_MANDATORY;

@Schema(description = OpenApiRestaurantRequestDtoConstants.RESTAURANT_REQUEST_DTO_DESCRIPTION)
@Getter
@Setter
public class RestaurantRequestDto {
    @Schema(description = OpenApiRestaurantRequestDtoConstants.RESTAURANT_NAME, example = OpenApiRestaurantRequestDtoConstants.RESTAURANT_NAME_EXAMPLE)
    @NotBlank(message = RestaurantRequestDtoConstants.NAME_IS_MANDATORY)
    @Pattern(regexp = RestaurantRequestDtoConstants.NAME_REGEX,
            message = RestaurantRequestDtoConstants.NAME_CANNOT_CONTAIN_ONLY_NUMERIC_CHARACTERS)
    private String name;

    @Schema(description = OpenApiRestaurantRequestDtoConstants.RESTAURANT_NIT, example = OpenApiRestaurantRequestDtoConstants.RESTAURANT_NIT_EXAMPLE)
    @NotBlank(message = RestaurantRequestDtoConstants.NIT_IS_MANDATORY)
    @Pattern(regexp = RestaurantRequestDtoConstants.NIT_REGEX,
            message = RestaurantRequestDtoConstants.NIT_PATTERN_MESSAGE)
    private String nit;

    @Schema(description = OpenApiRestaurantRequestDtoConstants.RESTAURANT_ADDRESS, example = OpenApiRestaurantRequestDtoConstants.RESTAURANT_ADDRESS_EXAMPLE)
    @NotBlank(message = RestaurantRequestDtoConstants.ADDRESS_IS_MANDATORY)
    private String address;

    @Schema(description = OpenApiRestaurantRequestDtoConstants.RESTAURANT_PHONE, example = OpenApiRestaurantRequestDtoConstants.RESTAURANT_PHONE_EXAMPLE)
    @NotBlank(message = RestaurantRequestDtoConstants.PHONE_IS_MANDATORY)
    @Pattern(regexp = RestaurantRequestDtoConstants.PHONE_PATTERN,
            message = RestaurantRequestDtoConstants.PHONE_PATTERN_MESSAGE)
    private String phone;

    @Schema(description = OpenApiRestaurantRequestDtoConstants.RESTAURANT_LOGO,
            example = OpenApiRestaurantRequestDtoConstants.RESTAURANT_LOGO_EXAMPLE)
    @NotBlank(message = RestaurantRequestDtoConstants.URL_LOGO_IS_MANDATORY)
    private String urlLogo;

    @Schema(description = OpenApiRestaurantRequestDtoConstants.RESTAURANT_OWNER, example = OpenApiRestaurantRequestDtoConstants.RESTAURANT_OWNER_EXAMPLE)
    @NotNull(message = ID_OWNER_IS_MANDATORY)
    private Long idOwner;
}