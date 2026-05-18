package com.caNhan.E_conomy.Service.Impl;

import com.caNhan.E_conomy.Dto.RequestDto.ReqProductVariantDto;
import com.caNhan.E_conomy.Dto.ResponseDto.ResProductVariantDto;
import com.caNhan.E_conomy.Entity.Product;
import com.caNhan.E_conomy.Entity.ProductVariant;
import com.caNhan.E_conomy.GlobalExeption.Exception.NoSuchCustomerExistsException;
import com.caNhan.E_conomy.Repository.ProductRepository;
import com.caNhan.E_conomy.Repository.ProductVariantRepository;
import com.caNhan.E_conomy.Service.ProductVariantService;
import com.caNhan.E_conomy.Util.FileStorageUtil;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

@Service
public class ProductVariantServiceImpl implements ProductVariantService {
    private ProductVariantRepository productVariantRepository;
    private ProductRepository productRepository;
    private ModelMapper modelMapper;
    @Autowired
    public ProductVariantServiceImpl(ProductVariantRepository productVariantRepository,
                                     ProductRepository productRepository,
                                     ModelMapper modelMapper) {
        this.productVariantRepository = productVariantRepository;
        this.productRepository = productRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public ResProductVariantDto createProductVariant(ReqProductVariantDto reqProductVariantDto) {
        Optional<Product> productOptional = productRepository.findById(reqProductVariantDto.getProductId());
        if(productOptional.isEmpty()){
            throw new NoSuchCustomerExistsException("Product not found with id " + reqProductVariantDto.getProductId());
        }
        try{
            String pathProductColore = FileStorageUtil.storeFile("productColor", reqProductVariantDto.getUrlProductColor());
            ProductVariant productVariant = new ProductVariant();
            productVariant.setStorage(reqProductVariantDto.getStorage());
            productVariant.setOriginPrice(reqProductVariantDto.getOriginPrice());
            productVariant.setCurrentPrice(reqProductVariantDto.getCurrentPrice());
            productVariant.setStock(reqProductVariantDto.getStock());
            productVariant.setColorName(reqProductVariantDto.getColorName());
            productVariant.setUrlProductColor(pathProductColore);
            productVariant.setProduct(productOptional.get());

            ProductVariant saveProductVariant = productVariantRepository.save(productVariant);
            ResProductVariantDto resProductVariantDto = modelMapper.map(saveProductVariant, ResProductVariantDto.class);
            return resProductVariantDto;
        }catch (IOException ex){
            throw new RuntimeException(ex.getMessage());
        }


    }

    @Override
    public ResProductVariantDto getProductVariantById(long productVariantId) {
        Optional<ProductVariant> productVariantOptional = productVariantRepository.findById(productVariantId);
        if(productVariantOptional.isEmpty()){
            throw new NoSuchCustomerExistsException("Product variant not found with id " + productVariantId);
        }
        ResProductVariantDto resProductVariantDto = modelMapper.map(productVariantOptional.get(), ResProductVariantDto.class);
        return resProductVariantDto;
    }

    @Override
    public ResProductVariantDto updateProductVariant(long productVariantId, ReqProductVariantDto reqProductVariantDto) {
        return null;
    }

    @Override
    public List<ResProductVariantDto> getAllProductVariantByProductId(long productId) {
        List<ProductVariant> productVariantList = productVariantRepository.findAllByProductId(productId);
        List<ResProductVariantDto> resProductVariantDtoList = productVariantList.stream()
                .map(productVariant -> modelMapper.map(productVariant, ResProductVariantDto.class))
                .toList();
        return resProductVariantDtoList;
    }

    @Override
    public Long countProduct() {
       return productVariantRepository.sumProductVariantByStock();

    }
}
