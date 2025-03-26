package com.pragma.powerup.application.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import io.swagger.v3.oas.annotations.media.Schema;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Schema(description = "DTO for delivering an order with a PIN verification")
public class DeliverOrderRequestDto {

    @Schema(description = "Unique identifier of the order to be delivered", example = "1", required = true)
    private Long idOrder;

    @Schema(description = "Customer's phone number for PIN verification", example = "+573001234567", required = true)
    private String phoneNumber;

    @Schema(description = "Security PIN received by the customer to validate order delivery", example = "1234", required = true)
    private String pin;
}