package com.tofi.shop.domain;

import java.io.Serializable;
import java.util.Set;

@SuppressWarnings("unused")
public class Item implements Serializable {
    private Integer id;
    private String title;
    private String description;
    private Integer price;
    private byte[] image;
    private ItemCategory category;
    private ItemStatus status;

    public Item() {
    }

    public Item(Integer id, String title, String description,
                Integer price, ItemCategory category, ItemStatus status) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.price = price;
        this.category = category;
        this.status = status;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public ItemCategory getCategory() {
        return category;
    }

    public void setCategory(ItemCategory category) {
        this.category = category;
    }

    public ItemStatus getStatus() {
        return status;
    }

    public void setStatus(ItemStatus status) {
        this.status = status;
    }

    public byte[] getImage() {
        return image;
    }

    public void setImage(byte[] image) {
        this.image = image;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Item item = (Item) o;

        if (id != null ? !id.equals(item.id) : item.id != null) return false;
        if (title != null ? !title.equals(item.title) : item.title != null)
            return false;
        if (description != null ? !description.equals(item.description) : item.description != null)
            return false;
        if (price != null ? !price.equals(item.price) : item.price != null)
            return false;
        if (category != null ? !category.equals(item.category) : item.category != null)
            return false;
        return status != null ? status.equals(item.status) : item.status == null;

    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (title != null ? title.hashCode() : 0);
        result = 31 * result + (description != null ? description.hashCode() : 0);
        result = 31 * result + (price != null ? price.hashCode() : 0);
        result = 31 * result + (category != null ? category.hashCode() : 0);
        result = 31 * result + (status != null ? status.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Item{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", price=" + price +
                ", category=" + category +
                ", status=" + status +
                '}';
    }
}
