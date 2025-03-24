package com.pragma.powerup.application.dto.response;

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
@Schema(description = "DTO for order list response")
public class OrderListResponseDto {
    @Schema(description = "Order ID")
    private Long id;

    @Schema(description = "Client ID")
    private Long clientId;

    @Schema(description = "Restaurant ID")
    private Long restaurantId;

    @Schema(description = "Order date")
    private LocalDate date;

    @Schema(description = "Order status")
    private String status;

    @Schema(description = "Order dishes")
    private List<OrderDishResponseDto> dishes;
}
