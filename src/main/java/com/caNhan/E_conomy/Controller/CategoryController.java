package com.caNhan.E_conomy.Controller;

import com.caNhan.E_conomy.Entity.Category;
import com.caNhan.E_conomy.Service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/category")
public class CategoryController {
    private CategoryService categoryService;
    @Autowired
    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @PostMapping("/add")
    public Category addCategory (@RequestBody Category category) {
        return categoryService.saveCategory(category);
    }
    @GetMapping("/getAll")
    public List<Category> getAllCategory () {
        return categoryService.findAllCategory();
    }
    @GetMapping("{id}")
    public ResponseEntity<Category> getCategoryById(@PathVariable int id){
        Category category = categoryService.findCategoryById(id);
        return ResponseEntity.ok(category);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<Category> updateCategory(@PathVariable int id, @RequestBody Category newCategory) {
        Category category = categoryService.findCategoryById(id);
        if(category != null){
            category.setCategoryName(newCategory.getCategoryName());
        }
        categoryService.saveCategory(category);
        return ResponseEntity.ok(category);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<HttpStatus> deleteCategory (@PathVariable int id){
        Category category = categoryService.findCategoryById(id);
        categoryService.deleteCategory(category);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
