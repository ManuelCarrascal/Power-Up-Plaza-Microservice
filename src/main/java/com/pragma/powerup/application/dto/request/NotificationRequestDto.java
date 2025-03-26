package com.pragma.powerup.application.dto.request;

import com.pragma.powerup.application.utils.constants.openapi.OpenApiNotificationRequestDto;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

@Schema(description = OpenApiNotificationRequestDto.SCHEMA_DESCRIPTION)
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class NotificationRequestDto {

    @Schema(description = OpenApiNotificationRequestDto.ID_ORDER_DESCRIPTION,
            example = OpenApiNotificationRequestDto.ID_ORDER_EXAMPLE,
            required = true)
    @Positive(message = OpenApiNotificationRequestDto.ID_ORDER_POSITIVE_MESSAGE)
    @NotNull(message = OpenApiNotificationRequestDto.ID_ORDER_NOT_NULL_MESSAGE)
    private Long idOrder;

    @Schema(description = OpenApiNotificationRequestDto.PHONE_DESCRIPTION,
            example = OpenApiNotificationRequestDto.PHONE_EXAMPLE,
            required = true)
    @NotBlank(message = OpenApiNotificationRequestDto.PHONE_NOT_BLANK_MESSAGE)
    private String phone;
}