package com.tofi.shop.domain;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class Order {
    private Integer id;
    //private OrderStatus status;
    private List<OrderHistory> historyList;
    private User user;
    private String uniqueCode;
    private int instantDiscount;

    public Order() {
    }

    public Order(Integer id, List<OrderHistory> historyList, User user, String uniqueCode, int instantDiscount) {
        this.id = id;
        this.historyList = historyList;
        this.user = user;
        this.uniqueCode = uniqueCode;
        this.instantDiscount = instantDiscount;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public OrderStatus getStatus() {
        if (historyList.size() == 0) return null;
        historyList.sort((o1, o2) -> o1.getChangeDate().compareTo(o2.getChangeDate()));
        return historyList.get(historyList.size()-1).getStatus();
    }

    public void setStatus(OrderStatus status) {
        if (historyList == null) {
            historyList = new ArrayList<>();
        }
        historyList.add(new OrderHistory(0, this, status, Timestamp.valueOf(LocalDateTime.now())));
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
}
