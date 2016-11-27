package com.tofi.shop.controller;

import com.tofi.shop.domain.Item;
import com.tofi.shop.domain.ItemInCart;
import com.tofi.shop.domain.User;
import com.tofi.shop.dto.ajax.ItemAction;
import com.tofi.shop.service.CartService;
import com.tofi.shop.service.ItemService;
import com.tofi.shop.service.ServiceException;
import com.tofi.shop.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
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
    public String showCart(Model model) throws ServiceException {
        long totalCost = cartService.getItemsInCart(userService.getAuthenticatedUser())
                .stream()
                .mapToLong(i -> i.getAmount()*i.getItem().getPrice())
                .sum();
        long discount = userService.getAuthenticatedUser().getDiscount();
        model.addAttribute("total_cost", totalCost);
        model.addAttribute("discount", discount);
        model.addAttribute("discount_cost", totalCost - totalCost*discount/100);
        model.addAttribute("user", userService.getAuthenticatedUser());
        return "cart";
    }

    @PostMapping("/remove")
    public @ResponseBody ItemAction delete(@RequestParam(value = "id", defaultValue = "-1") String itemId) throws ServiceException {
        int id = Integer.parseInt(itemId);
        Item item = itemService.findById(id);
        User user = userService.getAuthenticatedUser();
        cartService.decAmountOfItem(item, user);
        int totalPrice = cartService.getTotalPrice();
        return new ItemAction(cartService.getAmountOfItem(item, user), totalPrice, (int)(totalPrice * (1-user.getDeltaDiscount())), user.getDiscount());
    }

    @PostMapping("/add")
    public @ResponseBody ItemAction add(@RequestParam(value = "id", defaultValue = "-1") String itemId) throws ServiceException {
        int id = Integer.parseInt(itemId);
        Item item = itemService.findById(id);
        User user = userService.getAuthenticatedUser();
        cartService.incAmountOfItem(item, user);
        int totalPrice = cartService.getTotalPrice();
        return new ItemAction(cartService.getAmountOfItem(item, user), totalPrice, (int)(totalPrice * (1-user.getDeltaDiscount())), user.getDiscount());
    }
}
