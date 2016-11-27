package com.tofi.shop.controller;

import com.tofi.shop.domain.ItemCategory;
import com.tofi.shop.service.impl.ItemCategoryServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.InitBinder;

@ControllerAdvice
public class CommonBindingInitializer {
    @Autowired
    private ItemCategoryServiceImpl itemCategoryService;

    @InitBinder
    public void registerCustomEditors(WebDataBinder binder) {
        binder.registerCustomEditor(ItemCategory.class, "category", new ItemCategoryEditor(itemCategoryService));
    }
}