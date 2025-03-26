package com.pragma.powerup.domain.usecase;

import com.pragma.powerup.domain.api.IOrderServicePort;
import com.pragma.powerup.domain.exception.CustomValidationException;
import com.pragma.powerup.domain.model.Order;
import com.pragma.powerup.domain.model.OrderDish;
import com.pragma.powerup.domain.model.Pagination;
import com.pragma.powerup.domain.spi.*;
import com.pragma.powerup.domain.utils.constants.OrderUseCaseConstants;

import java.time.LocalDate;
import java.util.List;

public class OrderUseCase implements IOrderServicePort {

    private final IOrderPersistencePort orderPersistencePort;
    private final IRestaurantPersistencePort restaurantPersistencePort;
    private final IDishPersistencePort dishPersistencePort;
    private final IUserPersistencePort userPersistencePort;
    private final INotificationPersistencePort notificationPersistencePort;
    private final ITraceabilityPersistencePort traceabilityPersistencePort;

    public OrderUseCase(IOrderPersistencePort orderPersistencePort, IRestaurantPersistencePort restaurantPersistencePort, IDishPersistencePort dishPersistencePort, IUserPersistencePort userPersistencePort, INotificationPersistencePort notificationPersistencePort, ITraceabilityPersistencePort traceabilityPersistencePort) {
        this.orderPersistencePort = orderPersistencePort;
        this.restaurantPersistencePort = restaurantPersistencePort;
        this.dishPersistencePort = dishPersistencePort;
        this.userPersistencePort = userPersistencePort;
        this.notificationPersistencePort = notificationPersistencePort;
        this.traceabilityPersistencePort = traceabilityPersistencePort;
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
        traceabilityPersistencePort.saveOrderTraceability(order, OrderUseCaseConstants.STATUS_PENDING);
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

    @Override
    public void assignEmployeeToOrder(Long orderId, Long idRestaurant) {
        Long employeeId = userPersistencePort.getCurrentUserId();

        Order order = validateOrderOperation(
                orderId,
                idRestaurant,
                OrderUseCaseConstants.STATUS_PENDING,
                OrderUseCaseConstants.ORDER_NOT_PENDING
        );

        order.setIdEmployee(employeeId);
        order.setStatus(OrderUseCaseConstants.STATUS_IN_PREPARATION);

        orderPersistencePort.updateOrder(order);
        traceabilityPersistencePort.saveOrderTraceability(order, OrderUseCaseConstants.STATUS_IN_PREPARATION);
    }

    @Override
    public void orderReady(Long idOrder, Long idRestaurant) {
        Order order = validateOrderOperation(
                idOrder,
                idRestaurant,
                OrderUseCaseConstants.STATUS_IN_PREPARATION,
                OrderUseCaseConstants.ORDER_NOT_IN_PREPARATION
        );

        order.setStatus(OrderUseCaseConstants.STATUS_READY);
        String clientPhoneNumber = userPersistencePort.getPhoneNumberById(order.getClientId());

        notificationPersistencePort.saveNotification(idOrder, clientPhoneNumber);
        orderPersistencePort.updateOrder(order);
        traceabilityPersistencePort.saveOrderTraceability(order, OrderUseCaseConstants.STATUS_READY);
    }

    @Override
    public void deliverOrder(Long idOrder, String phoneNumber, String pin) {
        Order order = orderPersistencePort.findById(idOrder)
                .orElseThrow(() -> new CustomValidationException(OrderUseCaseConstants.ORDER_NOT_FOUND));

        if (!OrderUseCaseConstants.STATUS_READY.equals(order.getStatus())) {
            throw new CustomValidationException(OrderUseCaseConstants.ORDER_NOT_READY);
        }

        if (pin == null || pin.trim().isEmpty()) {
            throw new CustomValidationException(OrderUseCaseConstants.PIN_REQUIRED);
        }

        notificationPersistencePort.validatePin(idOrder, phoneNumber, pin);

        order.setStatus(OrderUseCaseConstants.STATUS_DELIVERED);
        orderPersistencePort.updateOrder(order);
        traceabilityPersistencePort.saveOrderTraceability(order, OrderUseCaseConstants.STATUS_DELIVERED);
    }

    @Override
    public void cancelOrder(Long orderId) {
        Long clientId = userPersistencePort.getCurrentUserId();

        Order order = orderPersistencePort.findById(orderId)
                .orElseThrow(() -> new CustomValidationException(OrderUseCaseConstants.ORDER_NOT_FOUND));

        if (!order.getClientId().equals(clientId)) {
            throw new CustomValidationException(OrderUseCaseConstants.CLIENT_NOT_ORDER_OWNER);
        }

        if (!OrderUseCaseConstants.STATUS_PENDING.equals(order.getStatus())) {
            throw new CustomValidationException(OrderUseCaseConstants.ORDER_CANNOT_BE_CANCELED);
        }

        order.setStatus(OrderUseCaseConstants.STATUS_CANCELED);
        orderPersistencePort.updateOrder(order);
        traceabilityPersistencePort.saveOrderTraceability(order, OrderUseCaseConstants.STATUS_CANCELED);
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

    private Order validateOrderOperation(Long orderId, Long restaurantId, String expectedStatus, String errorMessage) {
        Long employeeId = userPersistencePort.getCurrentUserId();

        if (orderId == null || orderId <= OrderUseCaseConstants.MINIMUM_VALID_ID) {
            throw new CustomValidationException(OrderUseCaseConstants.ORDER_ID_INVALID);
        }

        validateRestaurant(restaurantId);

        if (!userPersistencePort.isEmployeeOfRestaurant(employeeId, restaurantId)) {
            throw new CustomValidationException(OrderUseCaseConstants.EMPLOYEE_NOT_RESTAURANT_WORKER);
        }

        Order order = orderPersistencePort.findById(orderId)
                .orElseThrow(() -> new CustomValidationException(OrderUseCaseConstants.ORDER_NOT_FOUND));

        if (!order.getRestaurantId().equals(restaurantId)) {
            throw new CustomValidationException(OrderUseCaseConstants.ORDER_NOT_RESTAURANT);
        }

        if (!expectedStatus.equals(order.getStatus())) {
            throw new CustomValidationException(errorMessage);
        }

        return order;
    }
}