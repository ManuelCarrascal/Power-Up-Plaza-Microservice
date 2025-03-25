package com.pragma.powerup.application.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

@Schema(description = "Data transfer object for notification requests")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class NotificationRequestDto {

    @Schema(description = "Unique identifier of the order", example = "1", required = true)
    @Positive(message = "Order ID must be positive")
    @NotNull(message = "Order ID cannot be null")
    private Long idOrder;

    @Schema(description = "Phone number to send the notification to", example = "+573001234567", required = true)
    @NotBlank(message = "Phone number cannot be blank")
    private String phone;
}
