package com.caNhan.E_conomy.Service.Impl;

import ch.qos.logback.core.rolling.helper.FileStoreUtil;
import com.caNhan.E_conomy.Dto.ProductVariantDTO;
import com.caNhan.E_conomy.Dto.ResponseDto.ProductVariantResponseDTO;
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

import java.util.Optional;

@Service
public class ProductVariantImpl implements ProductVariantService {
    private ProductVariantRepository productVariantRepository;
    private ProductRepository productRepository;
    private ModelMapper modelMapper;
    @Autowired
    public ProductVariantImpl(ProductVariantRepository productVariantRepository,
                              ProductRepository productRepository,
                              ModelMapper modelMapper) {
        this.productVariantRepository = productVariantRepository;
        this.productRepository = productRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public ProductVariantResponseDTO create(ProductVariantDTO productVariantDTO) {
        try{
            Optional<Product> productId = productRepository.findById(
                                        productVariantDTO.getProductId());
            ProductVariant productVariant;
            if(productId.isEmpty()){
                throw new NoSuchCustomerExistsException("Không tạo được phiên bản với Id = " + productVariantDTO.getProductId());
            }
            else {
                String productVariantPath = FileStorageUtil.storeFile("productVariant",productVariantDTO.getUrlPhoto());
                productVariant = new ProductVariant();
                productVariant.setTitleVariant(productVariantDTO.getTitleVariant());
                productVariant.setUrlPhoto(FileStorageUtil.fullUrl(productVariantPath));
                productVariant.setProduct(productId.get());
                ProductVariant saveProductVariant = productVariantRepository.save(productVariant) ;
                return modelMapper.map(saveProductVariant, ProductVariantResponseDTO.class);
            }
        }catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }
}
