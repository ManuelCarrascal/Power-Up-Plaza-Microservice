package com.pragma.powerup.domain.usecase;

import com.pragma.powerup.domain.exception.CustomValidationException;
import com.pragma.powerup.domain.model.Order;
import com.pragma.powerup.domain.model.OrderDish;
import com.pragma.powerup.domain.model.Pagination;
import com.pragma.powerup.domain.model.Restaurant;
import com.pragma.powerup.domain.spi.IDishPersistencePort;
import com.pragma.powerup.domain.spi.IOrderPersistencePort;
import com.pragma.powerup.domain.spi.IRestaurantPersistencePort;
import com.pragma.powerup.domain.spi.IUserPersistencePort;
import com.pragma.powerup.domain.utils.constants.OrderUseCaseConstants;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class OrderUseCaseTest {

    @Mock
    private IOrderPersistencePort orderPersistencePort;

    @Mock
    private IRestaurantPersistencePort restaurantPersistencePort;

    @Mock
    private IDishPersistencePort dishPersistencePort;

    @Mock
    private IUserPersistencePort userPersistencePort;

    @InjectMocks
    private OrderUseCase orderUseCase;

    private Order order;
    private List<OrderDish> dishes;
    private Restaurant restaurant;

    @BeforeEach
    void setUp() {
        restaurant = new Restaurant();
        restaurant.setId(1L);
        restaurant.setName("Test Restaurant");

        dishes = new ArrayList<>();
        OrderDish dish = new OrderDish();
        dish.setDishId(1L);
        dish.setQuantity(2);
        dishes.add(dish);

        order = new Order();
        order.setRestaurantId(1L);
        order.setDishes(dishes);
    }

    @Test
    void createOrder_ValidOrder_OrderCreated() {
        Long clientId = 1L;
        when(userPersistencePort.getCurrentUserId()).thenReturn(clientId);
        when(orderPersistencePort.findOrderByClientId(clientId)).thenReturn(false);
        when(restaurantPersistencePort.findById(1L)).thenReturn(Optional.of(restaurant));
        when(dishPersistencePort.getRestaurantIdByDishId(1L)).thenReturn(1L);

        orderUseCase.createOrder(order);

        assertEquals(clientId, order.getClientId());
        assertEquals(OrderUseCaseConstants.STATUS_PENDING, order.getStatus());
        assertEquals(LocalDate.now(), order.getDate());
        verify(orderPersistencePort).createOrder(order);
    }

    @Test
    void createOrder_ClientWithActiveOrder_ThrowsException() {
        Long clientId = 1L;
        when(userPersistencePort.getCurrentUserId()).thenReturn(clientId);
        when(orderPersistencePort.findOrderByClientId(clientId)).thenReturn(true);

        CustomValidationException exception = assertThrows(
                CustomValidationException.class,
                () -> orderUseCase.createOrder(order)
        );
        assertEquals(OrderUseCaseConstants.CLIENT_HAS_ACTIVE_ORDER, exception.getMessage());
        verify(orderPersistencePort, never()).createOrder(any());
    }

    @Test
    void createOrder_NullRestaurantId_ThrowsException() {
        Long clientId = 1L;
        when(userPersistencePort.getCurrentUserId()).thenReturn(clientId);
        when(orderPersistencePort.findOrderByClientId(clientId)).thenReturn(false);
        order.setRestaurantId(null);

        CustomValidationException exception = assertThrows(
                CustomValidationException.class,
                () -> orderUseCase.createOrder(order)
        );
        assertEquals(OrderUseCaseConstants.RESTAURANT_ID_INVALID, exception.getMessage());
        verify(orderPersistencePort, never()).createOrder(any());
    }

    @Test
    void createOrder_InvalidRestaurantId_ThrowsException() {
        Long clientId = 1L;
        when(userPersistencePort.getCurrentUserId()).thenReturn(clientId);
        when(orderPersistencePort.findOrderByClientId(clientId)).thenReturn(false);
        order.setRestaurantId(0L);

        CustomValidationException exception = assertThrows(
                CustomValidationException.class,
                () -> orderUseCase.createOrder(order)
        );
        assertEquals(OrderUseCaseConstants.RESTAURANT_ID_INVALID, exception.getMessage());
        verify(orderPersistencePort, never()).createOrder(any());
    }

    @Test
    void createOrder_RestaurantNotFound_ThrowsException() {
        Long clientId = 1L;
        when(userPersistencePort.getCurrentUserId()).thenReturn(clientId);
        when(orderPersistencePort.findOrderByClientId(clientId)).thenReturn(false);
        when(restaurantPersistencePort.findById(anyLong())).thenReturn(Optional.empty());

        CustomValidationException exception = assertThrows(
                CustomValidationException.class,
                () -> orderUseCase.createOrder(order)
        );
        assertEquals(OrderUseCaseConstants.RESTAURANT_NOT_FOUND, exception.getMessage());
        verify(orderPersistencePort, never()).createOrder(any());
    }

    @Test
    void createOrder_NullDishes_ThrowsException() {
        Long clientId = 1L;
        when(userPersistencePort.getCurrentUserId()).thenReturn(clientId);
        when(orderPersistencePort.findOrderByClientId(clientId)).thenReturn(false);
        when(restaurantPersistencePort.findById(anyLong())).thenReturn(Optional.of(restaurant));
        order.setDishes(null);

        CustomValidationException exception = assertThrows(
                CustomValidationException.class,
                () -> orderUseCase.createOrder(order)
        );
        assertEquals(OrderUseCaseConstants.ORDER_MUST_HAVE_DISHES, exception.getMessage());
        verify(orderPersistencePort, never()).createOrder(any());
    }

    @Test
    void createOrder_EmptyDishes_ThrowsException() {
        Long clientId = 1L;
        when(userPersistencePort.getCurrentUserId()).thenReturn(clientId);
        when(orderPersistencePort.findOrderByClientId(clientId)).thenReturn(false);
        when(restaurantPersistencePort.findById(anyLong())).thenReturn(Optional.of(restaurant));
        order.setDishes(new ArrayList<>());

        CustomValidationException exception = assertThrows(
                CustomValidationException.class,
                () -> orderUseCase.createOrder(order)
        );
        assertEquals(OrderUseCaseConstants.ORDER_MUST_HAVE_DISHES, exception.getMessage());
        verify(orderPersistencePort, never()).createOrder(any());
    }

    @Test
    void createOrder_DishWithNullId_ThrowsException() {
        Long clientId = 1L;
        when(userPersistencePort.getCurrentUserId()).thenReturn(clientId);
        when(orderPersistencePort.findOrderByClientId(clientId)).thenReturn(false);
        when(restaurantPersistencePort.findById(anyLong())).thenReturn(Optional.of(restaurant));

        OrderDish dish = new OrderDish();
        dish.setDishId(null);
        dish.setQuantity(2);
        dishes.clear();
        dishes.add(dish);

        CustomValidationException exception = assertThrows(
                CustomValidationException.class,
                () -> orderUseCase.createOrder(order)
        );
        assertEquals(OrderUseCaseConstants.DISH_ID_INVALID, exception.getMessage());
        verify(orderPersistencePort, never()).createOrder(any());
    }

    @Test
    void createOrder_DishWithInvalidId_ThrowsException() {
        Long clientId = 1L;
        when(userPersistencePort.getCurrentUserId()).thenReturn(clientId);
        when(orderPersistencePort.findOrderByClientId(clientId)).thenReturn(false);
        when(restaurantPersistencePort.findById(anyLong())).thenReturn(Optional.of(restaurant));

        OrderDish dish = new OrderDish();
        dish.setDishId(0L);
        dish.setQuantity(2);
        dishes.clear();
        dishes.add(dish);

        CustomValidationException exception = assertThrows(
                CustomValidationException.class,
                () -> orderUseCase.createOrder(order)
        );
        assertEquals(OrderUseCaseConstants.DISH_ID_INVALID, exception.getMessage());
        verify(orderPersistencePort, never()).createOrder(any());
    }

    @Test
    void createOrder_DishWithInvalidQuantity_ThrowsException() {
        Long clientId = 1L;
        when(userPersistencePort.getCurrentUserId()).thenReturn(clientId);
        when(orderPersistencePort.findOrderByClientId(clientId)).thenReturn(false);
        when(restaurantPersistencePort.findById(anyLong())).thenReturn(Optional.of(restaurant));

        OrderDish dish = new OrderDish();
        dish.setDishId(1L);
        dish.setQuantity(0);
        dishes.clear();
        dishes.add(dish);

        CustomValidationException exception = assertThrows(
                CustomValidationException.class,
                () -> orderUseCase.createOrder(order)
        );
        assertEquals(OrderUseCaseConstants.DISH_QUANTITY_INVALID, exception.getMessage());
        verify(orderPersistencePort, never()).createOrder(any());
    }

    @Test
    void createOrder_DishFromDifferentRestaurant_ThrowsException() {
        Long clientId = 1L;
        when(userPersistencePort.getCurrentUserId()).thenReturn(clientId);
        when(orderPersistencePort.findOrderByClientId(clientId)).thenReturn(false);
        when(restaurantPersistencePort.findById(anyLong())).thenReturn(Optional.of(restaurant));
        when(dishPersistencePort.getRestaurantIdByDishId(1L)).thenReturn(2L); // Different restaurant

        CustomValidationException exception = assertThrows(
                CustomValidationException.class,
                () -> orderUseCase.createOrder(order)
        );
        assertEquals(OrderUseCaseConstants.DISH_RESTAURANT_MISMATCH, exception.getMessage());
        verify(orderPersistencePort, never()).createOrder(any());
    }

    @Test
    void orderList_Success_ReturnsOrdersWithDishes() {
        Long employeeId = 1L;
        Long restaurantId = 1L;
        String status = "PENDING";
        Integer currentPage = 0;
        Integer limitForPage = 10;
        String orderDirection = "ASC";

        Order order1 = new Order();
        order1.setId(1L);

        List<Order> orders = new ArrayList<>();
        orders.add(order1);

        List<OrderDish> orderDishes = new ArrayList<>();
        OrderDish dish = new OrderDish();
        dish.setDishId(1L);
        dish.setQuantity(2);
        orderDishes.add(dish);

        Pagination<Order> pagination = new Pagination<>();
        pagination.setContent(orders);
        pagination.setCurrentPage(0);
        pagination.setTotalPages(1);
        pagination.setTotalElements(1L);


        when(userPersistencePort.getCurrentUserId()).thenReturn(employeeId);
        when(restaurantPersistencePort.findById(restaurantId)).thenReturn(Optional.of(restaurant));
        when(userPersistencePort.isEmployeeOfRestaurant(employeeId, restaurantId)).thenReturn(true);
        when(orderPersistencePort.listOrders(orderDirection, currentPage, limitForPage, status, restaurantId)).thenReturn(pagination);
        when(orderPersistencePort.findDishesByOrderId(1L)).thenReturn(orderDishes);

        Pagination<Order> result = orderUseCase.orderList(orderDirection, currentPage, limitForPage, status, restaurantId);

        assertEquals(1, result.getContent().size());
        assertEquals(1, result.getContent().get(0).getDishes().size());
        verify(orderPersistencePort).listOrders(orderDirection, currentPage, limitForPage, status, restaurantId);
        verify(orderPersistencePort).findDishesByOrderId(1L);
    }
    @Test
    void orderList_NullRestaurantId_ThrowsException() {
        Long employeeId = 1L;
        Long restaurantId = null;
        String status = "PENDING";
        Integer currentPage = 0;
        Integer limitForPage = 10;
        String orderDirection = "ASC";

        when(userPersistencePort.getCurrentUserId()).thenReturn(employeeId);

        CustomValidationException exception = assertThrows(
                CustomValidationException.class,
                () -> orderUseCase.orderList(orderDirection, currentPage, limitForPage, status, restaurantId)
        );
        assertEquals(OrderUseCaseConstants.RESTAURANT_ID_INVALID, exception.getMessage());

        verify(orderPersistencePort, never()).listOrders(any(), any(), any(), any(), any());
    }

    @Test
    void orderList_InvalidRestaurantId_ThrowsException() {
        Long employeeId = 1L;
        Long restaurantId = 0L;
        String status = "PENDING";
        Integer currentPage = 0;
        Integer limitForPage = 10;
        String orderDirection = "ASC";

        when(userPersistencePort.getCurrentUserId()).thenReturn(employeeId);

        CustomValidationException exception = assertThrows(
                CustomValidationException.class,
                () -> orderUseCase.orderList(orderDirection, currentPage, limitForPage, status, restaurantId)
        );
        assertEquals(OrderUseCaseConstants.RESTAURANT_ID_INVALID, exception.getMessage());
        verify(orderPersistencePort, never()).listOrders(any(), any(), any(), any(), any());
    }

    @Test
    void orderList_RestaurantNotFound_ThrowsException() {
        Long employeeId = 1L;
        Long restaurantId = 1L;
        String status = "PENDING";
        Integer currentPage = 0;
        Integer limitForPage = 10;
        String orderDirection = "ASC";

        when(userPersistencePort.getCurrentUserId()).thenReturn(employeeId);
        when(restaurantPersistencePort.findById(restaurantId)).thenReturn(Optional.empty());

        CustomValidationException exception = assertThrows(
                CustomValidationException.class,
                () -> orderUseCase.orderList(orderDirection, currentPage, limitForPage, status, restaurantId)
        );
        assertEquals(OrderUseCaseConstants.RESTAURANT_NOT_FOUND, exception.getMessage());
        verify(orderPersistencePort, never()).listOrders(any(), any(), any(), any(), any());
    }

    @Test
    void orderList_EmployeeNotWorkingAtRestaurant_ThrowsException() {
        Long employeeId = 1L;
        Long restaurantId = 1L;
        String status = "PENDING";
        Integer currentPage = 0;
        Integer limitForPage = 10;
        String orderDirection = "ASC";

        when(userPersistencePort.getCurrentUserId()).thenReturn(employeeId);
        when(restaurantPersistencePort.findById(restaurantId)).thenReturn(Optional.of(restaurant));
        when(userPersistencePort.isEmployeeOfRestaurant(employeeId, restaurantId)).thenReturn(false);

        CustomValidationException exception = assertThrows(
                CustomValidationException.class,
                () -> orderUseCase.orderList(orderDirection, currentPage, limitForPage, status, restaurantId)
        );
        assertEquals(OrderUseCaseConstants.EMPLOYEE_NOT_RESTAURANT_WORKER, exception.getMessage());
        verify(orderPersistencePort, never()).listOrders(any(), any(), any(), any(), any());
    }
}