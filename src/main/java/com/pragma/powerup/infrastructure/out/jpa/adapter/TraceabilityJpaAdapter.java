package com.pragma.powerup.infrastructure.out.jpa.adapter;

import com.pragma.powerup.application.dto.request.OrderDishRequest;
import com.pragma.powerup.application.dto.request.OrderTraceabilityRequestDto;
import com.pragma.powerup.application.dto.request.StatusChangeRequest;
import com.pragma.powerup.application.mapper.ITraceabilityRequestMapper;
import com.pragma.powerup.domain.model.Order;
import com.pragma.powerup.domain.spi.ITraceabilityPersistencePort;
import com.pragma.powerup.infrastructure.out.feign.ITraceabilityFeignClient;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class TraceabilityJpaAdapter implements ITraceabilityPersistencePort {
    private final ITraceabilityFeignClient traceabilityFeignClient;
    private final ITraceabilityRequestMapper traceabilityRequestMapper;

    @Override
    public void saveOrderTraceability(Order order, String status) {
        OrderTraceabilityRequestDto traceabilityDto = new OrderTraceabilityRequestDto();

        traceabilityDto.setId(order.getId());
        traceabilityDto.setIdClient(order.getClientId());
        traceabilityDto.setIdEmployee(order.getIdEmployee());
        traceabilityDto.setIdRestaurant(order.getRestaurantId());

        if (order.getDishes() != null) {
            List<OrderDishRequest> dishRequests = traceabilityRequestMapper.toOrderDishRequestList(order.getDishes());
            traceabilityDto.setDishes(dishRequests);
        } else {
            traceabilityDto.setDishes(new ArrayList<>());
        }

        StatusChangeRequest statusChange = new StatusChangeRequest(
                LocalDateTime.now().format(DateTimeFormatter.ISO_DATE_TIME),
                status
        );

        List<StatusChangeRequest> statusChanges = new ArrayList<>();
        statusChanges.add(statusChange);
        traceabilityDto.setStatusChanges(statusChanges);

        traceabilityFeignClient.createOrderTraceability(traceabilityDto);
    }
}