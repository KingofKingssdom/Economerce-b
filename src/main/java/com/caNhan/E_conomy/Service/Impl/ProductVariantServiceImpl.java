package com.caNhan.E_conomy.Service.Impl;

import com.caNhan.E_conomy.Dto.ProductVariantDTO;
import com.caNhan.E_conomy.Entity.Product;
import com.caNhan.E_conomy.Entity.ProductVariant;
import com.caNhan.E_conomy.GlobalExeption.Exception.NoSuchCustomerExistsException;
import com.caNhan.E_conomy.Repository.ProductRepository;
import com.caNhan.E_conomy.Repository.ProductVariantRepository;
import com.caNhan.E_conomy.Service.ProductVariantService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
    public ProductVariantDTO create(ProductVariantDTO productVariantDTO) {
        Optional<Product> productId = productRepository.findById(productVariantDTO.getProductId());
        ProductVariant productVariant;
        if(productId.isEmpty()){
            throw new NoSuchCustomerExistsException("Không tạo được với sản phẩm ");
        }
        else {
            productVariant = new ProductVariant();
            productVariant.setStorage(productVariantDTO.getStorage());
            productVariant.setPriceOrigin(productVariantDTO.getPriceOrigin());
            productVariant.setPriceDiscount(productVariantDTO.getPriceDiscount());
            productVariant.setProduct(productId.get());
        }
        ProductVariant saveProductVariant = productVariantRepository.save(productVariant);
        return modelMapper.map(saveProductVariant, ProductVariantDTO.class);
    }
}
