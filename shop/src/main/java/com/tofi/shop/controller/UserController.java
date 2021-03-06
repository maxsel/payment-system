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
import org.springframework.web.bind.annotation.RequestParam;

import javax.inject.Inject;
import java.io.IOException;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;
import org.springframework.web.bind.annotation.*;

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
    private BankService bankService;
    private MailService mailService;
    private final DiscountService discountService;

    @Inject
    public UserController(UserService userService, CartService cartService, OrderService orderService,
                          OrderStatusService orderStatusService, ItemInOrderService itemInOrderService,
                          OrderHistoryService orderHistoryService, BankService bankService,
                          MailService mailService, DiscountService discountService) {
        this.userService = userService;
        this.cartService = cartService;
        this.orderService = orderService;
        this.orderStatusService = orderStatusService;
        this.itemInOrderService = itemInOrderService;
        this.orderHistoryService = orderHistoryService;
        this.bankService = bankService;
        this.mailService = mailService;
        this.discountService = discountService;
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
                orderService
                        .findAll()
                        .stream()
                        .filter(o -> o.getUser().getId() == user.getId())
                        .collect(Collectors.toList()));
        return "orders-list";
    }

    @RequestMapping("/purchase")
    public String showPurchasePage(Model model)
            throws ServiceException {
        model.addAttribute("cart_items", cartService.getItemsInCart(userService.getAuthenticatedUser()));
        return "purchase";
    }

    @RequestMapping("/checkOrder")
    public String checkOrder(@RequestParam(name = "cvv")String cvv,
                             @RequestParam(name = "Month") String month,
                             @RequestParam(name = "Year") String year,
                             Model model) throws ServiceException, IOException {
        model.addAttribute("cvv", cvv);
        LOG.debug("CVV - check order");
        LOG.debug(cvv);
        LOG.debug(month);
        LOG.debug(2000+Integer.parseInt(year));
        LOG.debug(LocalDate.now().getYear());
        LOG.debug(LocalDate.now().getMonth());
        LOG.debug(LocalDate.now().getMonth());
        int yearN = 2000+Integer.parseInt(year);
        int monthN = Integer.parseInt(month)-1;
        if (LocalDate.now().getYear() > yearN || (LocalDate.now().getYear()==yearN && LocalDate.now().getMonthValue() > monthN)){
            model.addAttribute("error", "your cart is too old");
            return showPurchasePage(model);
        }
        User user = userService.getAuthenticatedUser();
        if (bankService.checkCurrency(user.getCardId())) {
            return showOrderPage(cvv, model);
        }
        return "currency-confirm";
    }

    @PostMapping("/sendMail")
    public void sendMail(@RequestParam("mail") String mail) throws ServiceException {
        User user = userService.getAuthenticatedUser();
        String code = user.getOrders().get(user.getOrders().size()-1).getUniqueCode();
        mailService.sendCode(mail, code);
    }

    @RequestMapping("/order")
    public String showOrderPage(@RequestParam("cvv") String cvv, Model model) throws ServiceException, IOException {
        // TODO: payment stuff, interaction with bank

        LOG.debug(cvv);
        User user = userService.getAuthenticatedUser();
        LOG.debug("--- MAKING ORDER ---");

        // not enough money
        if (!bankService.checkAmountOfMoney(user.getCardId(), cartService.getTotalPrice()))
            return "error-money";
        // error transfer
        if (!bankService.pay(user.getCardId(), cvv, cartService.getTotalPrice()))
            return "error-transfer";

        long totalCost = cartService.getItemsInCart(userService.getAuthenticatedUser())
                .stream()
                .mapToLong(i -> i.getAmount()*i.getItem().getPrice())
                .sum();

        Order order = new Order();
        order.setUser(userService.getAuthenticatedUser());
        order.setUniqueCode(UUID.randomUUID().toString().substring(0, 8));
        int discount = discountService.getDiscount(user, totalCost);
        order.setInstantDiscount(discount);
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
            itemInOrder.setItem(itemInCart.getItem());
            itemInOrder.setAmount(itemInCart.getAmount());
            LOG.debug(itemInCart.getItem().getPrice()*itemInCart.getAmount()*(1-discount/100f));
            itemInOrder.setInstantPrice((int)(itemInCart.getItem().getPrice()*itemInCart.getAmount()*(1-discount/100f)));
            itemInOrderService.create(itemInOrder);
        }
        cartService.clear(userService.getAuthenticatedUser());

        model.addAttribute("orderCode", order.getUniqueCode());
        return "order-info";
    }
}
