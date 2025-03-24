package com.pragma.powerup.infrastructure.out.jpa.adapter;

import com.pragma.powerup.domain.model.Order;
import com.pragma.powerup.domain.spi.IOrderPersistencePort;
import com.pragma.powerup.infrastructure.out.jpa.entity.OrderDishEntity;
import com.pragma.powerup.infrastructure.out.jpa.entity.OrderEntity;
import com.pragma.powerup.infrastructure.out.jpa.mapper.IOrderDishEntityMapper;
import com.pragma.powerup.infrastructure.out.jpa.mapper.IOrderEntityMapper;
import com.pragma.powerup.infrastructure.out.jpa.repository.IDishRepository;
import com.pragma.powerup.infrastructure.out.jpa.repository.IOrderDishRepository;
import com.pragma.powerup.infrastructure.out.jpa.repository.IOrderRepository;
import com.pragma.powerup.infrastructure.utils.constants.OrderJpaAdapterConstants;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;
import java.util.List;

@Component
@RequiredArgsConstructor
public class OrderJpaAdapter implements IOrderPersistencePort {

    private final IOrderEntityMapper orderEntityMapper;
    private final IOrderRepository orderRepository;
    private final IDishRepository dishRepository;
    private final IOrderDishRepository orderDishRepository;
    private final IOrderDishEntityMapper orderDishEntityMapper;


    @Override
    @Transactional
    public void createOrder(Order order) {
        OrderEntity orderEntity = orderEntityMapper.toEntity(order);
        orderEntity = orderRepository.save(orderEntity);

        List<OrderDishEntity> orderDishEntities = orderDishEntityMapper.toEntityList(order.getDishes());

        for (OrderDishEntity entity : orderDishEntities) {
            entity.setOrder(orderEntity);
            entity.setDish(dishRepository.findById(entity.getDish().getId()).orElseThrow());
        }

        orderDishRepository.saveAll(orderDishEntities);
    }

    @Override
    public boolean findOrderByClientId(Long clientId) {
        return orderRepository.existsByClientIdAndStatusIn(clientId, OrderJpaAdapterConstants.IN_PROGRESS_STATUSES);
    }
}
