package com.caNhan.E_conomy.Dto.RequestDto;

import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public class ReqBrandDto {
    private String brandName;
    private String brandCode;
    private MultipartFile urlImageBrand;
    private List<Long> categoryIds;
    public ReqBrandDto() {
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

    public List<Long> getCategoryIds() {
        return categoryIds;
    }

    public void setCategoryIds(List<Long> categoryIds) {
        this.categoryIds = categoryIds;
    }

    public String getBrandCode() {
        return brandCode;
    }

    public void setBrandCode(String brandCode) {
        this.brandCode = brandCode;
    }
}
