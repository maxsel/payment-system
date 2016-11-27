package com.tofi.shop.controller;

import com.tofi.shop.domain.*;
import com.tofi.shop.dto.ajax.ItemAction;
import com.tofi.shop.service.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.inject.Inject;
import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/items-list")
public class ItemsListController {
    private static final Logger LOG = LogManager.getLogger(ItemsListController.class);

    private final ItemService itemService;
    private final ItemCategoryService itemCategoryService;
    private final CartService cartService;
    private final UserService userService;
    private final UserRoleService userRoleService;
    private final BankService bankService;
    private final ItemStatusService itemStatusService;
    private final UserRole ROLE_ADMIN;
    private final UserRole ROLE_USER;
    private final ItemStatus AVAILABLE;
    private final ItemStatus NOT_AVAILABLE;


    @Inject
    public ItemsListController(ItemService itemService, ItemCategoryService itemCategoryService,
                               CartService cartService, UserService userService, BankService bankService,
                               UserRoleService userRoleService, ItemStatusService itemStatusService) throws ServiceException {
        this.cartService = cartService;
        this.itemService = itemService;
        this.itemCategoryService = itemCategoryService;
        this.userService = userService;
        this.bankService = bankService;
        this.userRoleService = userRoleService;
        this.itemStatusService = itemStatusService;
        ROLE_ADMIN = userRoleService.findById(1);
        ROLE_USER = userRoleService.findById(2);
        AVAILABLE = itemStatusService.findById(1);
        NOT_AVAILABLE = itemStatusService.findById(2);
    }

    @ModelAttribute("items")
    public List<Item> populateItemsList() throws ServiceException {

        if (userService.getAuthenticatedUser() != null &&
                userService.getAuthenticatedUser().getRoles().contains(ROLE_ADMIN)) {
            return itemService.findAll();
        } else {
            return itemService
                    .findAll()
                    .stream()
                    .filter(i -> i.getStatus().equals(AVAILABLE))
                    .collect(Collectors.toList());
        }
    }

    @ModelAttribute("categories")
    public List<ItemCategory> populateCategoriesList() throws ServiceException {
        return itemCategoryService.findAll();
    }

    @ModelAttribute("item")
    public Item populateItemDTO() throws ServiceException {
        return new Item();
    }

    @RequestMapping("")
    public String showItemsPage(Model model, @ModelAttribute("items") List<Item> items)
            throws ServiceException {
        items = itemService.findAll();
        return "items-list";
    }

    @PostMapping("/add")
    public @ResponseBody ItemAction addItemToCart(@RequestParam(value = "id", defaultValue = "-1") String itemId)
            throws ServiceException {
        Integer id = Integer.parseInt(itemId);
        Item item = itemService.findById(id);
        User user = userService.getAuthenticatedUser();
        cartService.addItem(item, user);
        int totalPrice = cartService.getTotalPrice();
        return new ItemAction(cartService.getAmountOfItem(item, user), totalPrice, (int)(totalPrice * (1-user.getDeltaDiscount())), user.getDiscount());
    }
}