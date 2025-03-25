package com.pragma.powerup.application.handler.impl;

import com.pragma.powerup.application.dto.request.AssignEmployeeRequestDto;
import com.pragma.powerup.application.dto.request.OrderListRequestDto;
import com.pragma.powerup.application.dto.request.OrderRequestDto;
import com.pragma.powerup.application.dto.response.OrderListResponseDto;
import com.pragma.powerup.application.handler.IOrderHandler;
import com.pragma.powerup.application.mapper.IOrderRequestMapper;
import com.pragma.powerup.application.mapper.IPaginationOrderResponseMapper;
import com.pragma.powerup.domain.api.IOrderServicePort;
import com.pragma.powerup.domain.model.Pagination;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class OrderHandler implements IOrderHandler {

    private final IOrderServicePort orderServicePort;
    private final IOrderRequestMapper orderRequestMapper;
    private final IPaginationOrderResponseMapper paginationOrderResponseMapper;

    @Override
    public void saveOrder(OrderRequestDto orderRequestDto) {
        orderServicePort.createOrder(orderRequestMapper.toOrder(orderRequestDto));
    }

    @Override
    public Pagination<OrderListResponseDto> orderList(OrderListRequestDto orderListRequestDto) {
        return paginationOrderResponseMapper.toPaginationResponse(
                orderServicePort.orderList(
                        orderListRequestDto.getOrderDirection(),
                        orderListRequestDto.getCurrentPage(),
                        orderListRequestDto.getLimitForPage(),
                        orderListRequestDto.getStatus(),
                        orderListRequestDto.getRestaurantId()
                )
        );
    }

    @Override
    public void assignEmployee(AssignEmployeeRequestDto assignEmployeeRequestDto) {
        orderServicePort.assignEmployeeToOrder(assignEmployeeRequestDto.getIdOrder(), assignEmployeeRequestDto.getIdRestaurant());
    }

    @Override
    public void orderReady(AssignEmployeeRequestDto assignEmployeeRequestDto) {
        orderServicePort.orderReady(assignEmployeeRequestDto.getIdOrder(), assignEmployeeRequestDto.getIdRestaurant());
    }
}
