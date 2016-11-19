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
    private OrderHistoryService orderHistoryService;

    @Inject
    public UserController(UserService userService, CartService cartService, OrderService orderService,
                          OrderStatusService orderStatusService, ItemInOrderService itemInOrderService,
                          OrderHistoryService orderHistoryService) {
        this.userService = userService;
        this.cartService = cartService;
        this.orderService = orderService;
        this.orderStatusService = orderStatusService;
        this.itemInOrderService = itemInOrderService;
        this.orderHistoryService = orderHistoryService;
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
        // TODO: payment stuff, interaction with bank

        LOG.debug("--- MAKING ORDER ---");
        Order order = new Order();
        order.setUser(userService.getAuthenticatedUser());
        order.setUniqueCode("testuniquecode");
        order.setInstantDiscount(0);
        Integer id = orderService.create(order);
        order = orderService.findById(id);

        OrderStatus STATUS_NEW = orderStatusService.findById(1);
        OrderHistory oh = new OrderHistory(null, order, STATUS_NEW, Timestamp.valueOf(LocalDateTime.now()));
        Integer ohId = orderHistoryService.create(oh);
        order.getHistoryList().add(orderHistoryService.findById(ohId));
        orderService.update(order);
        LOG.debug(order);

        List<ItemInCart> cart = cartService.getItemsInCart(userService.getAuthenticatedUser());
        for (ItemInCart itemInCart : cart) {
            ItemInOrder itemInOrder = new ItemInOrder();

            itemInOrder.setOrder(order);
            /*if (order.getHistoryList() == null) {
                order.setHistoryList(new ArrayList<>());
            }
            OrderHistory orderHistory = new OrderHistory();
            orderHistory.setOrder(order);
            orderHistory.setStatus(STATUS_NEW);
            orderHistory.setChangeDate(Timestamp.valueOf(LocalDateTime.now()));
            order.getHistoryList().add(orderHistory);*/
            itemInOrder.setItem(itemInCart.getItem());
            itemInOrder.setAmount(itemInCart.getAmount());
            itemInOrder.setInstantPrice(itemInCart.getItem().getPrice());
            itemInOrderService.create(itemInOrder);
        }
        cartService.clear(userService.getAuthenticatedUser());
        return "redirect:/user/orders";
    }
}
