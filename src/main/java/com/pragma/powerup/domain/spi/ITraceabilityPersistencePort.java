package com.pragma.powerup.domain.spi;

import com.pragma.powerup.domain.model.Order;

public interface ITraceabilityPersistencePort {
    void saveOrderTraceability(Order order, String status);

}
