package com.tofi.shop.service.impl.bank.Models;

/**
 * Created by user on 12.10.2016.
 */
public class UserExists {

    private boolean _exists;

    public UserExists(boolean exists) {
        _exists = exists;
    }

    public UserExists(){}

    public boolean getExists() {
        return _exists;
    }

    public void setExists(boolean value) {
        _exists = value;
    }
}