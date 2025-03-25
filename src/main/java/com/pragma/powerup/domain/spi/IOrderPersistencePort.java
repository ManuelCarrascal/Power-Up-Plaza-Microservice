package com.pragma.powerup.domain.spi;

import com.pragma.powerup.domain.model.Order;
import com.pragma.powerup.domain.model.OrderDish;
import com.pragma.powerup.domain.model.Pagination;

import java.util.List;
import java.util.Optional;

public interface IOrderPersistencePort {
    void createOrder(Order order);

    boolean findOrderByClientId(Long clientId);

    Pagination<Order> listOrders(
            String orderDirection,
            Integer currentPage,
            Integer limitForPage,
            String status,
            Long restaurantId);

    List<OrderDish> findDishesByOrderId(Long orderId);
    Optional<Order> findById(Long orderId);
    void updateOrder(Order order);
}
