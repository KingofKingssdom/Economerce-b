package com.caNhan.E_conomy.Service;

import com.caNhan.E_conomy.Dto.RequestDto.ReqBindSpecificationProductsDto;
import com.caNhan.E_conomy.Dto.RequestDto.ReqProductSpecificationDto;
import com.caNhan.E_conomy.Dto.ResponseDto.ResProductSpecificationDto;

import java.util.List;

public interface ProductSpecificationService {
    ResProductSpecificationDto createProductSpecification(ReqProductSpecificationDto reqProductSpecificationDto);
    List<ResProductSpecificationDto> getAllProductSpecification();
    ResProductSpecificationDto updateProductSpecification(Long id, ReqProductSpecificationDto reqProductSpecificationDto);
    void deleteProductSpecification(Long id);
    ResProductSpecificationDto createProductsToSpecification(ReqBindSpecificationProductsDto dto);

}
