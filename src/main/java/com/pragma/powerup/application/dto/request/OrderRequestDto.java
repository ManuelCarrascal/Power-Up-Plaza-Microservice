package com.pragma.powerup.application.dto.request;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;
@Getter
@Setter
public class OrderRequestDto {
    private Long restaurantId;
    private List<OrderDishRequestDto> dishes;
    private LocalDate date;
}
