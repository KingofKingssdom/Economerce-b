package com.caNhan.E_conomy.Dto.ResponseDto;

import com.caNhan.E_conomy.Dto.RequestDto.SpecificationDetailDTO;

import java.util.List;

public class ResProductSpecificationDto {
    private Long id;
    private String nameSpecification;
    private List<SpecificationDetailDTO> specificationDetails;
    public ResProductSpecificationDto() {
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

    public List<SpecificationDetailDTO> getSpecificationDetails() {
        return specificationDetails;
    }

    public void setSpecificationDetails(List<SpecificationDetailDTO> specificationDetails) {
        this.specificationDetails = specificationDetails;
    }
}
