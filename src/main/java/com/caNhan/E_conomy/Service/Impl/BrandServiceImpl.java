package com.caNhan.E_conomy.Service.Impl;

import com.caNhan.E_conomy.Dto.RequestDto.ReqBrandDto;
import com.caNhan.E_conomy.Dto.ResponseDto.ResBrandDto;
import com.caNhan.E_conomy.Entity.Brand;
import com.caNhan.E_conomy.Entity.Category;
import com.caNhan.E_conomy.GlobalExeption.Exception.CustomerAlreadyExistsException;
import com.caNhan.E_conomy.GlobalExeption.Exception.NoSuchCustomerExistsException;
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
    public ResBrandDto createBrand(ReqBrandDto reqBrandDto) {
        try{
            Optional<Brand> resBrandDtoOptional = brandRepository.findBrandByBrandCode(reqBrandDto.getBrandCode());
            Brand brand;
            if(resBrandDtoOptional.isPresent()){
                throw new CustomerAlreadyExistsException("Brand has already existed with brand code " + reqBrandDto.getBrandCode());
            }
            else {
                String brandBath = FileStorageUtil.storeFile("Brand", reqBrandDto.getUrlImageBrand());
                brand = new Brand();
                brand.setBrandCode(reqBrandDto.getBrandCode());
                brand.setBrandName(reqBrandDto.getBrandName());
                brand.setUrlImageBrand(brandBath);
                brand.setCategories(new ArrayList<>());
            }
            List<Category> categories = categoryRepository.findAllById(reqBrandDto.getCategoryIds());
            brand.getCategories().addAll(categories);
           Brand saveBrand = brandRepository.save(brand);
            ResBrandDto resBrandDto = modelMapper.map(saveBrand, ResBrandDto.class);
            return  resBrandDto;
        }catch (Exception e){
            throw new RuntimeException(e.getMessage());
        }
    }

    @Override
    public List<ResBrandDto> getAllBrand() {
        List<Brand> brandList = brandRepository.findAll();
        List<ResBrandDto> resBrandDtoList = brandList.stream()
                .map(brand -> modelMapper.map(brand, ResBrandDto.class))
                .toList();
        return resBrandDtoList;
    }

    @Override
    public ResBrandDto getBrandByBrandCode(String brandCode){
       Optional <Brand> brandOptional = brandRepository.findBrandByBrandCode(brandCode);
       if(brandOptional.isEmpty()){
           throw new NoSuchCustomerExistsException("Brand not found with brand code "+ brandCode);
       }
       Brand brand = brandOptional.get();
       ResBrandDto resBrandDto = modelMapper.map(brand, ResBrandDto.class);
       return  resBrandDto;
    }

    @Override
    public ResBrandDto updateBrand(Long brandId, ReqBrandDto brandDto) {
        Brand brand = brandRepository.findById(brandId)
                .orElseThrow(() -> new NoSuchCustomerExistsException("Brand not found with id " + brandId));
        Optional<Brand> brandOptional = brandRepository.findBrandByBrandCode(brandDto.getBrandCode());
        if(brandOptional.isPresent()){
            throw new CustomerAlreadyExistsException("Brand has already existed with brand code " + brandDto.getBrandCode());
        }
        try {
            String brandBath = FileStorageUtil.storeFile("Brand", brandDto.getUrlImageBrand());
            brand.setBrandCode(brandDto.getBrandCode());
            brand.setBrandName(brandDto.getBrandName());
            brand.setUrlImageBrand(brandBath);
            brand.setCategories(new ArrayList<>());
            List<Category> categories = categoryRepository.findAllById(brandDto.getCategoryIds());
            brand.getCategories().addAll(categories);
            Brand saveBrand = brandRepository.save(brand);
            ResBrandDto resBrandDto = modelMapper.map(saveBrand, ResBrandDto.class);
            return resBrandDto;
        } catch (Exception e) {
            throw new RuntimeException("Update fail: " + e.getMessage());
        }
    }

    @Override
    public void deleteBrand(long brandId){
        if(!brandRepository.existsById(brandId)){
            throw new NoSuchCustomerExistsException("Brand not found with brand id " + brandId);
        }
        brandRepository.deleteById(brandId);
    }

    @Override
    public List<ResBrandDto> getAllBrandByCategoryId(long categoryId){
        List<Brand> brandList  = brandRepository.findBrandByCategories(categoryId);
        List<ResBrandDto> resBrandDtoList = brandList.stream()
                .map(brand -> modelMapper.map(brand, ResBrandDto.class))
                .toList();
        return resBrandDtoList;
    }

}
