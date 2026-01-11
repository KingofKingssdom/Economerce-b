package com.caNhan.E_conomy.Service;

import com.caNhan.E_conomy.Dto.ProductSpecificationDTO;
import com.caNhan.E_conomy.Dto.ResponseDto.ProductSpecificationResponseDTO;
import com.caNhan.E_conomy.Entity.Product;

import java.util.List;

public interface ProductSpecificationService {
    ProductSpecificationResponseDTO create(ProductSpecificationDTO productSpecificationDTO);
    List<ProductSpecificationResponseDTO> findByProductId(Long productId);


}
