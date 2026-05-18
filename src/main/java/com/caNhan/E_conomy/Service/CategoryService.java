package com.caNhan.E_conomy.Service;

import com.caNhan.E_conomy.Dto.RequestDto.ReqCategoryDto;
import com.caNhan.E_conomy.Dto.ResponseDto.ResCategoryDto;
import com.caNhan.E_conomy.Entity.Category;

import java.util.List;

public interface CategoryService {
    ResCategoryDto createCategory(ReqCategoryDto reqCategoryDto);
    List<ResCategoryDto> getAllCategory();
    ResCategoryDto updateCategory(Long categoryId, ReqCategoryDto categoryDto);
    ResCategoryDto getCategoryByCategoryCode(String categoryCode);
    void deleteCategory(Long categoryId);
}
