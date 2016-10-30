package com.tofi.shop.service.impl;

import com.tofi.shop.domain.Item;
import com.tofi.shop.domain.ItemInCart;
import com.tofi.shop.service.CartService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CartServiceImpl implements CartService {
    private List<ItemInCart> _itemsInCart = new ArrayList<>();

    @Override
    public void addItem(Item item) {
        ItemInCart itemInCart = getItemInCartByItem(item);
        if (itemInCart == null) {
            addItemInCart(new ItemInCart(1, item, 1));
        }
        else {
            itemInCart.setAmount(itemInCart.getAmount() + 1);
        }
    }

    @Override
    public void removeItem(Item item) {
        removeItemInCart(getItemInCartByItem(item));
    }

    @Override
    public void changeItemAmount(Item item, int number) {
        getItemInCartByItem(item).setAmount(number);
    }

    @Override
    public int getTotalPrice() {
        int totalPrice = 0;
        for (ItemInCart itemInCart : _itemsInCart) {
            totalPrice += itemInCart.getAmount() * itemInCart.getItem().getPrice();
        }
        return totalPrice;
    }

    @Override
    public int getNumberOfItems() {
        return _itemsInCart.size();
    }

    @Override
    public void clear() {
        _itemsInCart.clear();
    }

    public List<ItemInCart> getItemsInCart(){
        return this._itemsInCart;
    }

    private void removeItemInCart(ItemInCart itemInCart){
        _itemsInCart.remove(itemInCart);
    }

    private void addItemInCart(ItemInCart itemInCart){
        _itemsInCart.add(itemInCart);
    }

    private ItemInCart getItemInCartByItem(Item item){
        Optional<ItemInCart> itemInCart =
                _itemsInCart.stream().filter(iic -> iic.getItem().equals(item)).findFirst();
        if (itemInCart.isPresent()) return itemInCart.get();
        return null;
    }
}
