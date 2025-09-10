package com.caNhan.E_conomy.Service;

import com.caNhan.E_conomy.Dto.CategoryDTO;
import com.caNhan.E_conomy.Entity.Category;

import java.util.List;

public interface CategoryService {
    Category create(CategoryDTO categoryDto);
    List<Category> readAll();
    Category update(Long categoryId, CategoryDTO categoryDto);
    Category readById(Long categoryId);
    Category deleteById(Long categoryId);
}
