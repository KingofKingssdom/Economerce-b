package com.caNhan.E_conomy.Service;

import com.caNhan.E_conomy.Dto.ResponseDto.OrderItemResponseDTO;

import java.util.List;

public interface OrderItemService {
    List<OrderItemResponseDTO> findOrderItemByOrder(Long orderId);
}
