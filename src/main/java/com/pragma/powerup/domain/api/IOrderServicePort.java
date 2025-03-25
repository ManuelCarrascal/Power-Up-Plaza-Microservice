package com.pragma.powerup.domain.api;

import com.pragma.powerup.domain.model.Order;
import com.pragma.powerup.domain.model.Pagination;

public interface IOrderServicePort {
    void createOrder(Order order);

    Pagination<Order> orderList(
            String orderDirection,
            Integer currentPage,
            Integer limitForPage,
            String status,
            Long restaurantId);

    void assingEmployeeToOrder(Long orderId, Long idRestaurant);
}
