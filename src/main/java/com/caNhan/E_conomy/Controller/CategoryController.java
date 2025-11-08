package com.caNhan.E_conomy.Controller;

import com.caNhan.E_conomy.Dto.CategoryDTO;
import com.caNhan.E_conomy.Entity.Category;
import com.caNhan.E_conomy.Response.ResponseData;
import com.caNhan.E_conomy.Service.Impl.CategoryServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/category")
public class CategoryController {
    private CategoryServiceImpl categoryService;
    @Autowired
    public CategoryController(CategoryServiceImpl categoryService) {
        this.categoryService = categoryService;
    }

    @PostMapping("/create")
    private ResponseEntity<ResponseData<Category>> addCategory (@ModelAttribute CategoryDTO categoryDTO) {
        Category category = categoryService.create(categoryDTO);
        ResponseData<Category> responseData = new ResponseData<>(
                HttpStatus.OK.value(),
                "Tạo danh mục thành công",
                category
        );
        return ResponseEntity.ok(responseData);
    }

    @GetMapping("/search/all")
    private ResponseEntity<ResponseData<?>> getAllCategory () {
       List<Category> categories = categoryService.readAll();
        ResponseData responseData = new ResponseData(
                HttpStatus.OK.value(),
                "Lấy toàn bộ mục thành công",
                categories);
        return ResponseEntity.ok(responseData);
    }

    @PutMapping("/update")
    private ResponseEntity<?> updateCategory(@RequestParam(name = "categoryId") Long categoryId,
                                             @ModelAttribute CategoryDTO categoryDto) {
       Category category = categoryService.update(categoryId,categoryDto);
        ResponseData responseData = new ResponseData(
                HttpStatus.OK.value(),
                "Cập nhập danh mục thành công",
                category);
        return  ResponseEntity.ok(responseData);
    }
    @GetMapping("/search")
    private ResponseEntity<?> searchById(@RequestParam(name = "categoryId") Long categoryId){
        Category category = categoryService.readById(categoryId);
        ResponseData responseData = new ResponseData(
                HttpStatus.OK.value(),
                "Lấy danh mục theo id thành công",
                category
        );
        return ResponseEntity.ok(responseData);
    }

}
