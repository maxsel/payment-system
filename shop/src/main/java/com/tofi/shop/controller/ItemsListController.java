package com.tofi.shop.controller;

import com.tofi.shop.domain.Item;
import com.tofi.shop.domain.ItemCategory;
import com.tofi.shop.domain.User;
import com.tofi.shop.service.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.Assert;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.inject.Inject;
import java.util.LinkedList;
import java.util.List;

@Controller
@RequestMapping("/items-list")
public class ItemsListController {
    private static final Logger LOG = LogManager.getLogger(ItemsListController.class);

    private final ItemService itemService;
    private final CategoryService categoryService;
    private final CartService cartService;
    private final UserService userService;

    @Inject
    public ItemsListController(ItemService itemService, CategoryService categoryService,
                               CartService cartService, UserService userService) {
        Assert.notNull(itemService, "ItemService must be not null!");
        Assert.notNull(categoryService, "CategoryService must be not null!");
        Assert.notNull(cartService, "CartServicec must be not null");
        this.cartService = cartService;
        this.itemService = itemService;
        this.categoryService = categoryService;
        this.userService = userService;
    }

    @ModelAttribute("items")
    public List<Item> populateItemsList() throws ServiceException {
        return new LinkedList<>(itemService.findAll());
    }

    @ModelAttribute("categories")
    public List<ItemCategory> populateCategoriesList() throws ServiceException {
        return new LinkedList<>(categoryService.findAll());
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
    public @ResponseBody String addItemToCart(@RequestParam(value = "id", defaultValue = "-1") String itemId)
            throws ServiceException {
        Integer id = Integer.parseInt(itemId);
        if (id == -1) return "FAIL";
        Item item = itemService.findById(id);
        User user = userService.getAuthenticatedUser();
        cartService.addItem(item, user);
        return Integer.toString(cartService.getNumberOfItems());
    }
}
