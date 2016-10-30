package com.tofi.shop.controller;


import com.tofi.shop.domain.*;
import com.tofi.shop.service.ServiceException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

@Controller
@RequestMapping("/user")
public class UserController {
    private static final Logger LOG = LogManager.getLogger(UserController.class);

    public UserController() {

    }

    @ModelAttribute("user")
    public User populateUserDTO() throws ServiceException {
        return new User(1, "test_user", "test_passwd", "card_id", 0);
    }

    @RequestMapping("/profile")
    public String showProfilePage()
            throws ServiceException {
        return "profile";
    }

    @ModelAttribute("orders")
    public List<Order> populateOrdersList() throws ServiceException {
        ArrayList<Order> orders = new ArrayList<>();
        orders.add(new Order(1, new OrderStatus(1, "NEW")));
        orders.add(new Order(2, new OrderStatus(1, "NEW")));
        orders.add(new Order(3, new OrderStatus(1, "NEW")));
        return new LinkedList<>(orders);
    }

    @RequestMapping("/orders")
    public String showOrdersPage()
            throws ServiceException {
        return "orders-list";
    }
    
    @ModelAttribute("cart_items")
    public List<ItemInCart> populateCardItemsList() throws ServiceException {
        ArrayList<ItemInCart> itemInCarts = new ArrayList<>();
        itemInCarts.add(new ItemInCart(new Item(1, "titile","dedesc", 50,
                new ItemCategory(1, "Available"),
                new ItemStatus(1, "NEW")), 1));
        return new LinkedList<>(itemInCarts);
    }

    @RequestMapping("/cart")
    public String showCartPage() throws ServiceException {
        return "cart";
    }

    @RequestMapping("/purchase")
    public String showPurchasePage(@ModelAttribute("itemsInCart") ArrayList<ItemInCart> itemInCarts)
            throws ServiceException {
        itemInCarts.add(new ItemInCart(new Item(1, "titile","dedesc", 50,
                new ItemCategory(1, "Available"),
                new ItemStatus(1, "NEW")), 1));
        return "purchase";
    }

    @RequestMapping("/order")
    public String showOrderPage() throws ServiceException {
        return "redirect:/user/orders";
    }
}
