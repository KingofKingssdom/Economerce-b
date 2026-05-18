package com.caNhan.E_conomy.Dto.RequestDto;

import org.springframework.web.multipart.MultipartFile;

public class ReqProductVariantDto {
    private String storage;
    private double originPrice;
    private double currentPrice;
    private int stock;
    private MultipartFile urlProductColor;
    private String colorName;
    private Long productId;
    public ReqProductVariantDto() {
    }
    public String getStorage() {
        return storage;
    }

    public void setStorage(String storage) {
        this.storage = storage;
    }

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public double getOriginPrice() {
        return originPrice;
    }

    public void setOriginPrice(double originPrice) {
        this.originPrice = originPrice;
    }

    public String getColorName() {
        return colorName;
    }

    public void setColorName(String colorName) {
        this.colorName = colorName;
    }

    public double getCurrentPrice() {
        return currentPrice;
    }

    public void setCurrentPrice(double currentPrice) {
        this.currentPrice = currentPrice;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public MultipartFile getUrlProductColor() {
        return urlProductColor;
    }

    public void setUrlProductColor(MultipartFile urlProductColor) {
        this.urlProductColor = urlProductColor;
    }
}
