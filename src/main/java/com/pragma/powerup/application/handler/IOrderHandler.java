package com.pragma.powerup.application.handler;

import com.pragma.powerup.application.dto.request.AssignEmployeeRequestDto;
import com.pragma.powerup.application.dto.request.OrderListRequestDto;
import com.pragma.powerup.application.dto.request.OrderRequestDto;
import com.pragma.powerup.application.dto.response.OrderListResponseDto;
import com.pragma.powerup.domain.model.Pagination;

public interface IOrderHandler {
    void saveOrder(OrderRequestDto orderRequestDto);
    Pagination<OrderListResponseDto> orderList(OrderListRequestDto orderListRequestDto);
    void assignEmployee(AssignEmployeeRequestDto assignEmployeeRequestDto);

}
