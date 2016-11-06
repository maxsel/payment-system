package com.tofi.shop.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("admin/manage-items")
public class ManageItemsController {
    @RequestMapping("")
    public String index(){
        return "admin-manage";
    }
}
