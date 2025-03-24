package com.pragma.powerup.domain.spi;

import com.pragma.powerup.domain.model.Order;

public interface IOrderPersistencePort {
    void createOrder(Order order);
    boolean findOrderByClientId(Long clientId);
}
