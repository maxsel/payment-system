package com.tofi.shop.Models;

/**
 * Created by user on 12.10.2016.
 */
public class UserMoney {
    private int _amount;
    private String _currency;

    public UserMoney(int amount, String currency) {
        _amount = amount;
        _currency = currency;
    }

    public int getAmount() {
        return _amount;
    }

    public String getCurrency() {
        return _currency;
    }
}
