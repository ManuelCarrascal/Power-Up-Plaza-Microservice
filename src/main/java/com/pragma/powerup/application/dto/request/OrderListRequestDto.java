package com.pragma.powerup.application.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Schema(description = "Request DTO for listing orders with pagination")
public class OrderListRequestDto {
    @Schema(description = "Sort direction (ASC or DESC)", example = "ASC", defaultValue = "ASC")
    private String orderDirection = "ASC";

    @Schema(description = "Current page number", example = "0", defaultValue = "0")
    private Integer currentPage = 0;

    @Schema(description = "Number of items per page", example = "10", defaultValue = "10")
    private Integer limitForPage = 10;

    @Schema(description = "Filter orders by status (optional)", example = "PENDING")
    private String status;

    @Schema(description = "Restaurant ID to list orders from", example = "1", required = true)
    private Long restaurantId;
}