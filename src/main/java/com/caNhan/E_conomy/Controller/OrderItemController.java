package com.caNhan.E_conomy.Controller;

import com.caNhan.E_conomy.Dto.ResponseDto.OrderItemResponseDTO;
import com.caNhan.E_conomy.Response.ResponseData;
import com.caNhan.E_conomy.Service.OrderItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/orderItem")
public class OrderItemController {
    private OrderItemService orderItemService;
    @Autowired
    public OrderItemController(OrderItemService orderItemService) {
        this.orderItemService = orderItemService;
    }
    @GetMapping("/all")
    private ResponseEntity<?> findByOrderId(@RequestParam(name = "orderId") Long orderId){
        List<OrderItemResponseDTO> orderItems = orderItemService.findOrderItemByOrder(orderId);
        ResponseData responseData = new ResponseData(
                HttpStatus.OK.value(),
                "Lấy toàn bộ chi tiết đơn hàng theo mã đơn hàng thành công",
                orderItems
        );
        return ResponseEntity.ok(responseData);
    }
}
