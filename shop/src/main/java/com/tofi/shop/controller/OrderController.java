package com.tofi.shop.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by user on 30.10.2016.
 */
@Controller
@RequestMapping("order")
public class OrderController {
    @RequestMapping("")
    public String showCart(){
        return "order";
    }
}
