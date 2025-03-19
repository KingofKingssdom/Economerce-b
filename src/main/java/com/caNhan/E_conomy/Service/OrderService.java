package com.caNhan.E_conomy.Service;

import com.caNhan.E_conomy.Entity.Cart;
import com.caNhan.E_conomy.Entity.CartItem;
import com.caNhan.E_conomy.Entity.Order;
import com.caNhan.E_conomy.Entity.OrderItem;
import com.caNhan.E_conomy.Repository.CartItemRepository;
import com.caNhan.E_conomy.Repository.CartRepository;
import com.caNhan.E_conomy.Repository.OrderItemRepository;
import com.caNhan.E_conomy.Repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class OrderService {
    private final CartService cartService;
    private OrderRepository orderRepository;
    private CartRepository cartRepository;
    private OrderItemRepository orderItemRepository;
    private CartItemRepository cartItemRepository;
    @Autowired
    public OrderService(OrderRepository orderRepository, CartRepository cartRepository, OrderItemRepository orderItemRepository, CartItemRepository cartItemRepository, CartService cartService) {
        this.orderRepository = orderRepository;
        this.cartRepository = cartRepository;
        this.orderItemRepository = orderItemRepository;
        this.cartItemRepository = cartItemRepository;
        this.cartService = cartService;
    }
    public Order createOrder(Order order, int id) {
        Cart cart = cartRepository.findById(id).orElseThrow();
        if(cart != null){
            order.setFullName(order.getFullName());
            order.setPhone(order.getPhone());
            order.setAddress(order.getAddress());
            order.setStatus("CHỜ XÁC NHẬN");
            order.setOrderDate(LocalDateTime.now());
            order.setUser(cart.getUser());
        }
        orderRepository.save(order);
        List<CartItem> cartItems = cartItemRepository.findByCartId(id);
        if(cartItems != null){
           for (CartItem cartItem : cartItems){
               OrderItem orderItem = new OrderItem();
               orderItem.setOrder(order);
               orderItem.setQuantity(cartItem.getQuantity());
               orderItem.setProduct(cartItem.getProduct());
               orderItemRepository.save(orderItem);
           }
        }
        Cart cart1 = cartRepository.findById(cart.getCartId()).orElseThrow();
        if(cart1 != null) {
            cartItemRepository.deleteByCartId(cart1.getCartId());
        }
        return order;
    }
    public Order findOrderById(int id) {
        return orderRepository.findById(id).orElseThrow();
    }
    public List<Order> findAll () {
        return orderRepository.findAll();
    }
    public void deleteOrder( Order order){
        orderRepository.delete(order);
    }
    public List<Order> findOrderByUserId(int id){
        return orderRepository.findByUserId(id);
    }
    public Order saveOrder(Order order) {
        return orderRepository.save(order);
    }
}
