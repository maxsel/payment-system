package com.tofi.shop.controller;

import com.tofi.shop.domain.ItemInCart;
import com.tofi.shop.service.CartService;
import com.tofi.shop.service.ItemService;
import com.tofi.shop.service.ServiceException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.config.annotation.InterceptorRegistration;

import javax.inject.Inject;
import java.util.List;

@Controller
@RequestMapping("user/cart")
public class CartController {
    private final CartService cartService;
    private final ItemService itemService;

    @Inject
    public CartController(CartService cartService, ItemService itemService) {
        this.cartService = cartService;
        this.itemService = itemService;
    }

    @ModelAttribute("cart_items")
    public List<ItemInCart> itemsInCart() {
        return this.cartService.getItemsInCart();
    }

    @RequestMapping("")
    public String showCart() {
        return "cart";
    }

    @PostMapping("/remove")
    @ResponseBody String delete(@RequestParam(value = "id", defaultValue = "-1") String itemId) throws ServiceException {
        if (itemId.equals("-1")) return "FAIL";
        Integer id = Integer.parseInt(itemId);
        this.cartService.decAmountOfItem(itemService.findById(id));
        return Integer.toString(this.cartService.getNumberOfItems());
    }

    @PostMapping("/add")
    @ResponseBody String add(@RequestParam(value = "id", defaultValue = "-1") String itemId) throws ServiceException {
        if (itemId == "-1") return "FAIL";
        Integer id = Integer.parseInt(itemId);
        this.cartService.incAmountOfItem(itemService.findById(id));
        return Integer.toString(this.cartService.getNumberOfItems());
    }
}
