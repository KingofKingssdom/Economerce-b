package com.caNhan.E_conomy.Service;


import com.caNhan.E_conomy.Dto.RequestDto.ReqProductDto;
import com.caNhan.E_conomy.Dto.ResponseDto.ResProductDto;
import org.springframework.data.domain.Page;

import java.util.List;

public interface ProductService {
  ResProductDto createProduct(ReqProductDto reqProductDto);
  List<ResProductDto> getAllProduct();
  ResProductDto getProductByProductCode(String productCode);
  ResProductDto updateProduct(long productId, ReqProductDto productDto);
  List<ResProductDto> getAllProductByCategory(long categoryId);
  List<ResProductDto> getAllProductByFeatured(boolean featured);
  List<ResProductDto> getProductByProductName(String productName);
  List<ResProductDto> getAllByPromotional(boolean promotional);
  List<ResProductDto> getAllProductByCategoryAndBrand(long categoryId, long brandId);
}
