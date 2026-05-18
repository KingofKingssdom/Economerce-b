package com.caNhan.E_conomy.Dto.ResponseDto;

import java.util.List;

public class ResBrandDto {
    private Long id;
    private String brandName;
    private String brandCode;
    private String urlImageBrand;
    private List<Long> categoryIds;

    public ResBrandDto() {
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
    public String getBrandCode() {
        return brandCode;
    }

    public void setBrandCode(String brandCode) {
        this.brandCode = brandCode;
    }

    public List<Long> getCategoryIds() {
        return categoryIds;
    }

    public void setCategoryIds(List<Long> categoryIds) {
        this.categoryIds = categoryIds;
    }
}
