package com.tofi.shop.controller.editor;

import com.tofi.shop.domain.ItemCategory;
import com.tofi.shop.service.ItemCategoryService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.beans.PropertyEditorSupport;

public class ItemCategoryEditor extends PropertyEditorSupport {

    private ItemCategoryService itemCategoryService;

    private static final Logger LOG = LogManager.getLogger(ItemCategoryEditor.class);

    public ItemCategoryEditor() {
    }

    public ItemCategoryEditor(ItemCategoryService itemCategoryService) {
        this.itemCategoryService = itemCategoryService;
    }

    // Converts a String to an ItemCategory (when submitting form)
    @Override
    public void setAsText(String text) {
        /*ItemCategory itemCategory = new ItemCategory();
        itemCategory.setId(Integer.valueOf(text));*/
        ItemCategory itemCategory = null;
        try {
            if (itemCategoryService == null) {
                LOG.error("ItemCategoryService IS NULL!");
            }
            itemCategory = itemCategoryService.findById(Integer.valueOf(text));
        } catch (Exception e) {
            LOG.error(e);
        }
        this.setValue(itemCategory);
    }

    // Converts an Author to a ItemCategory (when displaying form)
    @Override
    public String getAsText() {
        ItemCategory itemCategory = (ItemCategory) this.getValue();
        return itemCategory == null ? "" : itemCategory.toString();
    }
}