package com.tofi.shop.domain;

public class ItemInCart {
    private int id;
    private Item item;
    private User user;
    private int amount;

    public ItemInCart() {
    }

    public ItemInCart(int id, Item item, User user, int amount) {
        this.id = id;
        this.item = item;
        this.user = user;
        this.amount = amount;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Item getItem() {
        return item;
    }

    public void setItem(Item item) {
        this.item = item;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ItemInCart that = (ItemInCart) o;

        if (id != that.id) return false;
        if (amount != that.amount) return false;
        if (item != null ? !item.equals(that.item) : that.item != null)
            return false;
        return user != null ? user.equals(that.user) : that.user == null;

    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (item != null ? item.hashCode() : 0);
        result = 31 * result + (user != null ? user.hashCode() : 0);
        result = 31 * result + amount;
        return result;
    }

    @Override
    public String toString() {
        return "ItemInCart{" +
                "id=" + id +
                ", item=" + item +
                ", user=" + user +
                ", amount=" + amount +
                '}';
    }
}
