package com.tofi.shop.service;

import com.tofi.shop.domain.Item;
import com.tofi.shop.domain.ItemInCart;

import java.util.List;

public interface CartService {
    void addItem(Item item) throws ServiceException;
    void removeItem(Item item);
    void incAmountOfItem(Item item);
    void decAmountOfItem(Item item);
    void changeItemAmount(Item item, int number);
    int getAmountOfItem(Item item);
    int getTotalPrice();
    int getNumberOfItems();
    List<ItemInCart> getItemsInCart();
    void clear();
}
