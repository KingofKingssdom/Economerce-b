package com.caNhan.E_conomy.Dto;

import com.caNhan.E_conomy.Dto.ResponseDto.ProductVariantResponseDTO;
import org.springframework.web.multipart.MultipartFile;

public class ProductDTO {
    private Long id;
    private String productCode;
    private String productName;
    private String description;
    private MultipartFile urlPhoto;
    private Long categoryId;
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
}
