package com.caNhan.E_conomy.Service;


import com.caNhan.E_conomy.Dto.ProductDTO;
import com.caNhan.E_conomy.Dto.ResponseDto.ProductResponseDTO;
import com.caNhan.E_conomy.Entity.Product;

import java.util.List;

public interface ProductService {
  Product create(ProductDTO productDto);
  List<ProductResponseDTO> readAll();
  ProductResponseDTO readById(Long productId);
  ProductResponseDTO update(Long productId, ProductDTO productDTO);

}
