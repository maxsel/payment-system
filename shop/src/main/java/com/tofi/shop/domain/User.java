package com.tofi.shop.domain;

import java.util.List;
import java.util.Set;

public class User {
    private int id;
    private String login;
    private String password;
    private String cardId;
    private int discount;
    private boolean blocked;
    private Set<UserRole> roles;
    private List<ItemInCart> itemsInCart;
    private List<Order> orders;

    public User() {
    }

    public User(int id, String login, String password, String cardId, int discount, boolean blocked) {
        this.id = id;
        this.login = login;
        this.password = password;
        this.cardId = cardId;
        this.discount = discount;
        this.blocked = blocked;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getCardId() {
        return cardId;
    }

    public void setCardId(String cardId) {
        this.cardId = cardId;
    }

    public int getDiscount() {
        return discount;
    }

    public float getDeltaDiscount() {
        return discount / 100f;
    }

    public void setDiscount(int discount) {
        this.discount = discount;
    }

    public Set<UserRole> getRoles() {
        return roles;
    }

    public void setRoles(Set<UserRole> roles) {
        this.roles = roles;
    }

    public List<ItemInCart> getItemsInCart() {
        return itemsInCart;
    }

    public void setItemsInCart(List<ItemInCart> itemsInCart) {
        this.itemsInCart = itemsInCart;
    }

    public List<Order> getOrders() {
        return orders;
    }

    public void setOrders(List<Order> orders) {
        this.orders = orders;
    }

    public boolean isBlocked() {
        return blocked;
    }

    public void setBlocked(boolean blocked) {
        this.blocked = blocked;
    }

    @Override
    public boolean equals(Object o) { // by login adn password only
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        User user = (User) o;

        if (login != null ? !login.equals(user.login) : user.login != null)
            return false;
        return password != null ? password.equals(user.password) : user.password == null;

    }

    @Override
    public int hashCode() {
        int result = login != null ? login.hashCode() : 0;
        result = 31 * result + (password != null ? password.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", login='" + login + '\'' +
                ", password='" + password + '\'' +
                ", cardId='" + cardId + '\'' +
                ", discount=" + discount +
                ", blocked=" + blocked +
                '}';
    }
}
