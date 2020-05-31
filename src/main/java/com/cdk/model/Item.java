package com.cdk.model;

import java.math.BigDecimal;

public class Item {
    private String name;
    private String description;
    private BigDecimal price;

    Item(String name, String description, BigDecimal price) {
        this.name = name;
        this.description = description;
        this.price = price;
    }

    public static ItemBuilder builder() {
        return new ItemBuilder();
    }

    public String getName() {
        return this.name;
    }

    public String getDescription() {
        return this.description;
    }

    public BigDecimal getPrice() {
        return this.price;
    }

    public ItemBuilder toBuilder() {
        return new ItemBuilder().name(this.name).description(this.description).price(this.price);
    }

    public static class ItemBuilder {
        private String name;
        private String description;
        private BigDecimal price;

        ItemBuilder() {
        }

        public Item.ItemBuilder name(String name) {
            this.name = name;
            return this;
        }

        public Item.ItemBuilder description(String description) {
            this.description = description;
            return this;
        }

        public Item.ItemBuilder price(BigDecimal price) {
            this.price = price;
            return this;
        }

        public Item build() {
            return new Item(name, description, price);
        }

        public String toString() {
            return "Item.ItemBuilder(name=" + this.name + ", description=" + this.description + ", price=" + this.price + ")";
        }
    }
}
