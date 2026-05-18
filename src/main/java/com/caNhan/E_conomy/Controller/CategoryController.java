package com.caNhan.E_conomy.Controller;

import com.caNhan.E_conomy.Dto.RequestDto.ReqCategoryDto;
import com.caNhan.E_conomy.Dto.ResponseDto.ResCategoryDto;
import com.caNhan.E_conomy.Entity.Category;
import com.caNhan.E_conomy.Response.ResponseData;
import com.caNhan.E_conomy.Service.Impl.CategoryServiceImpl;
import kotlin.ULong;
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

    @PostMapping
    private ResponseEntity<?> create (@ModelAttribute ReqCategoryDto reqcategoryDto) {
        ResCategoryDto resCategoryDto = categoryService.createCategory(reqcategoryDto);
        ResponseData<ResCategoryDto> responseData = new ResponseData<>(
                HttpStatus.OK.value(),
                "Data created successfully",
                resCategoryDto
        );
        return ResponseEntity.ok(responseData);
    }

    @GetMapping
    private ResponseEntity<?> getAll () {
       List<ResCategoryDto> resCategoryDtoList = categoryService.getAllCategory();
        ResponseData<List<ResCategoryDto>> responseData = new ResponseData<>(
                HttpStatus.OK.value(),
                "Data retrieved successfully",
                resCategoryDtoList);
        return ResponseEntity.ok(responseData);
    }

    @PutMapping("/{categoryId}")
    private ResponseEntity<?> update(@PathVariable long categoryId,
                                             @ModelAttribute ReqCategoryDto reqCategoryDto) {
       ResCategoryDto category = categoryService.updateCategory(categoryId,reqCategoryDto);
        ResponseData responseData = new ResponseData(
                HttpStatus.OK.value(),
                "Data updated successfully",
                category);
        return  ResponseEntity.ok(responseData);
    }
    @GetMapping("/categoryCode/{categoryCode}")
    private ResponseEntity<?> getByCategoryCode(@PathVariable String categoryCode){
        ResCategoryDto resCategoryDto = categoryService.getCategoryByCategoryCode(categoryCode);
        ResponseData responseData = new ResponseData(
                HttpStatus.OK.value(),
                "Data retrieved successfully",
                resCategoryDto
        );
        return ResponseEntity.ok(responseData);
    }
    @DeleteMapping("/{categoryId}")
    public ResponseEntity<?> delete(@PathVariable long categoryId){
        categoryService.deleteCategory(categoryId);
        ResponseData responseData = new ResponseData(
                HttpStatus.OK.value(),
                "Data deleted successfully"
        );
        return ResponseEntity.ok(responseData);
    }

}
