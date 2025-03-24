package com.pragma.powerup.application.dto.response;

import com.pragma.powerup.application.utils.constants.openapi.OpenApiRestaurantListResponseDto;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Schema(name = OpenApiRestaurantListResponseDto.DTO_NAME,
        description = OpenApiRestaurantListResponseDto.DTO_DESCRIPTION)
public class RestaurantListResponseDto {
    @Schema(description = OpenApiRestaurantListResponseDto.NAME_DESCRIPTION)
    private String name;

    @Schema(description = OpenApiRestaurantListResponseDto.URL_LOGO_DESCRIPTION)
    private String urlLogo;
}