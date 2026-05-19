package com.caNhan.E_conomy.Service;

import com.caNhan.E_conomy.Dto.RequestDto.ReqOrderDto;
import com.caNhan.E_conomy.Dto.ResponseDto.OrderCountStatusResponseDTO;
import com.caNhan.E_conomy.Dto.ResponseDto.ResOrderDto;
import com.caNhan.E_conomy.Dto.ResponseDto.OrderResponseDTOU;
import com.caNhan.E_conomy.Response.Enum.OrderStatus;
import com.caNhan.E_conomy.Response.Enum.PaymentMethod;
import com.caNhan.E_conomy.Response.Enum.PaymentStatus;

import java.util.List;


public interface OrderService {
    ResOrderDto createOrder(Long userId, ReqOrderDto reqOrderDto);
    List<ResOrderDto> getAllOrderByUser(Long userId);
    ResOrderDto updateOrderByOrderStatus (Long orderId, OrderStatus newOrderStatus);
    ResOrderDto cancelOrderByOrderId (Long orderId);
    List<ResOrderDto> getAllOrder();
    Long countOrder();
    double sumPriceOrder(OrderStatus orderStatus);
    List<ResOrderDto> getOrderByOrderStatus(OrderStatus orderStatus);

}
