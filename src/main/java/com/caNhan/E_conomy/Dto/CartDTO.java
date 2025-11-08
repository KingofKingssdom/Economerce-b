package com.caNhan.E_conomy.Dto;

import com.caNhan.E_conomy.Entity.Cart;

public class CartDTO {
    private Long id;
    private Cart cart;

    public CartDTO() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Cart getCart() {
        return cart;
    }

    public void setCart(Cart cart) {
        this.cart = cart;
    }
}
