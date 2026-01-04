package com.caNhan.E_conomy.Dto.ResponseDto;

import com.caNhan.E_conomy.Dto.ProductColorDTO;
import com.caNhan.E_conomy.Dto.ProductDTO;
import com.caNhan.E_conomy.Dto.ProductVariantDTO;

import java.util.List;

public class OrderItemResponseDTO {
    private Long id;
    private int quantity;
    private double priceBuy;
    private String productName;
    private ProductVariantDTO productVariant;
    private ProductColorResponseDTO productColor;
    private Long categoryId;
    public OrderItemResponseDTO() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public double getPriceBuy() {
        return priceBuy;
    }

    public void setPriceBuy(double priceBuy) {
        this.priceBuy = priceBuy;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public ProductVariantDTO getProductVariant() {
        return productVariant;
    }

    public void setProductVariant(ProductVariantDTO productVariant) {
        this.productVariant = productVariant;
    }

    public ProductColorResponseDTO getProductColor() {
        return productColor;
    }

    public void setProductColor(ProductColorResponseDTO productColor) {
        this.productColor = productColor;
    }

    public Long getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Long categoryId) {
        this.categoryId = categoryId;
    }
}
