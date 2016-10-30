package com.tofi.shop.domain;

import java.util.Set;

public class User {
    private int id;
    private String login;
    private String password;
    private String cardId;
    private int discount;
    private Set<UserRole> roles;

    public User() {
    }

    public User(int id, String login, String password, String cardId, int discount) {
        this.id = id;
        this.login = login;
        this.password = password;
        this.cardId = cardId;
        this.discount = discount;
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

    public void setDiscount(int discount) {
        this.discount = discount;
    }

    public Set<UserRole> getRoles() {
        return roles;
    }

    public void setRoles(Set<UserRole> roles) {
        this.roles = roles;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        User user = (User) o;

        if (id != user.id) return false;
        if (discount != user.discount) return false;
        if (login != null ? !login.equals(user.login) : user.login != null)
            return false;
        if (password != null ? !password.equals(user.password) : user.password != null)
            return false;
        return cardId != null ? cardId.equals(user.cardId) : user.cardId == null;

    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (login != null ? login.hashCode() : 0);
        result = 31 * result + (password != null ? password.hashCode() : 0);
        result = 31 * result + (cardId != null ? cardId.hashCode() : 0);
        result = 31 * result + discount;
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
                '}';
    }
}
