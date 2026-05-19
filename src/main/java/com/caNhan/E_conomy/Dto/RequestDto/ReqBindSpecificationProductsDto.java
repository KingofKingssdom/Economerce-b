package com.caNhan.E_conomy.Dto.RequestDto;

import java.util.List;

public class ReqBindSpecificationProductsDto {
    private Long specificationId;
    private List<Long> productIds;

    public Long getSpecificationId() {
        return specificationId;
    }

    public void setSpecificationId(Long specificationId) {
        this.specificationId = specificationId;
    }

    public List<Long> getProductIds() {
        return productIds;
    }

    public void setProductIds(List<Long> productIds) {
        this.productIds = productIds;
    }
}
