package com.tofi.shop.service;

public interface BankService {
    String pay(String clientId, long amount);

    boolean userExists(String cardId);
}
