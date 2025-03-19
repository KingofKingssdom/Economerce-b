package com.caNhan.E_conomy.Service;

import com.caNhan.E_conomy.Entity.Category;
import com.caNhan.E_conomy.Repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryService {
    private CategoryRepository categoryRepository;
    @Autowired
    public CategoryService(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }
    public Category saveCategory (Category category) {
        return categoryRepository.save(category);
    }

    public List <Category> findAllCategory () {
        return categoryRepository.findAll();
    }
    public Category findCategoryById (int id) {
        return categoryRepository.findById(id).orElseThrow();
    }
    public void deleteCategory (Category category) {
        categoryRepository.delete(category);
    }
}
