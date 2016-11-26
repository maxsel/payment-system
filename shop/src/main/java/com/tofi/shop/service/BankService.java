package com.tofi.shop.service;

import java.io.IOException;

public interface BankService {
    boolean pay(String cardNumber, String cvv, long amount) throws IOException;

    boolean userExists(String cardNumber, String cvv) throws IOException;

    boolean checkCurrency(String cardNumber) throws IOException;

    boolean checkAmountOfMoney(String cardNumber,  int amountOfMoney) throws  IOException;
}
