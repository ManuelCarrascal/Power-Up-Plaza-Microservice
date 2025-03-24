package com.pragma.powerup.domain.spi;

import com.pragma.powerup.domain.model.Order;
import com.pragma.powerup.domain.model.Pagination;

public interface IOrderPersistencePort {
    void createOrder(Order order);

    boolean findOrderByClientId(Long clientId);

    Pagination<Order> listOrders(
            String orderDirection,
            Integer currentPage,
            Integer limitForPage,
            String status,
            Long restaurantId);
}
