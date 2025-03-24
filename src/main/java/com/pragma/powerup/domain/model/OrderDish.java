package com.pragma.powerup.domain.model;

public class OrderDish {
    private Long id;
    private Long dishId;
    private Integer quantity;
    private String name;
    private Double price;

    public OrderDish() {
    }

    public OrderDish(Long id, Long dishId, Integer quantity, String name, Double price) {
        this.id = id;
        this.dishId = dishId;
        this.quantity = quantity;
        this.name = name;
        this.price = price;
    }

    // Original getters and setters
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

    // New getters and setters
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }
}