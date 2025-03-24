package com.pragma.powerup.domain.model;

public class OrderDish {
    private Long id;
    private Long dishId;
    private Integer quantity;

    public OrderDish() {
    }

    public OrderDish(Long id, Long dishId, Integer quantity) {
        this.id = id;
        this.dishId = dishId;
        this.quantity = quantity;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getDishId() {
        return dishId;
    }

    public void setDishId(Long dishId) {
        this.dishId = dishId;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }
}
