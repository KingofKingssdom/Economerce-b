package com.caNhan.E_conomy.Service.Impl;

import com.caNhan.E_conomy.Dto.BrandDTO;
import com.caNhan.E_conomy.Entity.Brand;
import com.caNhan.E_conomy.Entity.Category;
import com.caNhan.E_conomy.GlobalExeption.Exception.CustomerAlreadyExistsException;
import com.caNhan.E_conomy.Repository.BrandRepository;
import com.caNhan.E_conomy.Repository.CategoryRepository;
import com.caNhan.E_conomy.Service.BrandService;
import com.caNhan.E_conomy.Util.FileStorageUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class BrandServiceImpl implements BrandService {
    private BrandRepository brandRepository;
    private CategoryRepository categoryRepository;
    @Autowired
    public BrandServiceImpl(BrandRepository brandRepository, CategoryRepository categoryRepository) {
        this.brandRepository = brandRepository;
        this.categoryRepository = categoryRepository;
    }

    @Override
    public Brand create(BrandDTO brandDTO) {
        try{
            Optional<Brand> brandOptional = brandRepository.findByBrandName(brandDTO.getBrandName());
            Brand brand;
            if(brandOptional.isPresent()){
                brand = brandOptional.get();
            }
            else {
                String brandBath = FileStorageUtil.storeFile("Brand", brandDTO.getUrlImageBrand());
                 brand = new Brand();
                brand.setBrandName(brandDTO.getBrandName());
                brand.setUrlImageBrand(FileStorageUtil.fullUrl(brandBath));
            }
            List<Category> categories = categoryRepository.findAllById(brandDTO.getCategoryId());
            brand.getCategories().addAll(categories);

            return brandRepository.save(brand);
        }catch (Exception e){
            throw new RuntimeException(e.getMessage());
        }
    }

    @Override
    public List<Brand> realAll() {
        return brandRepository.findAll();
    }
//    public Brand saveBrand (Brand brand, MultipartFile file, List<Integer> categoryIds) throws Exception{
//        if(!file.isEmpty()) {
//            brand.setImageBrand(file.getBytes());
//        }
//        List<Category> categories = new ArrayList<>();
//        for(Integer categoryId: categoryIds){
//            Optional<Category> categoryOptional = categoryRepository.findById(categoryId);
//            categories.add(categoryOptional.get());
//        }
//        brand.setCategories(categories);
//        return brandRepository.save(brand);
//    }
//
//    public Brand findBrandById (int id) {
//        return brandRepository.findById(id).orElseThrow();
//    }
//    public List<Brand> findAllBrand () {
//        return brandRepository.findAll();
//    }
//
//    public List<Brand> findBrandByCategoryId(Integer categoryId){
//        return brandRepository.findByCategories_Id(categoryId);
//    }
//    public void deleteBrand (Brand brand) {
//        brandRepository.delete(brand);
//    }


}
