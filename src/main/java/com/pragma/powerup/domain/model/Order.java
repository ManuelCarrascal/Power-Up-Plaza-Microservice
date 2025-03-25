package com.pragma.powerup.domain.model;

import java.time.LocalDate;
import java.util.List;

public class Order {
    private Long id;
    private Long clientId;
    private Long restaurantId;
    private LocalDate date;
    private String status;
    private List<OrderDish> dishes;
    private Long idEmployee;


    public Order() {
    }

    public Order(Long id, Long clientId, Long restaurantId, LocalDate date, String status, List<OrderDish> dishes, Long idEmployee) {
        this.id = id;
        this.clientId = clientId;
        this.restaurantId = restaurantId;
        this.date = date;
        this.status = status;
        this.dishes = dishes;
        this.idEmployee = idEmployee;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getClientId() {
        return clientId;
    }

    public void setClientId(Long clientId) {
        this.clientId = clientId;
    }

    public Long getRestaurantId() {
        return restaurantId;
    }

    public void setRestaurantId(Long restaurantId) {
        this.restaurantId = restaurantId;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public List<OrderDish> getDishes() {
        return dishes;
    }

    public void setDishes(List<OrderDish> dishes) {
        this.dishes = dishes;
    }

    public Long getIdEmployee() {
        return idEmployee;
    }

    public void setIdEmployee(Long idEmployee) {
        this.idEmployee = idEmployee;
    }
}
