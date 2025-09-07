package com.caNhan.E_conomy.Service;

import com.caNhan.E_conomy.Dto.ProductVariantDTO;
import com.caNhan.E_conomy.Dto.ResponseDto.ProductVariantResponseDTO;
import com.caNhan.E_conomy.Entity.ProductVariant;

public interface ProductVariantService {
    ProductVariantResponseDTO create(ProductVariantDTO productVariantDTO);
}
