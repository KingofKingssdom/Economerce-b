package com.caNhan.E_conomy.Controller;

import com.caNhan.E_conomy.Entity.OrderItem;
import com.caNhan.E_conomy.Service.OrderItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/orderItem")
public class OrderItemController {
    private OrderItemService orderItemService;
    @Autowired
    public OrderItemController(OrderItemService orderItemService) {
        this.orderItemService = orderItemService;
    }
    @GetMapping("/orderId")
    public ResponseEntity<?> findByOrderId(@RequestParam("orderId") int orderId){
        List<OrderItem> orderItems = orderItemService.findByOrderId(orderId);
        return ResponseEntity.ok(orderItems);
    }
}
