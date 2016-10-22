package com.tofi.shop.service.impl.bank.Models;

/**
 * Created by user on 12.10.2016.
 */
public class TransferResult {
    private boolean _result;

    public TransferResult(boolean result) {
        _result = result;
    }

    public boolean getResult() {
        return _result;
    }
}