package com.caNhan.E_conomy.Service;

import com.caNhan.E_conomy.Dto.ResponseDto.OrderResponseDTO;
import com.caNhan.E_conomy.Dto.ResponseDto.OrderResponseDTOU;
import com.caNhan.E_conomy.Entity.CartItem;
import com.caNhan.E_conomy.Response.Enum.OrderStatus;
import com.caNhan.E_conomy.Response.Enum.PaymentMethod;
import com.caNhan.E_conomy.Response.Enum.PaymentStatus;

import java.util.List;


public interface OrderService {
    OrderResponseDTO create(Long userId, List<Long> carItemIds );
    List<OrderResponseDTO> findOrderByUser(Long userId);
    OrderResponseDTO updateOrderStatus (Long orderId, OrderStatus status);
    OrderResponseDTO findOrderById (Long orderId);
    OrderResponseDTO updateOrderByPaymentMethodAndPaymentStatus(Long orderId, PaymentMethod paymentMethod, PaymentStatus paymentStatus);
    OrderResponseDTO deleteOrder(Long orderId, OrderStatus orderStatus);
    List<OrderResponseDTOU> findAllOrders ();
    OrderResponseDTO updateOrderPaymentStatus(Long orderId, PaymentStatus paymentStatus);
}
