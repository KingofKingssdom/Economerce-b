package com.caNhan.E_conomy.Service;

import com.caNhan.E_conomy.Dto.ResponseDto.ResOrderItemDto;

import java.util.List;

public interface OrderItemService {
    List<ResOrderItemDto> getAllOrderItemsByOrderId(Long orderId);
}
