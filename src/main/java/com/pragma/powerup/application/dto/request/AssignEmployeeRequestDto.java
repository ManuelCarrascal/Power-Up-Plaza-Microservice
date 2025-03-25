package com.pragma.powerup.application.dto.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AssignEmployeeRequestDto {
    private Long idRestaurant;
    private Long idOrder;
}
