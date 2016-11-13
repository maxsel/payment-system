package com.tofi.shop.service;

import com.tofi.shop.domain.Item;
import com.tofi.shop.domain.ItemInCart;
import com.tofi.shop.domain.User;

import java.util.List;

public interface CartService {
    void addItem(Item item, User user) throws ServiceException;

    void removeItem(Item item, User user) throws ServiceException;

    void incAmountOfItem(Item item, User user) throws ServiceException;

    void decAmountOfItem(Item item, User user) throws ServiceException;

    void changeItemAmount(Item item, User user, int number) throws ServiceException;

    int getAmountOfItem(Item item, User user) throws ServiceException;

    int getTotalPrice() throws ServiceException;
    int getNumberOfItems() throws ServiceException;
    List<ItemInCart> getItemsInCart() throws ServiceException;
    void clear() throws ServiceException;
}
