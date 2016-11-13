package com.tofi.shop.controller;


import com.tofi.shop.domain.*;
import com.tofi.shop.service.CartService;
import com.tofi.shop.service.ServiceException;
import com.tofi.shop.service.UserService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.inject.Inject;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

@Controller
@RequestMapping("/user")
public class UserController {
    private static final Logger LOG = LogManager.getLogger(UserController.class);
    private UserService userService;
    private CartService cartService;


    @Inject
    public UserController(UserService userService, CartService cartService) {
        this.userService = userService;
        this.cartService = cartService;
    }

    @ModelAttribute("user")
    public User populateUserDTO() throws ServiceException {
        return new User();
    }

    @RequestMapping("/profile")
    public String showProfilePage(Model model)
            throws ServiceException {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String login = authentication.getName();
        User user = userService.findByLogin(login);
        model.addAttribute("current_user", user);
        return "profile";
    }

    @ModelAttribute("orders")
    public List<Order> populateOrdersList() throws ServiceException {
        ArrayList<Order> orders = new ArrayList<>();
        return new LinkedList<>(orders);
    }

    @RequestMapping("/orders")
    public String showOrdersPage()
            throws ServiceException {
        return "orders-list";
    }
    
//    @ModelAttribute("cart_items")
//    public List<ItemInCart> populateCardItemsList() throws ServiceException {
//        return new LinkedList<>();
//    }

    @RequestMapping("/purchase")
    public String showPurchasePage(Model model)
            throws ServiceException {
        model.addAttribute("cart_items", cartService.getItemsInCart(userService.getAuthenticatedUser()));
        return "purchase";
    }

    @RequestMapping("/order")
    public String showOrderPage() throws ServiceException {
        return "redirect:/user/orders";
    }
}
