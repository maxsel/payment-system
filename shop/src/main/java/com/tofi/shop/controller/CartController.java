package com.tofi.shop.controller;

import com.tofi.shop.domain.Item;
import com.tofi.shop.domain.ItemInCart;
import com.tofi.shop.domain.User;
import com.tofi.shop.service.CartService;
import com.tofi.shop.service.ItemService;
import com.tofi.shop.service.ServiceException;
import com.tofi.shop.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.inject.Inject;
import java.util.List;

@Controller
@RequestMapping("user/cart")
public class CartController {
    private final CartService cartService;
    private final ItemService itemService;
    private UserService userService;

    @Inject
    public CartController(CartService cartService, ItemService itemService, UserService userService) {
        this.cartService = cartService;
        this.itemService = itemService;
        this.userService = userService;
    }

    @ModelAttribute("cart_items")
    public List<ItemInCart> itemsInCart() throws ServiceException {
        return cartService.getItemsInCart(userService.getAuthenticatedUser());
    }

    @RequestMapping("")
    public String showCart() {
        return "cart";
    }

    @PostMapping("/remove")
    @ResponseBody String delete(@RequestParam(value = "id", defaultValue = "-1") String itemId)
            throws ServiceException {
        if (itemId.equals("-1")) return "FAIL";
        int id = Integer.parseInt(itemId);
        Item item = itemService.findById(id);
        User user = userService.getAuthenticatedUser();
        cartService.decAmountOfItem(item, user);
        return Integer.toString(cartService.getAmountOfItem(item, user));
    }

    @PostMapping("/add")
    @ResponseBody String add(@RequestParam(value = "id", defaultValue = "-1") String itemId)
            throws ServiceException {
        if (itemId == "-1") return "FAIL";
        int id = Integer.parseInt(itemId);
        Item item = itemService.findById(id);
        User user = userService.getAuthenticatedUser();
        cartService.incAmountOfItem(item, user);
        return Integer.toString(cartService.getAmountOfItem(item, user));
    }
}
