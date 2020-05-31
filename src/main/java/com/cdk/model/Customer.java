package com.cdk.model;


public class Customer {
    public enum Type {REGULAR, PREMIUM}

    private String name;
    private String emailId;
    private String mobileNo;
    private Cart cart;
    private Type type;

    Customer(String name, String emailId, String mobileNo, Cart cart, Type type) {
        this.name = name;
        this.emailId = emailId;
        this.mobileNo = mobileNo;
        this.cart = cart;
        this.type = type;
    }

    public Cart getCart() {
        return cart;
    }

    public Type getType() {
        return type;
    }

    public static CustomerBuilder builder() {
        return new CustomerBuilder();
    }

    public CustomerBuilder toBuilder() {
        return new CustomerBuilder().name(this.name).emailId(this.emailId).mobileNo(this.mobileNo).cart(this.cart).type(this.type);
    }

    public static class CustomerBuilder {
        private String name;
        private String emailId;
        private String mobileNo;
        private Cart cart;
        private Type type;

        CustomerBuilder() {
        }

        public CustomerBuilder name(String name) {
            this.name = name;
            return this;
        }

        public CustomerBuilder emailId(String emailId) {
            this.emailId = emailId;
            return this;
        }

        public CustomerBuilder mobileNo(String mobileNo) {
            this.mobileNo = mobileNo;
            return this;
        }

        public CustomerBuilder cart(Cart cart) {
            this.cart = cart;
            return this;
        }

        public CustomerBuilder type(Type type) {
            this.type = type;
            return this;
        }

        public Customer build() {
            return new Customer(name, emailId, mobileNo, cart, type);
        }

        public String toString() {
            return "Customer.CustomerBuilder(name=" + this.name + ", emailId=" + this.emailId + ", mobileNo=" + this.mobileNo + ", cart=" + this.cart + ", type=" + this.type + ")";
        }
    }
}
