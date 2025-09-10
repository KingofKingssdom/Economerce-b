package com.caNhan.E_conomy.Dto.ResponseDto;

import com.caNhan.E_conomy.Dto.ProductVariantDTO;
import com.caNhan.E_conomy.Entity.ProductVariant;

import java.util.List;

public class ProductResponseDTO {
    private Long id;
    private String productCode;
    private String productName;
    private String description;
    private String urlPhoto;
    private Long categoryId;
    private List<ProductColorResponseDTO> productColors;
    private List<ProductSpecificationResponseDTO> specifications;
    private List<ProductVariantDTO> productVariants;

    public ProductResponseDTO() {
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

    public String getUrlPhoto() {
        return urlPhoto;
    }

    public void setUrlPhoto(String urlPhoto) {
        this.urlPhoto = urlPhoto;
    }

    public Long getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Long categoryId) {
        this.categoryId = categoryId;
    }

    public List<ProductColorResponseDTO> getProductColors() {
        return productColors;
    }

    public void setProductColors(List<ProductColorResponseDTO> productColors) {
        this.productColors = productColors;
    }

    public List<ProductSpecificationResponseDTO> getSpecifications() {
        return specifications;
    }

    public void setSpecifications(List<ProductSpecificationResponseDTO> specifications) {
        this.specifications = specifications;
    }

    public List<ProductVariantDTO> getProductVariants() {
        return productVariants;
    }

    public void setProductVariants(List<ProductVariantDTO> productVariants) {
        this.productVariants = productVariants;
    }
}
