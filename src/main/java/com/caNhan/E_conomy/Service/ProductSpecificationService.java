package com.caNhan.E_conomy.Service;

import com.caNhan.E_conomy.Dto.ProductSpecificationDTO;
import com.caNhan.E_conomy.Dto.ResponseDto.ProductSpecificationResponseDTO;
public interface ProductSpecificationService {
    ProductSpecificationResponseDTO create(ProductSpecificationDTO productSpecificationDTO);

}
