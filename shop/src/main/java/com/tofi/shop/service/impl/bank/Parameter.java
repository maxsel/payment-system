package com.tofi.shop.service.impl.bank;

public class Parameter {
    private String _name;
    private String _value;

    public Parameter(String name, String value){
        this._name = name;
        this._value = value;
    }

    public String getName() {
        return _name;
    }

    public String getValue() {
        return _value;
    }
}