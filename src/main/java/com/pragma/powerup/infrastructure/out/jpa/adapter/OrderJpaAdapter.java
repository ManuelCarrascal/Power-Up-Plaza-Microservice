package com.pragma.powerup.infrastructure.out.jpa.adapter;

import com.pragma.powerup.domain.model.Order;
import com.pragma.powerup.domain.model.Pagination;
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
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
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

    @Override
    public Pagination<Order> listOrders(String orderDirection, Integer currentPage, Integer limitForPage, String status, Long restaurantId) {
        Sort sort = Sort.by(Sort.Direction.fromString(orderDirection), "date");
        Pageable pageable = PageRequest.of(currentPage, limitForPage, sort);

        Page<OrderEntity> orderEntityPage;

        if (status != null && !status.isEmpty()) {
            orderEntityPage = orderRepository.findAllByStatusAndRestaurantId(status, restaurantId, pageable);
        } else {
            orderEntityPage = orderRepository.findAllByRestaurantId(restaurantId, pageable);
        }

        List<OrderEntity> orderEntities = orderEntityPage.getContent();
        List<Order> orders = orderEntityMapper.toOrderList(orderEntities);

        // For each order, fetch and map its dishes
        for (Order order : orders) {
            List<OrderDishEntity> orderDishEntities = orderDishRepository.findByOrderId(order.getId());
            order.setDishes(orderDishEntityMapper.toOrderDishList(orderDishEntities));
        }

        return new Pagination<>(
                Sort.Direction.fromString(orderDirection).equals(Sort.Direction.ASC),
                currentPage,
                limitForPage,
                orderEntityPage.getTotalElements(),
                orders
        );
    }
}
