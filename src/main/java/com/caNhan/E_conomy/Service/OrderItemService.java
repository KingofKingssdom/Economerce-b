package com.caNhan.E_conomy.Service;

import com.caNhan.E_conomy.Entity.OrderItem;
import com.caNhan.E_conomy.Repository.OrderItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderItemService {
    private OrderItemRepository orderItemRepository;
    @Autowired
    public OrderItemService(OrderItemRepository orderItemRepository) {
        this.orderItemRepository = orderItemRepository;
    }
    public List<OrderItem> findByOrderId(int orderId) {
        return  orderItemRepository.findByOrderId(orderId);
    }
}
