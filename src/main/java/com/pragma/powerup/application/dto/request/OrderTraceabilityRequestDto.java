package com.pragma.powerup.application.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class OrderTraceabilityRequestDto {
    private Long id;
    private Long idClient;
    private Long idEmployee;
    private Long idRestaurant;
    private List<OrderDishRequest> dishes;
    private List<StatusChangeRequest> statusChanges;
}