package com.caNhan.E_conomy.Service;

import com.caNhan.E_conomy.Dto.RequestDto.ReqCarItemDto;
import com.caNhan.E_conomy.Dto.ResponseDto.ResCartItemDto;

import java.util.List;

public interface CartItemService {
     ResCartItemDto createCartItem(ReqCarItemDto reqCarItemDto);
     List<ResCartItemDto> getCartItemByUserId(Long userId);
     ResCartItemDto updateCartItemByQuantity(Long cartItemId, int newQuantity);
     void deleteCartItemById(List<Long> cartItemIds, Long userId);
     void deleteAllCartItem(Long userId);
}
