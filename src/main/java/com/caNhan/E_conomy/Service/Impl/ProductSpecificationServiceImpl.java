package com.caNhan.E_conomy.Service.Impl;

import com.caNhan.E_conomy.Dto.RequestDto.ReqBindSpecificationProductsDto;
import com.caNhan.E_conomy.Dto.RequestDto.ReqProductSpecificationDto;
import com.caNhan.E_conomy.Dto.ResponseDto.ResProductSpecificationDto;
import com.caNhan.E_conomy.Entity.Product;
import com.caNhan.E_conomy.Entity.ProductSpecification;
import com.caNhan.E_conomy.GlobalExeption.Exception.NoSuchCustomerExistsException;
import com.caNhan.E_conomy.Repository.ProductRepository;
import com.caNhan.E_conomy.Repository.ProductSpecificationRepository;
import com.caNhan.E_conomy.Service.ProductSpecificationService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ProductSpecificationServiceImpl implements ProductSpecificationService {
    private ProductSpecificationRepository productSpecificationRepository;
    private ProductRepository productRepository;
    private ModelMapper modelMapper;
    @Autowired
    public ProductSpecificationServiceImpl(
            ProductSpecificationRepository productSpecificationRepository,
            ProductRepository productRepository,
            ModelMapper modelMapper) {
        this.productSpecificationRepository = productSpecificationRepository;
        this.productRepository = productRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public ResProductSpecificationDto createProductSpecification(ReqProductSpecificationDto reqProductSpecificationDto) {
        ProductSpecification productSpecification = new ProductSpecification();
        productSpecification.setNameSpecification(reqProductSpecificationDto.getNameSpecification());
        ProductSpecification saveSpecification = productSpecificationRepository.save(productSpecification);
       return  modelMapper.map(saveSpecification, ResProductSpecificationDto.class);
    }

    @Override
    public List<ResProductSpecificationDto> getAllProductSpecification() {
        List<ProductSpecification> productSpecificationList = productSpecificationRepository.findAll();
        List<ResProductSpecificationDto> resProductSpecificationDtoList =
                productSpecificationList.stream()
                        .map(productSpecification -> modelMapper.map(productSpecification, ResProductSpecificationDto.class))
                        .toList();
        return resProductSpecificationDtoList;
    }

    @Override
    public ResProductSpecificationDto updateProductSpecification(Long id, ReqProductSpecificationDto reqProductSpecificationDto) {
        ProductSpecification productSpecification =
                productSpecificationRepository.findById(id)
                        .orElseThrow(() -> new NoSuchCustomerExistsException("Product specification not found with id " + id));
        productSpecification.setNameSpecification(reqProductSpecificationDto.getNameSpecification());
        ProductSpecification saveProductSpecification = productSpecificationRepository.save(productSpecification);
        return modelMapper.map(saveProductSpecification, ResProductSpecificationDto.class);
    }

    @Override
    public void deleteProductSpecification(Long id) {
        ProductSpecification productSpecification =
                productSpecificationRepository.findById(id)
                        .orElseThrow(() -> new NoSuchCustomerExistsException("Product specification not found with id " + id));
        productSpecificationRepository.delete(productSpecification);
    }

    @Override
    public ResProductSpecificationDto createProductsToSpecification(ReqBindSpecificationProductsDto dto) {
        ProductSpecification productSpecification = productSpecificationRepository.findById(dto.getSpecificationId())
                .orElseThrow(() -> new RuntimeException("Product specification not found with id " + dto.getSpecificationId()));
        List<Product> newProductsToConnect = productRepository.findAllById(dto.getProductIds());
        if (newProductsToConnect.isEmpty()) {
            throw new RuntimeException("Product not found ");
        }
        List<Product> currentProducts = productSpecification.getProducts();
        if (currentProducts == null) {
            currentProducts = new ArrayList<>();
        }
        for (Product product : newProductsToConnect) {
            if (!currentProducts.contains(product)) {
                currentProducts.add(product);
            }
        }
        productSpecification.setProducts(currentProducts);
        ProductSpecification updatedSpec = productSpecificationRepository.save(productSpecification);
        return modelMapper.map(updatedSpec, ResProductSpecificationDto.class);
    }


}