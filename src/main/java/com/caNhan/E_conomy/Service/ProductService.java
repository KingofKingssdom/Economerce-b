package com.caNhan.E_conomy.Service;


import com.caNhan.E_conomy.Dto.ProductDTO;
import com.caNhan.E_conomy.Dto.ResponseDto.ProductColorResponseDTO;
import com.caNhan.E_conomy.Dto.ResponseDto.ProductResponseDTO;
import com.caNhan.E_conomy.Entity.Product;
import org.springframework.data.domain.Page;

import java.util.List;

public interface ProductService {
  ProductResponseDTO create(ProductDTO productDto);
  Page<ProductResponseDTO> readAll(int pageNumber, int pageSize);
  ProductResponseDTO readById(Long productId);
  ProductResponseDTO update(Long productId, ProductDTO productDTO);
  Page<ProductResponseDTO> readByCategory(Long categoryId, int pageNumber, int pageSize);
  Page<ProductResponseDTO> readByCategoryAndBrand(Long categoryId, Long brandId, int pageNumber, int pageSize);
  List<ProductResponseDTO> readAllByFeatured(boolean featured);
  List<ProductResponseDTO> readByProductName(String productName);
}
