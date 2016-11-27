package com.tofi.shop.dto.ajax;

public class ItemAction {
    private int itemsCount;
    private int totalPrice;
    private int totalPriceWithDiscount;
    private int discount;

    public ItemAction(int itemsCount, int totalPrice, int totalPriceWithDiscount, int discount){
        this.itemsCount = itemsCount;
        this.totalPrice = totalPrice;
        this.totalPriceWithDiscount = totalPriceWithDiscount;
        this.discount = discount;
    }

    public int getItemsCount(){
        return this.itemsCount;
    }

    public int getTotalPrice(){
        return this.totalPrice;
    }

    public int getTotalPriceWithDiscount(){
        return this.totalPriceWithDiscount;
    }

    public int getDiscount(){
        return this.discount;
    }
}
