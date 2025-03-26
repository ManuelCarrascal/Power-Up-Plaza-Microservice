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

    void assignEmployeeToOrder(Long orderId, Long idRestaurant);

    void orderReady(Long idOrder, Long idRestaurant);

    void deliverOrder(Long idOrder, String phoneNumber, String pin);
}
