package com.tofi.shop.domain;

public class ItemInCart {
    private Item item;
    private int amount;

    public ItemInCart() {
    }

    public ItemInCart(Item item, int amount) {
        this.item = item;
        this.amount = amount;
    }

    public void incAmount(){
        this.amount ++;
    }

    public Item getItem() {
        return item;
    }

    public void setItem(Item item) {
        this.item = item;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    @Override
    public String toString(){
        return "{Clinent = " + this.item.toString() + " }\n" +
                ", Amount = " + this.amount;
    }
}
