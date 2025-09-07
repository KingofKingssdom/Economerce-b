package com.caNhan.E_conomy.Dto;

import org.springframework.web.multipart.MultipartFile;

public class ProductVariantDTO {
    private Long id;
    private MultipartFile urlPhoto;
    private String titleVariant;
    private Long productId;

    public ProductVariantDTO() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public MultipartFile getUrlPhoto() {
        return urlPhoto;
    }

    public void setUrlPhoto(MultipartFile urlPhoto) {
        this.urlPhoto = urlPhoto;
    }

    public String getTitleVariant() {
        return titleVariant;
    }

    public void setTitleVariant(String titleVariant) {
        this.titleVariant = titleVariant;
    }

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }
}
