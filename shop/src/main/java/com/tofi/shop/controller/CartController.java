package com.tofi.shop.controller;

import com.tofi.shop.domain.ItemInCart;
import com.tofi.shop.service.CartService;
import com.tofi.shop.service.ItemService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.inject.Inject;
import java.util.List;

@Controller
@RequestMapping("user/cart")
public class CartController {
    private final CartService cartService;
    private final ItemService itemService;

    @Inject
    public CartController(CartService cartService, ItemService itemService){
        this.cartService = cartService;
        this.itemService = itemService;
    }

    @ModelAttribute("cart_items")
    public List<ItemInCart> itemsInCart(){
        return this.cartService.getItemsInCart();
    }

    @RequestMapping("")
    public String showCart(){
        return "cart";
    }
}
