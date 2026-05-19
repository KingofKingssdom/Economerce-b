package com.caNhan.E_conomy.Dto.RequestDto;

import com.caNhan.E_conomy.Entity.Cart;
import com.caNhan.E_conomy.Entity.Product;

import java.util.Date;

public class ReqCarItemDto {
    private long cartId;
    private long productVariantId;
    private int quantity;
    public ReqCarItemDto() {
    }

    public long getCartId() {
        return cartId;
    }

    public void setCartId(long cartId) {
        this.cartId = cartId;
    }

    public long getProductVariantId() {
        return productVariantId;
    }

    public void setProductVariantId(long productVariantId) {
        this.productVariantId = productVariantId;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }


}
