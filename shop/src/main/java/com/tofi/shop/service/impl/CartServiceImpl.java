package com.tofi.shop.service.impl;

import com.tofi.shop.domain.Item;
import com.tofi.shop.domain.ItemInCart;
import com.tofi.shop.domain.User;
import com.tofi.shop.service.CartService;
import com.tofi.shop.service.ItemInCartService;
import com.tofi.shop.service.ServiceException;
import org.springframework.stereotype.Service;

import javax.inject.Inject;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CartServiceImpl implements CartService {
    private static final int DUMMY_ID = 0;
    private static final int INITIAL_AMOUNT = 1;
    private ItemInCartService itemInCartService;

    @Inject
    public CartServiceImpl(ItemInCartService itemInCartService) {
        this.itemInCartService = itemInCartService;
    }

    @Override
    public void addItem(Item item, User user) throws ServiceException {
        ItemInCart itemInCart = getItemInCartByItemAndUser(item, user);
        if (itemInCart == null) {
            addItemInCart(new ItemInCart(DUMMY_ID, item, user, INITIAL_AMOUNT));
        }
        else {
            itemInCart.setAmount(itemInCart.getAmount() + 1);
            itemInCartService.update(itemInCart);
        }
    }

    @Override
    public void removeItem(Item item, User user) throws ServiceException {
        removeItemInCart(getItemInCartByItemAndUser(item, user));
    }

    @Override
    public void incAmountOfItem(Item item, User user) throws ServiceException {
        ItemInCart itemInCart = getItemInCartByItemAndUser(item, user);
        assert itemInCart != null;
        changeAmountOfItem(itemInCart, itemInCart.getAmount()+1);
    }

    @Override
    public void decAmountOfItem(Item item, User user) throws ServiceException {
        ItemInCart itemInCart = getItemInCartByItemAndUser(item, user);
        assert itemInCart != null;
        Integer amount = itemInCart.getAmount();
        if (amount == 1) {
            removeItemInCart(itemInCart);
            return;
        }
        changeAmountOfItem(itemInCart, amount-1);
    }

    @Override
    public void changeItemAmount(Item item, User user, int number) throws ServiceException {
        getItemInCartByItemAndUser(item, user).setAmount(number);
    }

    @Override
    public int getAmountOfItem(Item item, User user) throws ServiceException {
        return getItemInCartByItemAndUser(item, user).getAmount();
    }

    @Override
    public int getTotalPrice() throws ServiceException {
        int totalPrice = 0;
        for (ItemInCart itemInCart : itemInCartService.findAll()) {
            totalPrice += itemInCart.getAmount() * itemInCart.getItem().getPrice();
        }
        return totalPrice;
    }

    @Override
    public int getNumberOfItems() throws ServiceException {
        return itemInCartService.findAll().size();
    }

    @Override
    public void clear(User user) throws ServiceException {
        for (ItemInCart itemInCart : itemInCartService.findAll()) {
            if (itemInCart.getUser().equals(user)) {
                itemInCartService.delete(itemInCart.getId());
            }
        }
    }

    public List<ItemInCart> getItemsInCart(User user) throws ServiceException {
        return itemInCartService.findAll()
                .stream()
                .filter(itemInCart ->
                        itemInCart.getUser().getId() == user.getId()).collect(Collectors.toList());
    }

    private void removeItemInCart(ItemInCart itemInCart) throws ServiceException {
        itemInCartService.delete(itemInCart.getId());
    }

    private void addItemInCart(ItemInCart itemInCart) throws ServiceException {
        itemInCartService.create(itemInCart);
    }

    private ItemInCart getItemInCartByItemAndUser(Item item, User user) throws ServiceException {
        return itemInCartService.findAll()
                .stream()
                .filter(itemInCart ->
                        itemInCart.getItem().getId().equals(item.getId()) &&
                                itemInCart.getUser().getId() == user.getId()).findFirst().orElse(null);
    }

    private void changeAmountOfItem(ItemInCart itemInCart, int amount) throws ServiceException {
        itemInCart.setAmount(amount);
        itemInCartService.update(itemInCart);
    }
}
