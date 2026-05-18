package com.caNhan.E_conomy.Service.Impl;

import com.caNhan.E_conomy.Dto.RequestDto.ReqProductDto;
import com.caNhan.E_conomy.Dto.RequestDto.ReqProductVariantDto;
import com.caNhan.E_conomy.Dto.ResponseDto.ResProductDto;
import com.caNhan.E_conomy.Entity.Brand;
import com.caNhan.E_conomy.Entity.Category;
import com.caNhan.E_conomy.Entity.Product;
import com.caNhan.E_conomy.GlobalExeption.Exception.CustomerAlreadyExistsException;
import com.caNhan.E_conomy.GlobalExeption.Exception.NoSuchCustomerExistsException;
import com.caNhan.E_conomy.Repository.BrandRepository;
import com.caNhan.E_conomy.Repository.CategoryRepository;
import com.caNhan.E_conomy.Repository.ProductRepository;
import com.caNhan.E_conomy.Service.ProductService;
import com.caNhan.E_conomy.Util.FileStorageUtil;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

@Service
public class ProductServiceImpl implements ProductService {
    private ProductRepository productRepository;
    private CategoryRepository categoryRepository;
    private BrandRepository brandRepository;
    private ModelMapper modelMapper;
    @Autowired
    public ProductServiceImpl(ProductRepository productRepository, CategoryRepository categoryRepository, BrandRepository brandRepository,
                              ModelMapper modelMapper) {
        this.productRepository = productRepository;
        this.categoryRepository = categoryRepository;
        this.brandRepository = brandRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public ResProductDto createProduct(ReqProductDto reqProductDto) {
        Optional<Product> productOptional = productRepository.findAllByProductCode(reqProductDto.getProductCode());
        if(productOptional.isPresent()){
            throw new CustomerAlreadyExistsException("Product has already existed with product code " + reqProductDto.getProductCode());
        }
        Optional<Category> categoryOptional = categoryRepository.findById(reqProductDto.getCategoryId());
        if(categoryOptional.isEmpty()){
            throw new NoSuchCustomerExistsException("Category not found with id " + reqProductDto.getCategoryId());
        }
        Optional<Brand> brandOptional = brandRepository.findById(reqProductDto.getBrandId());
        if(brandOptional.isEmpty()){
            throw new NoSuchCustomerExistsException("Brand not found with id " + reqProductDto.getBrandId());
        }
        try{
            String productPath = FileStorageUtil.storeFile("Product",reqProductDto.getUrlPhoto());
            Product  product = new Product();
            product.setProductCode(reqProductDto.getProductCode());
            product.setProductName(reqProductDto.getProductName());
            product.setDescription(reqProductDto.getDescription());
            product.setPhotoUrl(productPath);
            product.setFeatured(reqProductDto.isFeatured());
            product.setPromotional(reqProductDto.isPromotional());
            product.setCategory(categoryOptional.get());
            product.setBrand(brandOptional.get());
            Product saveProduct =  productRepository.save(product);
            return modelMapper.map(saveProduct, ResProductDto.class);
        }
        catch (Exception ex){
            throw new RuntimeException(ex.getMessage());
        }
    }

    @Override
    public List<ResProductDto> getAllProduct() {
       List<Product> productList = productRepository.findAll();
       List<ResProductDto> productDtoList = productList.stream()
               .map(product -> modelMapper.map(product, ResProductDto.class))
               .toList();
        return productDtoList;
    }


    @Override
    public ResProductDto getProductByProductCode(String productCode) {
        Optional<Product> productOptional = productRepository.findAllByProductCode(productCode);
       if(productOptional.isEmpty()){
           throw new NoSuchCustomerExistsException("Product not found with product code " + productCode);
       }
       ResProductDto resProductDto = modelMapper.map(productCode, ResProductDto.class);
       return  resProductDto;

    }

    @Override
    public ResProductDto updateProduct(long productId, ReqProductDto reqProductDto) {
        Optional<Product> productOptional = productRepository.findById(productId);
        if(productOptional.isEmpty()){
            throw new NoSuchCustomerExistsException("Product not found with id " + productId);
        }
        Optional<Category> categoryOptional = categoryRepository.findById(reqProductDto.getCategoryId());
        if(categoryOptional.isEmpty()){
            throw new NoSuchCustomerExistsException("Category not found with id " + reqProductDto.getCategoryId());
        }
        Optional<Brand> brandOptional = brandRepository.findById(reqProductDto.getBrandId());
        if(brandOptional.isEmpty()){
            throw new NoSuchCustomerExistsException("Brand not found with id " + reqProductDto.getBrandId());
        }
        try{
            String productPath = FileStorageUtil.storeFile("Product",reqProductDto.getUrlPhoto());
            Product product = new Product();
            product.setProductCode(reqProductDto.getProductCode());
            product.setProductName(reqProductDto.getProductName());
            product.setDescription(reqProductDto.getDescription());
            product.setFeatured(reqProductDto.isFeatured());
            product.setPromotional(reqProductDto.isPromotional());
            product.setPhotoUrl(productPath);
            product.setCategory(categoryOptional.get());
            product.setBrand(brandOptional.get());
            Product updateProduct = productRepository.save(product);
            return modelMapper.map(updateProduct, ResProductDto.class);
        }
        catch (IOException e){
            throw new RuntimeException(e.getMessage());
        }
    }

    @Override
    public List<ResProductDto> getAllProductByCategory(long categoryId) {
        List<Product> productList = productRepository.findByCategoryId(categoryId);
        List<ResProductDto> resProductDtos = productList.stream()
                .map(product -> modelMapper.map(product, ResProductDto.class))
                .toList();
        return resProductDtos;
    }

    @Override
    public List<ResProductDto> getAllProductByFeatured(boolean featured) {
        List<Product> products = productRepository.findAllByFeatured(featured);
        return products.stream()
                .map(product -> {
            ResProductDto dto = modelMapper.map(product, ResProductDto.class);

            if (product.getProductVariants() != null) {
                dto.setProductVariants(
                        product.getProductVariants().stream()
                                .map(variant -> modelMapper.map(variant, ReqProductVariantDto.class))
                                .toList()
                );
            }
            return dto;})
                .toList();
    }

    @Override
    public List<ResProductDto> getProductByProductName(String productName) {
        List<Product> products = productRepository.findAllByProductName(productName);
        return products.stream()
                .map(product -> {
                    ResProductDto dto = modelMapper.map(product, ResProductDto.class);

                    if (product.getProductVariants() != null) {
                        dto.setProductVariants(
                                product.getProductVariants().stream()
                                        .map(variant -> modelMapper.map(variant, ReqProductVariantDto.class))
                                        .toList()
                        );
                    }
                    return dto;})
                .toList();
    }

//    @Override
//    public List<ResProductDto> readAllByPromotionalAndCategory(boolean promotional, Long categoryId) {
//        List<Product> products = productRepository.findAllByPromotionalAndCategory(promotional, categoryId);
//
//        return products.stream()
//                .map(product -> {
//                    ResProductDto dto = modelMapper.map(product, ResProductDto.class);
//
//                    if (product.getProductVariants() != null) {
//                        dto.setProductVariants(
//                                product.getProductVariants().stream()
//                                        .map(variant -> modelMapper.map(variant, ProductVariantDTO.class))
//                                        .toList()
//                        );
//                    }
//                    return dto;})
//                .toList();
//    }
   public List<ResProductDto> getAllByPromotional(boolean promotional){
        List<Product> productList = productRepository.findAllByPromotional(promotional);
        List<ResProductDto> productDtoList = productList.stream()
                .map(product -> modelMapper.map(product, ResProductDto.class))
                .toList();
        return  productDtoList;
    }
   public List<ResProductDto> getAllProductByCategoryAndBrand(long categoryId, long brandId){
        List<Product> productList = productRepository.findByCategoryAndBrand(categoryId, brandId);
        List<ResProductDto> productDtoList = productList.stream()
                .map(product -> modelMapper.map(product, ResProductDto.class))
                .toList();
        return  productDtoList;
    }
}
