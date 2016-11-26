package com.tofi.shop.controller;

import com.tofi.shop.domain.Item;
import com.tofi.shop.domain.ItemCategory;
import com.tofi.shop.service.ItemService;
import com.tofi.shop.service.ServiceException;
import org.springframework.stereotype.Controller;
import org.springframework.util.Assert;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.inject.Inject;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("admin/manage-items")
public class ManageItemsController {
    private ItemService itemService;

    @Inject
    ManageItemsController(ItemService itemService){
        Assert.notNull(itemService, "ItemService must be not null");
        this.itemService = itemService;
    }

    @RequestMapping("")
    public String index(){
        return "admin-manage";
    }

    @ModelAttribute("newItem")
    public Item populateNewItemDTO(){
        return new Item();
    }

    @ModelAttribute("categories")
    public List<ItemCategory> populateCategoriesDTO(){
        return new ArrayList<ItemCategory>(){{
            add(new ItemCategory(1, "category 1"));
            add(new ItemCategory(1, "category 2"));
            add(new ItemCategory(1, "category 3"));
            add(new ItemCategory(1, "category 4"));
        }};
    }

    @RequestMapping("/add")
    public String addItem(@ModelAttribute("newItem") Item item,
                          BindingResult bindingResult) throws ServiceException {
        itemService.create(item);
        return "admin-manage";
    }
}
