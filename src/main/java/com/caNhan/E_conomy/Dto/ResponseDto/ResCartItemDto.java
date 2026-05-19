package com.caNhan.E_conomy.Dto.ResponseDto;


import java.util.Date;

public class ResCartItemDto {
    private Long id;
    private long cartId;
    private long productVariantId;
    private String productName;
    private double priceAtTime;
    private int quantity;
    private Date createAt;
    private double totalPrice;
    private String storage;
    private String colorName;
    private String urlProductColor;
    public ResCartItemDto() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
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

    public double getPriceAtTime() {
        return priceAtTime;
    }

    public void setPriceAtTime(double priceAtTime) {
        this.priceAtTime = priceAtTime;
    }

    public Date getCreateAt() {
        return createAt;
    }

    public void setCreateAt(Date createAt) {
        this.createAt = createAt;
    }

    public String getStorage() {
        return storage;
    }

    public void setStorage(String storage) {
        this.storage = storage;
    }

    public String getColorName() {
        return colorName;
    }

    public void setColorName(String colorName) {
        this.colorName = colorName;
    }

    public String getUrlProductColor() {
        return urlProductColor;
    }

    public void setUrlProductColor(String urlProductColor) {
        this.urlProductColor = urlProductColor;
    }
}
