package com.tofi.shop.controller;

import com.tofi.shop.domain.Item;
import com.tofi.shop.domain.ItemCategory;
import com.tofi.shop.domain.User;
import com.tofi.shop.service.ItemService;
import com.tofi.shop.service.ServiceException;
import com.tofi.shop.service.UserService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.Assert;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.inject.Inject;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/admin")
public class AdminController {
    private static final Logger LOG = LogManager.getLogger(AdminController.class);

    private ItemService itemService;
    private UserService userService;

    @Inject
    AdminController(ItemService itemService, UserService userService) {
        Assert.notNull(itemService, "ItemService must be not null");
        Assert.notNull(userService, "UserService must be not null");
        this.itemService = itemService;
        this.userService = userService;
    }

    @RequestMapping("/manage-users")
    public String showUsers(Model model) throws ServiceException {
        model.addAttribute("user", new User());
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

    /*@RequestMapping("/manage-users/block-user/{id}")
    public String blockUser(@ModelAttribute("user") User user,
                            BindingResult bindingResult) throws ServiceException {
        LOG.debug("Blocking user: " + user);
        userService.update(user);
        return "redirect:/admin/manage-users";
    }*/

    @RequestMapping("/manage-users/block-user/{id}")
    public String blockUser(@PathVariable("id") int id) throws ServiceException {
        User user = userService.findById(id);
        LOG.debug("Blocking user: " + user);
        user.setBlocked(!user.isBlocked());
        userService.update(user);
        return "redirect:/admin/manage-users";
    }

    @RequestMapping("/manage-items")
    public String manageItems() {
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

    @RequestMapping("/manage-items/add")
    public String addItem(@ModelAttribute("newItem") Item item,
                          BindingResult bindingResult) throws ServiceException {
        itemService.create(item);
        return "admin-manage";
    }
}
