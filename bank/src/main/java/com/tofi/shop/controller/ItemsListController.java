package com.tofi.shop.controller;

import com.tofi.shop.domain.Item;
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
@RequestMapping("/admin/news-list")
public class ItemsListController {
    private static final Logger LOG = LogManager.getLogger(ItemsListController.class);

    private final ItemService itemService;

    @Inject
    public ItemsListController(ItemService itemService) {
        Assert.notNull(itemService, "ItemService must be not null!");
        this.itemService = itemService;
    }

    @ModelAttribute("items")
    public List<Item> populateItemsList() throws ServiceException {
        return new LinkedList<>(itemService.findAll());
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

    @RequestMapping(value = "/update")
    public String update(@ModelAttribute("item") Item item,
                         BindingResult bindingResult){
        try {
            LOG.debug("Item to update: " + item);
            if (bindingResult.hasErrors()) {
                LOG.debug("There are binding errors:" + bindingResult.getAllErrors());
                return "edit-items";
            }
            itemService.update(item);
        } catch (ServiceException e) {
            LOG.error(e);
            return "error";
        }
        return "redirect:/admin/items/edit-items";
    }
}
