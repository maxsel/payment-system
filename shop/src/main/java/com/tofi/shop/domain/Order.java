package com.tofi.shop.domain;

import java.util.List;

public class Order {
    private Integer id;
    private List<OrderHistory> historyList;
    private User user;
    private String uniqueCode;
    private int instantDiscount;
    private List<ItemInOrder> items;

    public Order() {
    }

    public Order(Integer id, List<OrderHistory> historyList, User user, String uniqueCode, int instantDiscount) {
        this.id = id;
        this.historyList = historyList;
        this.user = user;
        this.uniqueCode = uniqueCode;
        this.instantDiscount = instantDiscount;
    }

    public OrderStatus getStatus() {
        if (historyList == null || historyList.size() == 0) return null;
        historyList.sort((o1, o2) -> o1.getChangeDate().compareTo(o2.getChangeDate()));
        return historyList.get(historyList.size()-1).getStatus();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
    
    public List<OrderHistory> getHistoryList() {
        return historyList;
    }

    public void setHistoryList(List<OrderHistory> historyList) {
        this.historyList = historyList;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getUniqueCode() {
        return uniqueCode;
    }

    public void setUniqueCode(String uniqueCode) {
        this.uniqueCode = uniqueCode;
    }

    public int getInstantDiscount() {
        return instantDiscount;
    }

    public void setInstantDiscount(int instantDiscount) {
        this.instantDiscount = instantDiscount;
    }

    public List<ItemInOrder> getItems() {
        return items;
    }

    public void setItems(List<ItemInOrder> items) {
        this.items = items;
    }

    public long getTotalCost() {
        if (items == null) return 0;
        long totalCost = items.stream().mapToLong(i -> i.getAmount()*i.getInstantPrice()).sum();
        return (int)(totalCost*(1-instantDiscount/100.0));
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Order order = (Order) o;

        if (instantDiscount != order.instantDiscount) return false;
        if (id != null ? !id.equals(order.id) : order.id != null) return false;
        if (historyList != null ? !historyList.equals(order.historyList) : order.historyList != null)
            return false;
        if (user != null ? !user.equals(order.user) : order.user != null)
            return false;
        return uniqueCode != null ? uniqueCode.equals(order.uniqueCode) : order.uniqueCode == null;

    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (historyList != null ? historyList.hashCode() : 0);
        result = 31 * result + (user != null ? user.hashCode() : 0);
        result = 31 * result + (uniqueCode != null ? uniqueCode.hashCode() : 0);
        result = 31 * result + instantDiscount;
        return result;
    }

    @Override
    public String toString() {
        return "Order{" +
                "id=" + id +
                ", status=" + getStatus() +
                ", user=" + user +
                ", uniqueCode='" + uniqueCode + '\'' +
                ", instantDiscount=" + instantDiscount +
                ", totalCost=" + getTotalCost() +
                '}';
    }
}
