package com.tofi.shop.service;

import com.tofi.shop.domain.Item;

public interface CartService {
    public void addItem(Item item);
    public void removeItem(Item item);
    public void changeItemAmount(Item item, int number);
    public int getTotalPrice();
    public int getNumberOfItems();
    public void clear();
}
