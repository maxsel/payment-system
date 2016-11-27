package com.tofi.shop.controller;

import com.tofi.shop.domain.User;
import com.tofi.shop.service.ServiceException;
import com.tofi.shop.service.UserService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.inject.Inject;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/admin")
public class AdminController {
    private static final Logger LOG = LogManager.getLogger(AdminController.class);

    private UserService userService;
    @Inject
    AdminController(UserService userService) {
        Assert.notNull(userService, "UserService must be not null");
        this.userService = userService;
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
}
