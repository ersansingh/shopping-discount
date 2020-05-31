package com.cdk.model;

import java.util.List;

public class Cart {
    private Long cartId;
    private List<Item> items;

    Cart(Long cartId, List<Item> items) {
        this.cartId = cartId;
        this.items = items;
    }

    public static CartBuilder builder() {
        return new CartBuilder();
    }

    public Long getCartId() {
        return this.cartId;
    }

    public List<Item> getItems() {
        return this.items;
    }

    public CartBuilder toBuilder() {
        return new CartBuilder().cartId(this.cartId).items(this.items);
    }

    public static class CartBuilder {
        private Long cartId;
        private List<Item> items;

        CartBuilder() {
        }

        public Cart.CartBuilder cartId(Long cartId) {
            this.cartId = cartId;
            return this;
        }

        public Cart.CartBuilder items(List<Item> items) {
            this.items = items;
            return this;
        }

        public Cart build() {
            return new Cart(cartId, items);
        }

        public String toString() {
            return "Cart.CartBuilder(cartId=" + this.cartId + ", items=" + this.items + ")";
        }
    }
}
