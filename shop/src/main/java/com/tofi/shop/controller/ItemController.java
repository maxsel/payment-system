package com.tofi.shop.controller;

import com.tofi.shop.domain.Item;
import com.tofi.shop.service.ItemCategoryService;
import com.tofi.shop.service.ItemService;
import com.tofi.shop.service.ItemStatusService;
import com.tofi.shop.service.ServiceException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.inject.Inject;
import java.io.IOException;

@Controller
@RequestMapping("/admin/items")
public class ItemController {
    private static final Logger LOG = LogManager.getLogger(ItemController.class);

    private ItemService itemService;
    private ItemCategoryService itemCategoryService;
    private ItemStatusService itemStatusService;

    @Inject
    public ItemController(ItemService itemService,
                          ItemCategoryService itemCategoryService,
                          ItemStatusService itemStatusService) {
        this.itemService = itemService;
        this.itemCategoryService = itemCategoryService;
        this.itemStatusService = itemStatusService;
    }

    @RequestMapping("/add")
    public String showAddItemPage(Model model) throws ServiceException {
        model.addAttribute("operation", "add");
        model.addAttribute("item", new Item());
        model.addAttribute("categoriesList", itemCategoryService.findAll());
        model.addAttribute("statusList", itemStatusService.findAll());
        return "edit-item";
    }

    @RequestMapping(value = "/add/save", method = RequestMethod.POST)
    public String saveNewlyCreatedItem(@ModelAttribute("item") Item item,
                                       @RequestParam("file") MultipartFile file,
                                       BindingResult bindingResult)
            throws ServiceException, IOException {
        LOG.debug("Item to create: " + item);

        LOG.debug("File:" + file.getName());
        LOG.debug("ContentType:" + file.getContentType());

        if (bindingResult.hasErrors()) {
            LOG.debug("There are binding errors:" + bindingResult.getAllErrors());
        }

        item.setImage(file.getBytes());
        item.setImageFilename(file.getOriginalFilename());
        item.setImageFormat(file.getContentType());
        int id = itemService.create(item);
        LOG.debug("Created itemId: " + id);
        return "redirect:/";
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
        model.addAttribute("categoriesList", itemCategoryService.findAll());
        model.addAttribute("statusList", itemStatusService.findAll());
        return "edit-item";
    }

    @RequestMapping(value = "/{id}/edit/save", method = RequestMethod.POST)
    public String saveItemAfterEditing(@PathVariable("id") long id,
                                       @ModelAttribute("item") Item item,
                                       @RequestParam("file") MultipartFile file,
                                       BindingResult bindingResult)
            throws ServiceException, IOException {
        LOG.debug("Item to update(id=" + id + "): " + item);

        LOG.debug("File:" + file.getName());
        LOG.debug("ContentType:" + file.getContentType());

        if (bindingResult.hasErrors()) {
            LOG.debug("There are binding errors:" + bindingResult.getAllErrors());
        }

        item.setImage(file.getBytes());
        item.setImageFilename(file.getOriginalFilename());
        item.setImageFormat(file.getContentType());
        itemService.update(item);
        return "redirect:/";
    }
}
