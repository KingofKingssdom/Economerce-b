package com.caNhan.E_conomy.Dto;

import java.util.List;

public class ProductSpecificationDTO {
    private String nameSpecification;
    private List<Long> productId;

    public ProductSpecificationDTO() {
    }

    public String getNameSpecification() {
        return nameSpecification;
    }

    public void setNameSpecification(String nameSpecification) {
        this.nameSpecification = nameSpecification;
    }

    public List<Long> getProductId() {
        return productId;
    }

    public void setProductId(List<Long> productId) {
        this.productId = productId;
    }
}
