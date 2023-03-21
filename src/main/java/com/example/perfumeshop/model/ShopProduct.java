package com.example.perfumeshop.model;

public class ShopProduct {
    private Product product;
    private int stock;

    public ShopProduct(Product product, int stock) {
        this.product = product;
        this.stock = stock;
    }
    public Product getProduct() {
        return product;
    }

    public int getStock() {
        return stock;
    }
}
