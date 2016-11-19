package com.tofi.shop.domain;

import java.sql.Timestamp;

public class OrderHistory {
    private Integer id;
    private Order order;
    private OrderStatus status;
    private Timestamp changeDate;

    public OrderHistory() {
    }

    public OrderHistory(Integer id, Order order, OrderStatus status, Timestamp changeDate) {
        this.id = id;
        this.order = order;
        this.status = status;
        this.changeDate = changeDate;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    public OrderStatus getStatus() {
        return status;
    }

    public void setStatus(OrderStatus status) {
        this.status = status;
    }

    public Timestamp getChangeDate() {
        return changeDate;
    }

    public void setChangeDate(Timestamp changeDate) {
        this.changeDate = changeDate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        OrderHistory that = (OrderHistory) o;

        if (id != null ? !id.equals(that.id) : that.id != null) return false;
        if (order != null ? !order.equals(that.order) : that.order != null)
            return false;
        if (status != null ? !status.equals(that.status) : that.status != null)
            return false;
        return changeDate != null ? changeDate.equals(that.changeDate) : that.changeDate == null;

    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (order != null ? order.hashCode() : 0);
        result = 31 * result + (status != null ? status.hashCode() : 0);
        result = 31 * result + (changeDate != null ? changeDate.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "OrderHistory{" +
                "id=" + id +
                ", order=" + order +
                ", status=" + status +
                ", changeDate=" + changeDate +
                '}';
    }
}
