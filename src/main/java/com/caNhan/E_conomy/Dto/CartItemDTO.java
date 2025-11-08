package com.caNhan.E_conomy.Dto;

import com.caNhan.E_conomy.Entity.Cart;
import com.caNhan.E_conomy.Entity.Product;

public class CartItemDTO {
    private Cart cart;
    private Product product;
    private int quantity;
    private double totalPrice;
    public CartItemDTO() {
    }

    public Cart getCart() {
        return cart;
    }

    public void setCart(Cart cart) {
        this.cart = cart;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }
}
