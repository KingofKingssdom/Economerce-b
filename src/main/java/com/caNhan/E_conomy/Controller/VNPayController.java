package com.caNhan.E_conomy.Controller;

import com.caNhan.E_conomy.Config.VNPayConfig;
import com.caNhan.E_conomy.Response.ResponseData;
import com.caNhan.E_conomy.Service.VNPayService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;
@RestController
@RequestMapping("/api/VNPay")
public class VNPayController {
    private VNPayService vnPayService;
    @Autowired
    public VNPayController(VNPayService vnPayService) {
        this.vnPayService = vnPayService;
    }
    @PostMapping("/submitOrder")
    public Map<String, Object> submitOrder(@RequestParam("amount") long orderTotal,
                                           @RequestParam("orderInfo") String orderInfo,
                                           @RequestParam(name = "orderId") String orderId,
                                           HttpServletRequest request) {
        String baseUrl = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort();
        String vnpayUrl = vnPayService.createOrder(orderTotal, orderInfo, orderId ,baseUrl);

        Map<String, Object> response = new HashMap<>();
        response.put("status", "OK");
        response.put("message", "VNPAY URL generated successfully");
        response.put("vnpayUrl", vnpayUrl);

        return response;
    }

    @GetMapping("/vnpay-payment")
    public ResponseEntity<?> vnpayPayment(HttpServletRequest request) {
        int paymentStatus = vnPayService.orderReturn(request);

        String orderInfo = request.getParameter("vnp_OrderInfo");
        String paymentTime = request.getParameter("vnp_PayDate");
        String transactionId = request.getParameter("vnp_TransactionNo");
        String totalPrice = request.getParameter("vnp_Amount");

        Map<String, Object> response = new HashMap<>();
        response.put("orderId", orderInfo);
        response.put("totalPrice", totalPrice);
        response.put("paymentTime", paymentTime);
        response.put("transactionId", transactionId);
        response.put("paymentStatus", paymentStatus == 1 ? "Success" : "Failed");
        ResponseData responseData = new ResponseData(
                HttpStatus.OK.value(),
                "Lấy dữ liệu thành công",
                response
        );

        return ResponseEntity.ok(responseData);
    }
}
