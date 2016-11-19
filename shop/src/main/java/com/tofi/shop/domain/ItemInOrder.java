package com.tofi.shop.domain;

import java.io.Serializable;

public class ItemInOrder implements Serializable {
    private Integer id;
    private Item item;
    private Order order;
    private int amount;
    private int instantPrice;

    public ItemInOrder() {
    }

    public ItemInOrder(Integer id, Item item, Order order, int amount, int instantPrice) {
        this.id = id;
        this.item = item;
        this.order = order;
        this.amount = amount;
        this.instantPrice = instantPrice;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Item getItem() {
        return item;
    }

    public void setItem(Item item) {
        this.item = item;
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public int getInstantPrice() {
        return instantPrice;
    }

    public void setInstantPrice(int instantPrice) {
        this.instantPrice = instantPrice;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ItemInOrder that = (ItemInOrder) o;

        if (amount != that.amount) return false;
        if (instantPrice != that.instantPrice) return false;
        if (id != null ? !id.equals(that.id) : that.id != null) return false;
        if (item != null ? !item.equals(that.item) : that.item != null)
            return false;
        return order != null ? order.equals(that.order) : that.order == null;

    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (item != null ? item.hashCode() : 0);
        result = 31 * result + (order != null ? order.hashCode() : 0);
        result = 31 * result + amount;
        result = 31 * result + instantPrice;
        return result;
    }

    @Override
    public String toString() {
        return "ItemInOrder{" +
                "id=" + id +
                ", item=" + item +
                ", order=" + order +
                ", amount=" + amount +
                ", instantPrice=" + instantPrice +
                '}';
    }
}
