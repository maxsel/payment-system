package com.tofi.shop.controller;

import com.tofi.shop.domain.Order;
import com.tofi.shop.domain.OrderHistory;
import com.tofi.shop.domain.OrderStatus;
import com.tofi.shop.domain.User;
import com.tofi.shop.service.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.inject.Inject;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/admin")
public class AdminController {
    private static final Logger LOG = LogManager.getLogger(AdminController.class);

    private UserService userService;
    private OrderService orderService;
    private OrderStatusService orderStatusService;
    private OrderHistoryService orderHistoryService;
    private OrderStatus STATUS_NEW;
    private OrderStatus STATUS_READY;
    private OrderStatus STATUS_REJECTED;
    private OrderStatus STATUS_ARCHIVED;

    @Inject
    public AdminController(UserService userService,
                           OrderService orderService,
                           OrderStatusService orderStatusService,
                           OrderHistoryService orderHistoryService)
            throws ServiceException {
        this.userService = userService;
        this.orderService = orderService;
        this.orderStatusService = orderStatusService;
        this.orderHistoryService = orderHistoryService;
        STATUS_NEW = orderStatusService.findById(1);
        STATUS_READY = orderStatusService.findById(2);
        STATUS_REJECTED = orderStatusService.findById(3);
        STATUS_ARCHIVED = orderStatusService.findById(4);
    }

    @ModelAttribute(name = "user")
    public User formBackingObjectUser() {
        return new User();
    }

    @RequestMapping("/manage-users")
    public String showUsers(Model model) throws ServiceException {
        model.addAttribute("users",
                userService
                        .findAll()
                        .stream()
                        .filter(u -> u.getRoles()
                                .stream()
                                .allMatch(r -> !r.getName().equals("ROLE_ADMIN")))
                        .collect(Collectors.toList()));
        return "users";
    }

    @RequestMapping("/manage-users/update-user")
    public String updateUser(@ModelAttribute("user") User user) throws ServiceException {
        LOG.debug("Updating user: " + user);
        User userFromDB = userService.findById(user.getId());
        user.setRoles(userFromDB.getRoles());
        user.setItemsInCart(userFromDB.getItemsInCart());
        user.setOrders(userFromDB.getOrders());
        userService.update(user);
        return "redirect:/admin/manage-users";
    }

    @RequestMapping("/manage-users/block-user/{id}")
    public String blockUser(@PathVariable("id") int id) throws ServiceException {
        User user = userService.findById(id);
        LOG.debug("Blocking user: " + user);
        user.setBlocked(!user.isBlocked());
        userService.update(user);
        return "redirect:/admin/manage-users";
    }

    @RequestMapping("/change-order-status/{id}/{status}")
    public String changeOrderStatus(@PathVariable("id") int id,
                                    @PathVariable("status") String status) throws ServiceException {

        Order order = orderService.findById(id);

        switch (status) {
            case "ready" :
                OrderHistory oh1 = new OrderHistory(null, order, STATUS_READY, Timestamp.valueOf(LocalDateTime.now()));
                Integer oh1Id = orderHistoryService.create(oh1);
                order.getHistoryList().add(orderHistoryService.findById(oh1Id));
                orderService.update(order);
                break;
            case "reject" :
                OrderHistory oh2 = new OrderHistory(null, order, STATUS_REJECTED, Timestamp.valueOf(LocalDateTime.now()));
                Integer oh2Id = orderHistoryService.create(oh2);
                order.getHistoryList().add(orderHistoryService.findById(oh2Id));
                orderService.update(order);
                break;
            case "archive" :
                OrderHistory oh3 = new OrderHistory(null, order, STATUS_ARCHIVED, Timestamp.valueOf(LocalDateTime.now()));
                Integer oh3Id = orderHistoryService.create(oh3);
                order.getHistoryList().add(orderHistoryService.findById(oh3Id));
                orderService.update(order);
                break;
        }
        return "redirect:/admin/orders";
    }

    @RequestMapping("/orders")
    public String showOrdersPage(Model model)
            throws ServiceException {
        model.addAttribute("orders", orderService.findAll());
        return "orders-list";
    }
}
