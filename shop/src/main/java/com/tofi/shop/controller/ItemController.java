package com.tofi.shop.controller;

import com.tofi.shop.domain.Item;
import com.tofi.shop.service.CategoryService;
import com.tofi.shop.service.ItemService;
import com.tofi.shop.service.ServiceException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.inject.Inject;

@Controller
@RequestMapping("/admin/items")
public class ItemController {
    private static final Logger LOG = LogManager.getLogger(ItemController.class);

    private ItemService itemService;
    private CategoryService categoryService;

    @Inject
    public ItemController(ItemService itemService, CategoryService categoryService) {
        this.itemService = itemService;
        this.categoryService = categoryService;
    }

    @RequestMapping("/add")
    public String showAddItemPage(Model model) throws ServiceException {
        model.addAttribute("operation", "add");
        model.addAttribute("item", new Item());
        model.addAttribute("categoriesList", categoryService.findAll());
        return "edit-item";
    }

    @RequestMapping(value = "/add/save", method = RequestMethod.POST)
    public String saveNewlyCreatedItem(@ModelAttribute("item")
                                               Item item,
                                       BindingResult bindingResult)
            throws ServiceException {
        LOG.debug("Item to create: " + item);
        int id = itemService.create(item);
        LOG.debug("Created itemId: " + id);
        return "redirect:/items-list";
    }

    @RequestMapping("/{id}/edit")
    public String showEditItemPage(@PathVariable("id") int id, Model model)
            throws ServiceException {
        model.addAttribute("operation", "edit");
        Item item = itemService.findById(id);
        if (item == null) {
            return "redirect:/error";
        }
        model.addAttribute("item", item);
        model.addAttribute("categoriesList", categoryService.findAll());
        return "edit-item";
    }

    @RequestMapping(value = "/{id}/edit/save", method = RequestMethod.POST)
    public String saveItemAfterEditing(@PathVariable("id") long id,
                                       @ModelAttribute("item") Item item,
                                       BindingResult bindingResult)
            throws ServiceException {
        LOG.debug("ExtendedNews to update(id=" + id + "): " + item);
        if (bindingResult.hasErrors()) {
            LOG.debug("There are binding errors:" + bindingResult.getAllErrors());
        }
        itemService.update(item);
        return "redirect:/admin/item/" + item.getId() + "/view";
    }
}
