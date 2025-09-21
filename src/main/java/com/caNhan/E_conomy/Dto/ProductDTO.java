package com.caNhan.E_conomy.Dto;

import org.springframework.web.multipart.MultipartFile;

public class ProductDTO {
    private Long id;
    private String productCode;
    private String productName;
    private String description;
    private MultipartFile urlPhoto;
    private Long categoryId;
    private Long brandId;
    private boolean featured;
    private boolean promotional;
    public ProductDTO() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getProductCode() {
        return productCode;
    }

    public void setProductCode(String productCode) {
        this.productCode = productCode;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public MultipartFile getUrlPhoto() {
        return urlPhoto;
    }

    public void setUrlPhoto(MultipartFile urlPhoto) {
        this.urlPhoto = urlPhoto;
    }

    public Long getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Long categoryId) {
        this.categoryId = categoryId;
    }

    public Long getBrandId() {
        return brandId;
    }

    public void setBrandId(Long brandId) {
        this.brandId = brandId;
    }

    public boolean isFeatured() {
        return featured;
    }

    public void setFeatured(boolean featured) {
        this.featured = featured;
    }

    public boolean isPromotional() {
        return promotional;
    }

    public void setPromotional(boolean promotional) {
        this.promotional = promotional;
    }
}
