package com.caNhan.E_conomy.Service;

import com.caNhan.E_conomy.Entity.Brand;
import com.caNhan.E_conomy.Entity.Category;
import com.caNhan.E_conomy.Repository.BrandRepository;
import com.caNhan.E_conomy.Repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class BrandService {
    private BrandRepository brandRepository;
    private CategoryRepository categoryRepository;
    @Autowired
    public BrandService(BrandRepository brandRepository, CategoryRepository categoryRepository) {
        this.brandRepository = brandRepository;
        this.categoryRepository = categoryRepository;
    }
    public Brand saveBrand (Brand brand, MultipartFile file, List<Integer> categoryIds) throws Exception{
        if(!file.isEmpty()) {
            brand.setImageBrand(file.getBytes());
        }
        List<Category> categories = new ArrayList<>();
        for(Integer categoryId: categoryIds){
            Optional<Category> categoryOptional = categoryRepository.findById(categoryId);
            categories.add(categoryOptional.get());
        }
        brand.setCategories(categories);
        return brandRepository.save(brand);
    }

    public Brand findBrandById (int id) {
        return brandRepository.findById(id).orElseThrow();
    }
    public List<Brand> findAllBrand () {
        return brandRepository.findAll();
    }

    public List<Brand> findBrandByCategoryId(Integer categoryId){
        return brandRepository.findByCategories_Id(categoryId);
    }
    public void deleteBrand (Brand brand) {
        brandRepository.delete(brand);
    }


}
