package com.tofi.shop.controller.editor;

import com.tofi.shop.domain.ItemCategory;
import com.tofi.shop.domain.ItemStatus;
import com.tofi.shop.service.ItemCategoryService;
import com.tofi.shop.service.ItemStatusService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.InitBinder;

@ControllerAdvice
public class CommonBindingInitializer {
    @Autowired
    private ItemCategoryService itemCategoryService;

    @Autowired
    private ItemStatusService itemStatusService;

    @InitBinder
    public void registerCustomEditors(WebDataBinder binder) {
        binder.registerCustomEditor(ItemCategory.class, "category", new ItemCategoryEditor(itemCategoryService));
        binder.registerCustomEditor(ItemStatus.class, "status", new ItemStatusEditor(itemStatusService));
    }
}