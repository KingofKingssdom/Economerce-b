package com.caNhan.E_conomy.Service.Impl;

import com.caNhan.E_conomy.Dto.RequestDto.ReqCategoryDto;
import com.caNhan.E_conomy.Dto.ResponseDto.ResCategoryDto;
import com.caNhan.E_conomy.Entity.Category;
import com.caNhan.E_conomy.GlobalExeption.Exception.CustomerAlreadyExistsException;
import com.caNhan.E_conomy.GlobalExeption.Exception.NoSuchCustomerExistsException;
import com.caNhan.E_conomy.Repository.CategoryRepository;
import com.caNhan.E_conomy.Service.CategoryService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.swing.text.html.Option;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CategoryServiceImpl implements CategoryService {
    private CategoryRepository categoryRepository;
    private ModelMapper modelMapper;
    @Autowired
    public CategoryServiceImpl(CategoryRepository categoryRepository, ModelMapper modelMapper) {
        this.categoryRepository = categoryRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public ResCategoryDto createCategory(ReqCategoryDto reqCategoryDto) {
        Optional<Category> categoryOptional = categoryRepository
                .findCategoryByCategoryCode(reqCategoryDto.getCategoryCode());
        if(categoryOptional.isPresent()){
            throw new CustomerAlreadyExistsException("Category has already existed with categoryCode " + reqCategoryDto.getCategoryCode());
        }
        Category category = new Category();
        category.setCategoryCode(reqCategoryDto.getCategoryCode());
        category.setCategoryName(reqCategoryDto.getCategoryName());
        Category saveCategory = categoryRepository.save(category);
        ResCategoryDto resCategoryDto = modelMapper.map(saveCategory, ResCategoryDto.class);
        return resCategoryDto;
    }

    @Override
    public List<ResCategoryDto> getAllCategory() {
        List<Category> categoryList = categoryRepository.findAll();
        List<ResCategoryDto> resCategoryDtos = categoryList.stream()
                .map(category ->modelMapper.map(category, ResCategoryDto.class))
                .collect(Collectors.toList());
        return resCategoryDtos;
    }

    @Override
    public ResCategoryDto updateCategory(Long categoryId, ReqCategoryDto categoryDto) {
        Optional<Category> categoryOptional = categoryRepository.findById(categoryId);
        if(categoryOptional.isEmpty()){
            throw new NoSuchCustomerExistsException("Category not found with Id " + categoryId);
        }
        Optional<Category> categoryCode = categoryRepository.findCategoryByCategoryCode(categoryDto.getCategoryCode());
        if(categoryCode.isPresent()){
            throw new CustomerAlreadyExistsException("Category has already existed with categoryCode " + categoryDto.getCategoryCode());
        }
        else {
           Category category = categoryOptional.get();
           category.setCategoryCode(categoryDto.getCategoryCode());
           category.setCategoryName(categoryDto.getCategoryName());
          Category saveCategory = categoryRepository.save(category);
          ResCategoryDto resCategoryDto = modelMapper.map(saveCategory, ResCategoryDto.class);
            return  resCategoryDto;
        }
    }
    @Override
   public ResCategoryDto getCategoryByCategoryCode(String categoryCode){
        Optional<Category> category = categoryRepository.findCategoryByCategoryCode(categoryCode);
        if(category.isEmpty()){
            throw new NoSuchCustomerExistsException("Category not found with category code " + categoryCode);
        }
        ResCategoryDto resCategoryDto = modelMapper.map(category, ResCategoryDto.class);
        return  resCategoryDto;
    }

    @Override
    public void deleteCategory(Long categoryId) {
        if (!categoryRepository.existsById(categoryId)) {
            throw new NoSuchCustomerExistsException("Category not found with id " + categoryId);
        }
        categoryRepository.deleteById(categoryId);
    }
}
