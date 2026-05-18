package com.caNhan.E_conomy.Service;

import com.caNhan.E_conomy.Dto.RequestDto.ReqProductVariantDto;
import com.caNhan.E_conomy.Dto.ResponseDto.ResProductVariantDto;

import java.util.List;

public interface ProductVariantService {
    ResProductVariantDto createProductVariant(ReqProductVariantDto reqProductVariantDto);
    ResProductVariantDto getProductVariantById(long productVariantId);
    ResProductVariantDto updateProductVariant(long productVariantId, ReqProductVariantDto reqProductVariantDto);
    List<ResProductVariantDto> getAllProductVariantByProductId(long productId);
    Long countProduct();
}
