package com.caNhan.E_conomy.Service.Impl;

import com.caNhan.E_conomy.Dto.CategoryDTO;
import com.caNhan.E_conomy.Entity.Category;
import com.caNhan.E_conomy.GlobalExeption.Exception.NoSuchCustomerExistsException;
import com.caNhan.E_conomy.Repository.CategoryRepository;
import com.caNhan.E_conomy.Service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CategoryServiceImpl implements CategoryService {
    private CategoryRepository categoryRepository;
    @Autowired
    public CategoryServiceImpl(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @Override
    public Category create(CategoryDTO categoryDto) {
        Category category = new Category();
        category.setCategoryName(categoryDto.getCategoryName());
        return categoryRepository.save(category);
    }

    @Override
    public List<Category> readAll() {
        return categoryRepository.findAll();
    }

    @Override
    public Category update(Long categoryId, CategoryDTO categoryDto) {
        Optional<Category> categoryOptional = categoryRepository.findById(categoryId);
        if(categoryOptional.isEmpty()){
            throw new NoSuchCustomerExistsException("Id category không tồn tại");
        }
        else {
           Category category = categoryOptional.get();
           category.setCategoryName(categoryDto.getCategoryName());
           return categoryRepository.save(category);
        }
    }

    @Override
    public Category readById(Long categoryId) {
        Optional<Category> categoryOptional = categoryRepository.findById(categoryId);
        if(categoryOptional.isEmpty()){
            throw new NoSuchCustomerExistsException("Không tìm thấy danh mục theo id = " + categoryId);
        }
        else {
            return categoryOptional.get();
        }
    }

    @Override
    public Category deleteById(Long categoryId) {
        return null;
    }
}
