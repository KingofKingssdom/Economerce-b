package com.caNhan.E_conomy.Service.Impl;

import com.caNhan.E_conomy.Dto.BrandDTO;
import com.caNhan.E_conomy.Dto.ResponseDto.BrandResponseDTO;
import com.caNhan.E_conomy.Entity.Brand;
import com.caNhan.E_conomy.Entity.Category;
import com.caNhan.E_conomy.Repository.BrandRepository;
import com.caNhan.E_conomy.Repository.CategoryRepository;
import com.caNhan.E_conomy.Service.BrandService;
import com.caNhan.E_conomy.Util.FileStorageUtil;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class BrandServiceImpl implements BrandService {
    private ModelMapper modelMapper;
    private BrandRepository brandRepository;
    private CategoryRepository categoryRepository;
    @Autowired
    public BrandServiceImpl(BrandRepository brandRepository, CategoryRepository categoryRepository, ModelMapper modelMapper) {
        this.brandRepository = brandRepository;
        this.categoryRepository = categoryRepository;
        this.modelMapper = modelMapper;
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
                brand.setCategories(new ArrayList<>());
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

    @Override
    public List<Brand> readByCategoryId(Long categoryId) {
        return brandRepository.findBrandByCategories(categoryId);
    }

}
