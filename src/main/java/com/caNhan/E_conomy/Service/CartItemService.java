package com.caNhan.E_conomy.Service;

import com.caNhan.E_conomy.Dto.CartItemDTO;
import com.caNhan.E_conomy.Dto.ResponseDto.CartItemResponseDTO;

import java.util.List;

public interface CartItemService {
     CartItemResponseDTO create(Long userId, Long productId, Long productVariantId, Long productColorId);
     List<CartItemResponseDTO> findAllByCartId(Long userId);
     void deleteByCartId(Long cartItemId);
}
