package com.pragma.powerup.application.dto.request;

import com.pragma.powerup.application.utils.constants.openapi.OpenApiAssignEmployeeRequestDto;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;

@Getter
@Setter
@Schema(name = OpenApiAssignEmployeeRequestDto.DTO_NAME,
        description = OpenApiAssignEmployeeRequestDto.DTO_DESCRIPTION)
public class AssignEmployeeRequestDto {
    @NotNull
    @Schema(description = OpenApiAssignEmployeeRequestDto.ID_RESTAURANT_DESCRIPTION,
            example = OpenApiAssignEmployeeRequestDto.ID_RESTAURANT_EXAMPLE,
            required = true)
    private Long idRestaurant;

    @NotNull
    @Schema(description = OpenApiAssignEmployeeRequestDto.ID_ORDER_DESCRIPTION,
            example = OpenApiAssignEmployeeRequestDto.ID_ORDER_EXAMPLE,
            required = true)
    private Long idOrder;
}