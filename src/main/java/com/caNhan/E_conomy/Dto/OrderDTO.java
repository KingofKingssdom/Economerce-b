package com.caNhan.E_conomy.Dto;

import java.util.List;

public class OrderDTO {
    private List<Long> cartItemIds;

    public List<Long> getCartItemIds() {
        return cartItemIds;
    }

    public void setCartItemIds(List<Long> cartItemIds) {
        this.cartItemIds = cartItemIds;
    }
}
