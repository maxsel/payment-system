package com.tofi.shop.controller.editor;

import com.tofi.shop.domain.ItemStatus;
import com.tofi.shop.service.ItemStatusService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.beans.PropertyEditorSupport;

public class ItemStatusEditor extends PropertyEditorSupport {

    private ItemStatusService itemStatusService;

    private static final Logger LOG = LogManager.getLogger(ItemStatusEditor.class);

    public ItemStatusEditor() {
    }

    public ItemStatusEditor(ItemStatusService itemStatusService) {
        this.itemStatusService = itemStatusService;
    }

    // Converts a String to an ItemCategory (when submitting form)
    @Override
    public void setAsText(String text) {
        /*ItemCategory itemCategory = new ItemCategory();
        itemCategory.setId(Integer.valueOf(text));*/
        ItemStatus itemStatus = null;
        try {
            if (itemStatusService == null) {
                LOG.error("ItemCategoryService IS NULL!");
            }
            itemStatus = itemStatusService.findById(Integer.valueOf(text));
        } catch (Exception e) {
            LOG.error(e);
        }
        this.setValue(itemStatus);
    }

    // Converts an Author to a ItemCategory (when displaying form)
    @Override
    public String getAsText() {
        ItemStatus itemStatus = (ItemStatus) this.getValue();
        return itemStatus == null ? "" : itemStatus.toString();
    }
}