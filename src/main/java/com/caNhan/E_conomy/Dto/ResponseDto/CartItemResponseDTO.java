package com.caNhan.E_conomy.Dto.ResponseDto;

import com.caNhan.E_conomy.Dto.RequestDto.ReqProductVariantDto;

public class CartItemResponseDTO {
    private Long id;
    private String productName;
    private double productPrice;
    private int quantity;
    private double totalPrice;
    private ReqProductVariantDto productVariant;
    private ProductColorResponseDTO productColor;
    private Long categoryId;
    public CartItemResponseDTO() {
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

    public ReqProductVariantDto getProductVariant() {
        return productVariant;
    }

    public void setProductVariant(ReqProductVariantDto productVariant) {
        this.productVariant = productVariant;
    }

    public ProductColorResponseDTO getProductColor() {
        return productColor;
    }

    public void setProductColor(ProductColorResponseDTO productColor) {
        this.productColor = productColor;
    }

    public double getProductPrice() {
        return productPrice;
    }

    public void setProductPrice(double productPrice) {
        this.productPrice = productPrice;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }

    public Long getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Long categoryId) {
        this.categoryId = categoryId;
    }
}
