package com.caNhan.E_conomy.Controller;

import com.caNhan.E_conomy.Entity.Cart;
import com.caNhan.E_conomy.Entity.Order;
import com.caNhan.E_conomy.Service.CartService;
import com.caNhan.E_conomy.Service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/order")
public class OrderController {
    private OrderService orderService;
    private CartService cartService;
    @Autowired
    public OrderController(OrderService orderService, CartService cartService) {
        this.orderService = orderService;
        this.cartService = cartService;
    }
    @PostMapping("/add")
    public ResponseEntity<?> addOrder (
                           @RequestParam(value = "cartId") int cartId,
                           @RequestParam(value = "address") String address,
                           @RequestParam(value = "fullName") String fullName,
                           @RequestParam(value = "phone") String phone) {
        Cart cart = cartService.findCartById(cartId);
        Order order = new Order();
        if(cart != null){
            order.setUser(cart.getUser());
            order.setFullName(fullName);
            order.setPhone(phone);
            order.setAddress(address);
            orderService.createOrder(order,cartId);
        }

        return ResponseEntity.ok(order);
    }
    @GetMapping("/getAll")
    public List<Order> getAll() {
        return orderService.findAll();
    }
    @GetMapping("{id}")
    public ResponseEntity<Order> getOrderById (@PathVariable int id) {
        Order order = orderService.findOrderById(id);
        return ResponseEntity.ok(order);
    }

    @GetMapping("/user")
    public ResponseEntity<?> findOrderByUerId(@RequestParam("userId") int id){
        List<Order> orders = orderService.findOrderByUserId(id);
        return ResponseEntity.ok(orders);
    }

    @PutMapping("/update/status/{id}")
    public ResponseEntity<?> updateStatusById(@PathVariable int id,@RequestParam("status") String status,
                                              @RequestParam("description") String description){
        Order order = orderService.findOrderById(id);
        if (order!= null){
            order.setStatus(status);
            order.setDescription(description);
            orderService.saveOrder(order);
        }
        return ResponseEntity.ok(order);
    }
}
