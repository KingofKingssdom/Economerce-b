package com.caNhan.E_conomy.Dto;

public class SpecificationDetailDTO {
    private String labelSpecification;
    private String valueSpecification;
    private Long productSpecificationId;

    public SpecificationDetailDTO() {
    }

    public String getLabelSpecification() {
        return labelSpecification;
    }

    public void setLabelSpecification(String labelSpecification) {
        this.labelSpecification = labelSpecification;
    }

    public String getValueSpecification() {
        return valueSpecification;
    }

    public void setValueSpecification(String valueSpecification) {
        this.valueSpecification = valueSpecification;
    }

    public Long getProductSpecificationId() {
        return productSpecificationId;
    }

    public void setProductSpecificationId(Long productSpecificationId) {
        this.productSpecificationId = productSpecificationId;
    }
}
