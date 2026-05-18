package com.caNhan.E_conomy.Service;

import com.caNhan.E_conomy.Dto.RequestDto.ReqBrandDto;
import com.caNhan.E_conomy.Dto.ResponseDto.ResBrandDto;
import com.caNhan.E_conomy.Entity.Brand;

import java.util.List;

public interface BrandService {
    ResBrandDto createBrand(ReqBrandDto brandDto);
    List<ResBrandDto> getAllBrand();
    ResBrandDto getBrandByBrandCode(String brandCode);
    ResBrandDto updateBrand(Long brandId, ReqBrandDto brandDto);
    void deleteBrand(long brandId);
    List<ResBrandDto> getAllBrandByCategoryId(long categoryId);

}
