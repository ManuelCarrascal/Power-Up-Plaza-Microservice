package com.pragma.powerup.application.dto.request;

import com.pragma.powerup.application.utils.constants.OrderListRequestDtoConstants;
import com.pragma.powerup.application.utils.constants.openapi.OpenApiOrderListRequestDto;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotNull;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Schema(name = OpenApiOrderListRequestDto.DTO_NAME,
        description = OpenApiOrderListRequestDto.DTO_DESCRIPTION)
public class OrderListRequestDto {
    @Schema(description = OpenApiOrderListRequestDto.ORDER_DIRECTION_DESCRIPTION,
            example = OpenApiOrderListRequestDto.ORDER_DIRECTION_EXAMPLE,
            defaultValue = OpenApiOrderListRequestDto.ORDER_DIRECTION_DEFAULT)
    private String orderDirection = OpenApiOrderListRequestDto.ORDER_DIRECTION_DEFAULT;

    @Schema(description = OpenApiOrderListRequestDto.CURRENT_PAGE_DESCRIPTION,
            example = OpenApiOrderListRequestDto.CURRENT_PAGE_EXAMPLE,
            defaultValue = OpenApiOrderListRequestDto.CURRENT_PAGE_DEFAULT)
    private Integer currentPage = OrderListRequestDtoConstants.CURRENT_PAGE_DEFAULT;

    @Schema(description = OpenApiOrderListRequestDto.LIMIT_FOR_PAGE_DESCRIPTION,
            example = OpenApiOrderListRequestDto.LIMIT_FOR_PAGE_EXAMPLE,
            defaultValue = OpenApiOrderListRequestDto.LIMIT_FOR_PAGE_DEFAULT)
    private Integer limitForPage = OrderListRequestDtoConstants.LIMIT_FOR_PAGE_DEFAULT;

    @Schema(description = OpenApiOrderListRequestDto.STATUS_DESCRIPTION,
            example = OpenApiOrderListRequestDto.STATUS_EXAMPLE)
    private String status;

    @Schema(description = OpenApiOrderListRequestDto.RESTAURANT_ID_DESCRIPTION,
            example = OpenApiOrderListRequestDto.RESTAURANT_ID_EXAMPLE,
            required = true)
    @NotNull(message = OrderListRequestDtoConstants.RESTAURANT_ID_IS_MANDATORY)
    private Long restaurantId;
}