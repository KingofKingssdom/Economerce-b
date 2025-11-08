package com.caNhan.E_conomy.Controller;

import com.caNhan.E_conomy.Custom.CustomUserDetail;
import com.caNhan.E_conomy.Dto.OrderDTO;
import com.caNhan.E_conomy.Dto.ResponseDto.OrderResponseDTO;
import com.caNhan.E_conomy.Dto.ResponseDto.OrderResponseDTOU;
import com.caNhan.E_conomy.Entity.Cart;
import com.caNhan.E_conomy.Entity.Order;
import com.caNhan.E_conomy.Response.Enum.OrderStatus;
import com.caNhan.E_conomy.Response.Enum.PaymentMethod;
import com.caNhan.E_conomy.Response.Enum.PaymentStatus;
import com.caNhan.E_conomy.Response.ResponseData;
import com.caNhan.E_conomy.Service.CartService;
import com.caNhan.E_conomy.Service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/order")
public class OrderController {
    private OrderService orderService;
    private Authentication authentication;
    @Autowired
    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }
    @PostMapping("/create")
    private ResponseEntity<?> checkout(@RequestBody OrderDTO orderDTO){
        SecurityContext securityContext = SecurityContextHolder.getContext();
        Authentication authentication = securityContext.getAuthentication();
        CustomUserDetail customUserDetail = (CustomUserDetail) authentication.getPrincipal();
        Long userId = customUserDetail.getId();
        OrderResponseDTO order = orderService.create(userId,orderDTO.getCartItemIds());
        ResponseData responseData = new ResponseData(
                HttpStatus.OK.value(),
                "Tạo đơn hàng thành công",
                order
        );
        return ResponseEntity.ok(responseData);
    }

    @GetMapping("/all")
    private ResponseEntity<?> getAllOrderByUser(){
        SecurityContext securityContext = SecurityContextHolder.getContext();
        Authentication authentication = securityContext.getAuthentication();
        CustomUserDetail customUserDetail = (CustomUserDetail) authentication.getPrincipal();
        Long userId = customUserDetail.getId();
        List<OrderResponseDTO> orderResponse = orderService.findOrderByUser(userId);
        ResponseData responseData = new ResponseData(
                HttpStatus.OK.value(),
                "Lấy toàn bộ đơn hàng theo user thành công",
                orderResponse
        );
        return ResponseEntity.ok(responseData);
    }

    @PutMapping("/update")
    private ResponseEntity<?> updateOrderStatus (@RequestParam (name = "orderId") Long orderId,
                                                 @RequestParam (name = "status")OrderStatus status) {
        OrderResponseDTO orderResponseDTO = orderService.updateOrderStatus(orderId, status);
        ResponseData responseData = new ResponseData(
                HttpStatus.OK.value(),
                "Cập nhập trạng thái giao hàng thành công",
                orderResponseDTO
        );
        return ResponseEntity.ok(responseData);
    }
    @PutMapping("/update/payment")
    private ResponseEntity<?> updateOrderByPaymentMethodAndPaymentStatus (
            @RequestParam (name = "orderId") Long orderId,
            @RequestParam (name = "paymentMethod") PaymentMethod paymentMethod,
            @RequestParam (name = "paymentStatus")PaymentStatus paymentStatus) {
        OrderResponseDTO orderResponseDTO = orderService.
                updateOrderByPaymentMethodAndPaymentStatus(orderId, paymentMethod, paymentStatus);
        ResponseData responseData = new ResponseData(
                HttpStatus.OK.value(),
                "Cập nhập trạng thái đơn hàng thành công",
                orderResponseDTO
        );
        return ResponseEntity.ok(responseData);
    }
    @GetMapping("/search")
    private ResponseEntity<?> getOrderById(@RequestParam(name = "orderId") Long orderId) {
        OrderResponseDTO orderResponseDTO = orderService.findOrderById(orderId);
        ResponseData responseData = new ResponseData(
                HttpStatus.OK.value(),
                "Tìm đơn hàng theo id thanh công",
                orderResponseDTO
        );
        return ResponseEntity.ok(responseData);
    }

    @PutMapping("/delete")
    private ResponseEntity<?> deleteOrder (@RequestParam (name = "orderId") Long orderId,
                                           @RequestParam (name = "status")OrderStatus status) {
        OrderResponseDTO orderResponseDTO = orderService.deleteOrder(orderId, status);
        ResponseData responseData = new ResponseData(
                HttpStatus.OK.value(),
                "Cập nhập trạng thái giao hàng thành công",
                orderResponseDTO
        );
        return ResponseEntity.ok(responseData);
    }

    @GetMapping("/search/all")
    private ResponseEntity<?> findAllOrder(){
        List<OrderResponseDTOU> orderResponseDTO = orderService.findAllOrders();
        ResponseData responseData = new ResponseData(
                HttpStatus.OK.value(),
                "Lây toàn bộ đơn hàng thanh công",
                orderResponseDTO
        );
        return ResponseEntity.ok(responseData);
    }

    @PutMapping("/update/paymentStatus")
    private ResponseEntity<?> updateOrderByPPaymentStatus (
            @RequestParam (name = "orderId") Long orderId,
            @RequestParam (name = "paymentStatus")PaymentStatus paymentStatus) {
        OrderResponseDTO orderResponseDTO = orderService.
                updateOrderPaymentStatus(orderId, paymentStatus);
        ResponseData responseData = new ResponseData(
                HttpStatus.OK.value(),
                "Cập nhập trạng thái đơn hàng thành công",
                orderResponseDTO
        );
        return ResponseEntity.ok(responseData);
    }
}
