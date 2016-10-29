package com.tofi.shop.controller;

import com.tofi.shop.domain.Item;
import com.tofi.shop.domain.ItemInCart;
import com.tofi.shop.service.CartService;
import com.tofi.shop.service.ItemService;
import com.tofi.shop.service.ServiceException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.inject.Inject;
import java.util.List;

@Controller
@RequestMapping("admin/cart")
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

    @RequestMapping("/add")
    public void addItemToCart(@RequestParam(value = "id", defaultValue = "-1") String itemId ) throws ServiceException {
        long id = Long.parseLong(itemId);
        if (id == -1) return;
        Item item = this.itemService.findById(id);
        this.cartService.addItem(item);
    }
}
