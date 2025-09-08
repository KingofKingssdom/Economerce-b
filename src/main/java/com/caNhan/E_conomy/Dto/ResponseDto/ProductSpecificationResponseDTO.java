package com.caNhan.E_conomy.Dto.ResponseDto;

import com.caNhan.E_conomy.Dto.SpecificationDetailDTO;
import com.caNhan.E_conomy.Entity.SpecificationDetail;
import com.caNhan.E_conomy.Service.SpecificationDetailService;

public class ProductSpecificationResponseDTO {
    private Long id;
    private String nameSpecification;
    private SpecificationDetail specificationDetail;
    public ProductSpecificationResponseDTO() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNameSpecification() {
        return nameSpecification;
    }

    public void setNameSpecification(String nameSpecification) {
        this.nameSpecification = nameSpecification;
    }

    public SpecificationDetail getSpecificationDetail() {
        return specificationDetail;
    }

    public void setSpecificationDetail(SpecificationDetail specificationDetail) {
        this.specificationDetail = specificationDetail;
    }
}
