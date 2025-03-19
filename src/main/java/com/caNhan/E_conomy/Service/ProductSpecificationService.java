package com.caNhan.E_conomy.Service;

import com.caNhan.E_conomy.Entity.ProductSpecification;
import com.caNhan.E_conomy.Repository.ProductSpecificationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductSpecificationService {
    private ProductSpecificationRepository productSpecificationRepository;
    @Autowired
    public ProductSpecificationService(ProductSpecificationRepository productSpecificationRepository) {
        this.productSpecificationRepository = productSpecificationRepository;
    }
    public ProductSpecification createProductSpecification(ProductSpecification productSpecification){
        return productSpecificationRepository.save(productSpecification);
    }
    public ProductSpecification findById(int id) {
        return productSpecificationRepository.findById(id).orElseThrow();
    }
    public List<ProductSpecification> findAll () {
        return productSpecificationRepository.findAll();
    }

}
