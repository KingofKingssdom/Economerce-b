package com.caNhan.E_conomy.Dto.ResponseDto;

public class ResProductVariantDto {
    private Long id;
    private String storage;
    private double originPrice;
    private double currentPrice;
    private int stock;
    private String urlProductColor;
    private String colorName;

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

    public double getOriginPrice() {
        return originPrice;
    }

    public void setOriginPrice(double originPrice) {
        this.originPrice = originPrice;
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

    public String getUrlProductColor() {
        return urlProductColor;
    }

    public void setUrlProductColor(String urlProductColor) {
        this.urlProductColor = urlProductColor;
    }

    public String getColorName() {
        return colorName;
    }

    public void setColorName(String colorName) {
        this.colorName = colorName;
    }
}
