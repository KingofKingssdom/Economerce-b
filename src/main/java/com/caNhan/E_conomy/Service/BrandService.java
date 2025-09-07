package com.caNhan.E_conomy.Service;

import com.caNhan.E_conomy.Dto.BrandDTO;
import com.caNhan.E_conomy.Entity.Brand;

import java.util.List;

public interface BrandService {
    Brand create(BrandDTO brandDTO);
    List<Brand> realAll();
}
