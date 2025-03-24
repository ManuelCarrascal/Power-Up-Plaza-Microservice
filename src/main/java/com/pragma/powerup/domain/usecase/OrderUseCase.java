package com.pragma.powerup.domain.usecase;

import com.pragma.powerup.domain.api.IOrderServicePort;
import com.pragma.powerup.domain.exception.CustomValidationException;
import com.pragma.powerup.domain.model.Order;
import com.pragma.powerup.domain.model.OrderDish;
import com.pragma.powerup.domain.model.Pagination;
import com.pragma.powerup.domain.spi.IDishPersistencePort;
import com.pragma.powerup.domain.spi.IOrderPersistencePort;
import com.pragma.powerup.domain.spi.IRestaurantPersistencePort;
import com.pragma.powerup.domain.spi.IUserPersistencePort;
import com.pragma.powerup.domain.utils.constants.OrderUseCaseConstants;

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

        order.setStatus(OrderUseCaseConstants.STATUS_PENDING);
        order.setDate(LocalDate.now());

        orderPersistencePort.createOrder(order);
    }

    @Override
    public Pagination<Order> orderList(String orderDirection, Integer currentPage, Integer limitForPage, String status, Long restaurantId) {
        Long employeeId = userPersistencePort.getCurrentUserId();

        validateRestaurant(restaurantId);

        if (!userPersistencePort.isEmployeeOfRestaurant(employeeId, restaurantId)) {
            throw new CustomValidationException(OrderUseCaseConstants.EMPLOYEE_NOT_RESTAURANT_WORKER);
        }
        Pagination<Order> orderPagination = orderPersistencePort.listOrders(orderDirection, currentPage, limitForPage, status, restaurantId);

        List<Order> orders = orderPagination.getContent();
        for (Order order : orders) {
            List<OrderDish> orderDishes = orderPersistencePort.findDishesByOrderId(order.getId());
            order.setDishes(orderDishes);
        }

        return orderPagination;
    }

    private void validateClientHasNoActiveOrder(Long clientId) {
        if (orderPersistencePort.findOrderByClientId(clientId)) {
            throw new CustomValidationException(OrderUseCaseConstants.CLIENT_HAS_ACTIVE_ORDER);
        }
    }

    private void validateRestaurant(Long restaurantId) {
        if (restaurantId == null || restaurantId <= OrderUseCaseConstants.MINIMUM_VALID_ID) {
            throw new CustomValidationException(OrderUseCaseConstants.RESTAURANT_ID_INVALID);
        }
        if (restaurantPersistencePort.findById(restaurantId).isEmpty()) {
            throw new CustomValidationException(OrderUseCaseConstants.RESTAURANT_NOT_FOUND);
        }
    }

    private void validateOrderHasDishes(Order order) {
        if (order.getDishes() == null || order.getDishes().isEmpty()) {
            throw new CustomValidationException(OrderUseCaseConstants.ORDER_MUST_HAVE_DISHES);
        }
    }

    private void validateDishes(List<OrderDish> dishes, Long restaurantId) {
        for (OrderDish dish : dishes) {
            Long dishId = dish.getDishId();

            if (dishId == null || dishId <= OrderUseCaseConstants.MINIMUM_VALID_ID) {
                throw new CustomValidationException(OrderUseCaseConstants.DISH_ID_INVALID);
            }

            if (dish.getQuantity() <= OrderUseCaseConstants.MINIMUM_DISH_QUANTITY) {
                throw new CustomValidationException(OrderUseCaseConstants.DISH_QUANTITY_INVALID);
            }

            if (!restaurantId.equals(dishPersistencePort.getRestaurantIdByDishId(dishId))) {
                throw new CustomValidationException(OrderUseCaseConstants.DISH_RESTAURANT_MISMATCH);
            }
        }
    }
}