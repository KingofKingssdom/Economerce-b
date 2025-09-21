package com.caNhan.E_conomy.Dto.ResponseDto;

import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public class BrandResponseDTO {
    private Long id;
    private String brandName;
    private String urlImageBrand;
    private List<Long> categoryId;

    public BrandResponseDTO() {
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
    public String getUrlImageBrand() {
        return urlImageBrand;
    }

    public void setUrlImageBrand(String urlImageBrand) {
        this.urlImageBrand = urlImageBrand;
    }

    public List<Long> getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(List<Long> categoryId) {
        this.categoryId = categoryId;
    }
}
