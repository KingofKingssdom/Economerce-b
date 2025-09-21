package com.caNhan.E_conomy.Dto;

public class ProductVariantDTO {
    private Long id;
    private String storage;
    private double priceOrigin;
    private double priceDiscount;
    private Long productId;

    public ProductVariantDTO() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getStorage() {
        return storage;
    }

    public void setStorage(String storage) {
        this.storage = storage;
    }

    public double getPriceOrigin() {
        return priceOrigin;
    }

    public void setPriceOrigin(double priceOrigin) {
        this.priceOrigin = priceOrigin;
    }

    public double getPriceDiscount() {
        return priceDiscount;
    }

    public void setPriceDiscount(double priceDiscount) {
        this.priceDiscount = priceDiscount;
    }

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }
}
