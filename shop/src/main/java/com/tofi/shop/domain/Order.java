package com.tofi.shop.domain;

public class Order {
    private int id;
    private OrderStatus status;

    public Order() {
    }

    public Order(int id, OrderStatus status) {
        this.id = id;
        this.status = status;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public OrderStatus getStatus() {
        return status;
    }

    public void setStatus(OrderStatus status) {
        this.status = status;
    }
}
