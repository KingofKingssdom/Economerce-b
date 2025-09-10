package com.caNhan.E_conomy.Service.Impl;

import com.caNhan.E_conomy.Dto.ProductColorDTO;
import com.caNhan.E_conomy.Dto.ResponseDto.ProductColorResponseDTO;
import com.caNhan.E_conomy.Entity.Product;
import com.caNhan.E_conomy.Entity.ProductColor;
import com.caNhan.E_conomy.GlobalExeption.Exception.NoSuchCustomerExistsException;
import com.caNhan.E_conomy.Repository.ProductRepository;
import com.caNhan.E_conomy.Repository.ProductColorRepository;
import com.caNhan.E_conomy.Service.ProductColorService;
import com.caNhan.E_conomy.Util.FileStorageUtil;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ProductColorServiceImpl implements ProductColorService {
    private ProductColorRepository productColorRepository;
    private ProductRepository productRepository;
    private ModelMapper modelMapper;
    @Autowired
    public ProductColorServiceImpl(ProductColorRepository productColorRepository,
                                   ProductRepository productRepository,
                                   ModelMapper modelMapper) {
        this.productColorRepository = productColorRepository;
        this.productRepository = productRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public ProductColorResponseDTO create(ProductColorDTO productColorDTO) {
        try{
            Optional<Product> productId = productRepository.findById(
                    productColorDTO.getProductId());
            ProductColor productColor;
            if(productId.isEmpty()){
                throw new NoSuchCustomerExistsException("Không tạo được phiên bản với Id = " + productColorDTO.getProductId());
            }
            else {
                String productColorPath = FileStorageUtil.storeFile("productColor",productColorDTO.getUrlPhoto());
                productColor = new ProductColor();
                productColor.setTitleVariant(productColorDTO.getTitleVariant());
                productColor.setUrlPhoto(FileStorageUtil.fullUrl(productColorPath));
                productColor.setProduct(productId.get());
                ProductColor saveProductColor = productColorRepository.save(productColor) ;
                return modelMapper.map(saveProductColor, ProductColorResponseDTO.class);
            }
        }catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }
}
