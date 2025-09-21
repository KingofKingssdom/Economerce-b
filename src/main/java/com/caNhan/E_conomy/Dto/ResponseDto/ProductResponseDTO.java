package com.caNhan.E_conomy.Dto.ResponseDto;

import com.caNhan.E_conomy.Dto.ProductVariantDTO;
import com.caNhan.E_conomy.Entity.ProductVariant;

import java.util.List;

public class ProductResponseDTO {
    private Long id;
    private String productCode;
    private String productName;
    private String description;
    private String urlPhotoProduct;
    private Long categoryId;
    private List<ProductColorResponseDTO> productColors;
    private List<ProductSpecificationResponseDTO> specifications;
    private List<ProductVariantDTO> productVariants;
    private Long brandId;
    private boolean featured;
    private boolean promotional;
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

    public String getUrlPhotoProduct() {
        return urlPhotoProduct;
    }

    public void setUrlPhotoProduct(String urlPhotoProduct) {
        this.urlPhotoProduct = urlPhotoProduct;
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
