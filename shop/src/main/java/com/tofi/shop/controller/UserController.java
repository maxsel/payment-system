package com.tofi.shop.controller;


import com.tofi.shop.domain.*;
import com.tofi.shop.service.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.inject.Inject;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/user")
public class UserController {
    private static final Logger LOG = LogManager.getLogger(UserController.class);
    private UserService userService;
    private CartService cartService;
    private OrderService orderService;
    private OrderStatusService orderStatusService;
    private ItemInOrderService itemInOrderService;

    @Inject
    public UserController(UserService userService, CartService cartService, OrderService orderService,
                          OrderStatusService orderStatusService, ItemInOrderService itemInOrderService) {
        this.userService = userService;
        this.cartService = cartService;
        this.orderService = orderService;
        this.orderStatusService = orderStatusService;
        this.itemInOrderService = itemInOrderService;
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

    @RequestMapping("/orders")
    public String showOrdersPage(Model model)
            throws ServiceException {
        User user = userService.getAuthenticatedUser();
        model.addAttribute("orders",
                orderService.findAll().stream().filter(o -> o.getUser().getId() == user.getId()).collect(Collectors.toList()));
        return "orders-list";
    }

    @RequestMapping("/purchase")
    public String showPurchasePage(Model model)
            throws ServiceException {
        model.addAttribute("cart_items", cartService.getItemsInCart(userService.getAuthenticatedUser()));
        return "purchase";
    }

    @RequestMapping("/order")
    public String showOrderPage() throws ServiceException {
        List<ItemInCart> cart = cartService.getItemsInCart(userService.getAuthenticatedUser());
        // TODO: payment stuff, interaction with bank
        OrderStatus NEW = orderStatusService.findById(1);
        System.out.println(NEW);
        Order order = new Order();
        order.setStatus(NEW);
        order.setUser(userService.getAuthenticatedUser());
        order.setUniqueCode("testuniquecode");
        order.setInstantDiscount(0);
        Integer id = orderService.create(order);
        /*for (ItemInCart itemInCart : cart) {
            ItemInOrder itemInOrder = new ItemInOrder();
          }

            itemInOrder.setOrder(order);
            if (order.getHistoryList() == null) {
                order.setHistoryList(new ArrayList<>());
            }
            OrderHistory orderHistory = new OrderHistory();
            orderHistory.setOrder(order);
            orderHistory.setStatus(NEW);
            orderHistory.setChangeDate(Timestamp.valueOf(LocalDateTime.now()));
            order.getHistoryList().add(orderHistory);
            itemInOrder.setItem(itemInCart.getItem());
            itemInOrder.setAmount(itemInCart.getAmount());
            itemInOrder.setInstantPrice(itemInCart.getItem().getPrice());
            itemInOrderService.create(itemInOrder);
        }*/
        return "redirect:/user/orders";
    }
}
