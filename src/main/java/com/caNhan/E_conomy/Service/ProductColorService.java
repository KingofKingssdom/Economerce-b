package com.caNhan.E_conomy.Service;

import com.caNhan.E_conomy.Dto.ProductColorDTO;
import com.caNhan.E_conomy.Dto.ResponseDto.ProductColorResponseDTO;

public interface ProductColorService {
    ProductColorResponseDTO create(ProductColorDTO productColorDTO);
}
