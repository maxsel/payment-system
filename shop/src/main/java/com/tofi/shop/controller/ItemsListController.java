package com.tofi.shop.controller;

import com.tofi.shop.domain.Item;
import com.tofi.shop.domain.ItemCategory;
import com.tofi.shop.service.CategoryService;
import com.tofi.shop.service.ItemService;
import com.tofi.shop.service.ServiceException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.Assert;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.inject.Inject;
import java.util.LinkedList;
import java.util.List;

@Controller
@RequestMapping("/items-list")
public class ItemsListController {
    private static final Logger LOG = LogManager.getLogger(ItemsListController.class);

    private final ItemService itemService;
    private final CategoryService categoryService;

    @Inject
    public ItemsListController(ItemService itemService, CategoryService categoryService) {
        Assert.notNull(itemService, "ItemService must be not null!");
        Assert.notNull(categoryService, "CategoryService must be not null!");
        this.itemService = itemService;
        this.categoryService = categoryService;
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
}
