package com.tofi.shop.service.impl.bank.Models;

public class UserMoney {
    private int amount;

    private String currency;

    public UserMoney(int amount, String currency) {
        this.amount = amount;
        this.currency = currency;
    }

    public UserMoney(){}

    public int getAmount() {
        return amount;
    }

    public String getCurrency() {
        return currency;
    }

    public void setAmount(int _amount) {
        this.amount = _amount;
    }

    public void setCurrency(String _currency) {
        this.currency = _currency;
    }
}
