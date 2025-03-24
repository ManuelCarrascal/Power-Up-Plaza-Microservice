package com.pragma.powerup.application.dto.response;

import com.pragma.powerup.application.utils.constants.openapi.OpenApiOrderListResponseDto;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Schema(name = OpenApiOrderListResponseDto.DTO_NAME,
        description = OpenApiOrderListResponseDto.DTO_DESCRIPTION)
public class OrderListResponseDto {
    @Schema(description = OpenApiOrderListResponseDto.ID_DESCRIPTION)
    private Long id;

    @Schema(description = OpenApiOrderListResponseDto.CLIENT_ID_DESCRIPTION)
    private Long clientId;

    @Schema(description = OpenApiOrderListResponseDto.RESTAURANT_ID_DESCRIPTION)
    private Long restaurantId;

    @Schema(description = OpenApiOrderListResponseDto.DATE_DESCRIPTION)
    private LocalDate date;

    @Schema(description = OpenApiOrderListResponseDto.STATUS_DESCRIPTION)
    private String status;

    @Schema(description = OpenApiOrderListResponseDto.DISHES_DESCRIPTION)
    private List<OrderDishResponseDto> dishes;
}