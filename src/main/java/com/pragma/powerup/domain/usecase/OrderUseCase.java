package com.pragma.powerup.domain.usecase;

import com.pragma.powerup.domain.api.IOrderServicePort;
import com.pragma.powerup.domain.exception.CustomValidationException;
import com.pragma.powerup.domain.model.Order;
import com.pragma.powerup.domain.model.OrderDish;
import com.pragma.powerup.domain.spi.IDishPersistencePort;
import com.pragma.powerup.domain.spi.IOrderPersistencePort;
import com.pragma.powerup.domain.spi.IRestaurantPersistencePort;
import com.pragma.powerup.domain.spi.IUserPersistencePort;

import java.time.LocalDate;
import java.util.List;

public class OrderUseCase implements IOrderServicePort {

    private final IOrderPersistencePort orderPersistencePort;
    private final IRestaurantPersistencePort restaurantPersistencePort;
    private final IDishPersistencePort dishPersistencePort;
    private final IUserPersistencePort userPersistencePort;

    public OrderUseCase(IOrderPersistencePort orderPersistencePort, IRestaurantPersistencePort restaurantPersistencePort, IDishPersistencePort dishPersistencePort, IUserPersistencePort userPersistencePort) {
        this.orderPersistencePort = orderPersistencePort;
        this.restaurantPersistencePort = restaurantPersistencePort;
        this.dishPersistencePort = dishPersistencePort;
        this.userPersistencePort = userPersistencePort;
    }

    @Override
    public void createOrder(Order order) {
        Long clientId = userPersistencePort.getCurrentUserId();
        order.setClientId(clientId);
        validateClientHasNoActiveOrder(order.getClientId());
        validateRestaurant(order.getRestaurantId());
        validateOrderHasDishes(order);
        validateDishes(order.getDishes(), order.getRestaurantId());

        order.setStatus("PENDING");
        order.setDate(LocalDate.now());

        orderPersistencePort.createOrder(order);
    }

    private void validateClientHasNoActiveOrder(Long clientId) {
        if (orderPersistencePort.findOrderByClientId(clientId)) {
            throw new CustomValidationException("Client already has an order in progress");
        }
    }

    private void validateRestaurant(Long restaurantId) {
        if (restaurantId == null || restaurantId <= 0) {
            throw new CustomValidationException("Restaurant id invalid");
        }
        if (restaurantPersistencePort.findById(restaurantId).isEmpty()) {
            throw new CustomValidationException("Restaurant not found");
        }
    }

    private void validateOrderHasDishes(Order order) {
        if (order.getDishes() == null || order.getDishes().isEmpty()) {
            throw new CustomValidationException("Order must have at least one dish");
        }
    }

    private void validateDishes(List<OrderDish> dishes, Long restaurantId) {
        for (OrderDish dish : dishes) {
            Long dishId = dish.getDishId();

            if (dishId == null || dishId <= 0) {
                throw new CustomValidationException("Dish id invalid");
            }

            if (dish.getQuantity() <= 0) {
                throw new CustomValidationException("Dish quantity must be greater than zero");
            }

            if (!restaurantId.equals(dishPersistencePort.getRestaurantIdByDishId(dishId))) {
                throw new CustomValidationException("Dish id mismatch");
            }
        }
    }
}
