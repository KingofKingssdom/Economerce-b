package com.caNhan.E_conomy.Controller;

import com.caNhan.E_conomy.Custom.CustomUserDetail;
import com.caNhan.E_conomy.Dto.RequestDto.ReqOrderDto;
import com.caNhan.E_conomy.Dto.ResponseDto.OrderCountStatusResponseDTO;
import com.caNhan.E_conomy.Dto.ResponseDto.ResOrderDto;
import com.caNhan.E_conomy.Dto.ResponseDto.OrderResponseDTOU;
import com.caNhan.E_conomy.Response.Enum.OrderStatus;
import com.caNhan.E_conomy.Response.Enum.PaymentMethod;
import com.caNhan.E_conomy.Response.Enum.PaymentStatus;
import com.caNhan.E_conomy.Response.ResponseData;
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
    @PostMapping("/{userId}")
    private ResponseEntity<?> checkout(@PathVariable (name = "userId") Long userId,
            @ModelAttribute ReqOrderDto reqOrderDto){
//        SecurityContext securityContext = SecurityContextHolder.getContext();
//        Authentication authentication = securityContext.getAuthentication();
//        CustomUserDetail customUserDetail = (CustomUserDetail) authentication.getPrincipal();
//        Long userId = customUserDetail.getId();
        ResOrderDto order = orderService.createOrder(userId,reqOrderDto);
        ResponseData responseData = new ResponseData(
                HttpStatus.OK.value(),
                "Data created successfully",
                order
        );
        return ResponseEntity.ok(responseData);
    }

    @GetMapping("/user/{userId}")
    private ResponseEntity<?> getAllByUser(@PathVariable(name = "userId") Long userId){
//        SecurityContext securityContext = SecurityContextHolder.getContext();
//        Authentication authentication = securityContext.getAuthentication();
//        CustomUserDetail customUserDetail = (CustomUserDetail) authentication.getPrincipal();
//        Long userId = customUserDetail.getId();
        List<ResOrderDto> orderResponse = orderService.getAllOrderByUser(userId);
        ResponseData responseData = new ResponseData(
                HttpStatus.OK.value(),
                "Data retrieved successfully",
                orderResponse
        );
        return ResponseEntity.ok(responseData);
    }

    @PutMapping("/{orderId}")
    private ResponseEntity<?> update (@PathVariable Long orderId,
                                      @RequestParam (name = "status")OrderStatus status) {
        ResOrderDto resOrderDto = orderService.updateOrderByOrderStatus(orderId, status);
        ResponseData responseData = new ResponseData(
                HttpStatus.OK.value(),
                "Data updated successfully",
                resOrderDto
        );
        return ResponseEntity.ok(responseData);
    }
//    @PutMapping("/update/payment")
//    private ResponseEntity<?> updateOrderByPaymentMethodAndPaymentStatus (
//            @RequestParam (name = "orderId") Long orderId,
//            @RequestParam (name = "paymentMethod") PaymentMethod paymentMethod,
//            @RequestParam (name = "paymentStatus")PaymentStatus paymentStatus) {
//        ResOrderDto orderResponseDTO = orderService.
//                updateOrderByPaymentMethodAndPaymentStatus(orderId, paymentMethod, paymentStatus);
//        ResponseData responseData = new ResponseData(
//                HttpStatus.OK.value(),
//                "Cập nhập trạng thái đơn hàng thành công",
//                orderResponseDTO
//        );
//        return ResponseEntity.ok(responseData);
//    }
    @PutMapping("/{orderId}/cancel")
    private ResponseEntity<?> cancelOrder(@PathVariable Long orderId) {
        ResOrderDto resOrderDto = orderService.cancelOrderByOrderId(orderId);
        ResponseData responseData = new ResponseData(
                HttpStatus.OK.value(),
                "Data updated successfully",
                resOrderDto
        );
        return ResponseEntity.ok(responseData);
    }


    @GetMapping
    private ResponseEntity<?> getAll(){
        List<ResOrderDto> resOrderDtoList = orderService.getAllOrder();
        ResponseData responseData = new ResponseData(
                HttpStatus.OK.value(),
                "Data retrieved successfully",
                resOrderDtoList
        );
        return ResponseEntity.ok(responseData);
    }

    @GetMapping("/count")
    private ResponseEntity<?> countOrder () {
        Long count =
                orderService.countOrder();
        ResponseData responseData = new ResponseData(
                HttpStatus.OK.value(),
                "Data retrieved successfully",
                count
        );
        return ResponseEntity.ok(responseData);
    }

    @GetMapping("/sum-prices")
    private ResponseEntity<?> countOrderStatus(){
        double sum = orderService.sumPriceOrder();
        ResponseData responseData = new ResponseData(
                HttpStatus.OK.value(),
                "Data retrieved successfully",
                sum);
        return  ResponseEntity.ok(responseData);
    }
}
