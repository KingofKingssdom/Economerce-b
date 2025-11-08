package com.caNhan.E_conomy.Service;

import com.caNhan.E_conomy.Dto.BrandDTO;
import com.caNhan.E_conomy.Dto.ResponseDto.BrandResponseDTO;
import com.caNhan.E_conomy.Entity.Brand;

import java.util.List;

public interface BrandService {
    Brand create(BrandDTO brandDTO);
    List<Brand> realAll();
    List<Brand> readByCategoryId(Long categoryId);
    Brand update(Long brandId, BrandDTO brandDTO);

}
