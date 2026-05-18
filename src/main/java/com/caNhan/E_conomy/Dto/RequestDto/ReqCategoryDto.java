package com.caNhan.E_conomy.Dto.RequestDto;

public class ReqCategoryDto {
    private String categoryCode;
    private String categoryName;
    public ReqCategoryDto() {
    }

    public String getCategoryCode() {
        return categoryCode;
    }

    public void setCategoryCode(String categoryCode) {
        this.categoryCode = categoryCode;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }
}
