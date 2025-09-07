package com.caNhan.E_conomy.Dto;

import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public class BrandDTO {
    private Long id;
    private String brandName;
    private MultipartFile urlImageBrand;
    private List<Long> categoryId;
    private List<CategoryDTO> categoryDTOS;
    public BrandDTO() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getBrandName() {
        return brandName;
    }

    public void setBrandName(String brandName) {
        this.brandName = brandName;
    }

    public MultipartFile getUrlImageBrand() {
        return urlImageBrand;
    }

    public void setUrlImageBrand(MultipartFile urlImageBrand) {
        this.urlImageBrand = urlImageBrand;
    }

    public List<Long> getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(List<Long> categoryId) {
        this.categoryId = categoryId;
    }

    public List<CategoryDTO> getCategoryDTOS() {
        return categoryDTOS;
    }

    public void setCategoryDTOS(List<CategoryDTO> categoryDTOS) {
        this.categoryDTOS = categoryDTOS;
    }
}
