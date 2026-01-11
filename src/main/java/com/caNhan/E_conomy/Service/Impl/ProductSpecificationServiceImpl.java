package com.caNhan.E_conomy.Service.Impl;

import com.caNhan.E_conomy.Dto.ProductSpecificationDTO;
import com.caNhan.E_conomy.Dto.ResponseDto.ProductSpecificationResponseDTO;
import com.caNhan.E_conomy.Entity.Product;
import com.caNhan.E_conomy.Entity.ProductSpecification;
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
    public ProductSpecificationResponseDTO create(ProductSpecificationDTO productSpecificationDTO) {
        Optional<ProductSpecification> productSpecificationOptional =
                productSpecificationRepository.findByNameSpecification(productSpecificationDTO.getNameSpecification());
        ProductSpecification specification;
        if(productSpecificationOptional.isPresent()) {
            specification = productSpecificationOptional.get();
        }
        else {
            specification = new ProductSpecification();
            specification.setNameSpecification(productSpecificationDTO.getNameSpecification());
            specification.setProducts(new ArrayList<>());
        }
        List<Product> productIds = productRepository.findAllById(productSpecificationDTO.getProductId());
        specification.getProducts().addAll(productIds);
       ProductSpecification saveSpecification = productSpecificationRepository.save(specification);
       return  modelMapper.map(saveSpecification, ProductSpecificationResponseDTO.class);
    }

    @Override
    public List<ProductSpecificationResponseDTO> findByProductId(Long productId) {
        List<ProductSpecification>
                productSpecs = productSpecificationRepository.findByProducts_Id(productId);
        return productSpecs.stream()
                .map(productSpec ->modelMapper.map(productSpec, ProductSpecificationResponseDTO.class))
                .toList();
    }
}