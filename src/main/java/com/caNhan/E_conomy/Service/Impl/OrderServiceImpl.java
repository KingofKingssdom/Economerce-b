package com.caNhan.E_conomy.Service.Impl;

import com.caNhan.E_conomy.Dto.ResponseDto.OrderCountStatusResponseDTO;
import com.caNhan.E_conomy.Dto.ResponseDto.OrderResponseDTO;
import com.caNhan.E_conomy.Dto.ResponseDto.OrderResponseDTOU;
import com.caNhan.E_conomy.Entity.*;
import com.caNhan.E_conomy.GlobalExeption.Exception.NoSuchCustomerExistsException;
import com.caNhan.E_conomy.Repository.*;
import com.caNhan.E_conomy.Response.Enum.OrderStatus;
import com.caNhan.E_conomy.Response.Enum.PaymentMethod;
import com.caNhan.E_conomy.Response.Enum.PaymentStatus;
import com.caNhan.E_conomy.Service.OrderService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class OrderServiceImpl implements OrderService {
    private OrderRepository orderRepository;
    private CartRepository cartRepository;
    private CartItemRepository cartItemRepository;
    private ModelMapper modelMapper;
    @Autowired
    public OrderServiceImpl(OrderRepository orderRepository,
                            CartRepository cartRepository,
                            CartItemRepository cartItemRepository,
                            ModelMapper modelMapper) {
        this.orderRepository = orderRepository;
        this.cartRepository = cartRepository;
        this.cartItemRepository =cartItemRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public OrderResponseDTO create(Long userId, List<Long> carItemIds) {
        Cart cart = cartRepository.findCartByUserId(userId);
        List<CartItem> selectedItems = cartItemRepository.findAllById(carItemIds);
        if (selectedItems.isEmpty()) {
            throw new RuntimeException("Không có sản phẩm nào được chọn để đặt hàng");
        }

        Order order = new Order();
        order.setUser(cart.getUser());
        order.setStatus(OrderStatus.PENDING);
        order.setPaymentMethod(PaymentMethod.COD);
        order.setPaymentStatus(PaymentStatus.UNPAID);
        order.setDayCreate(LocalDateTime.now());
        order.setOrderItems(new ArrayList<>());

        double totalPrice = 0.0;

        for (CartItem cartItem : selectedItems) {
            OrderItem orderItem = new OrderItem();
            orderItem.setOrder(order);
            orderItem.setProduct(cartItem.getProduct());
            orderItem.setProductVariant(cartItem.getProductVariant());
            orderItem.setProductColor(cartItem.getProductColor());
            orderItem.setQuantity(cartItem.getQuantity());
            orderItem.setPriceBuy(cartItem.getProductPrice());
            orderItem.setCategoryId(cartItem.getCategoryId());
            totalPrice += orderItem.getPriceBuy() * orderItem.getQuantity();

            order.getOrderItems().add(orderItem);
        }
        List<String> productNames = order.getOrderItems().stream()
                .map(item -> item.getProduct().getProductName())
                .toList();
        String orderName = String.join(", ", productNames);
        if (orderName.length() > 240) {
            orderName = orderName.substring(0, 237) + "...";
        }
        order.setOrderName(orderName);
        order.setTotalPrice(totalPrice);
        Order savedOrder = orderRepository.save(order);

        // Xóa các item đã đặt khỏi giỏ hàng
        cart.getCartItems().removeIf(item -> carItemIds.contains(item.getId()));
        cartRepository.save(cart);

        return modelMapper.map(savedOrder, OrderResponseDTO.class);
    }

    @Override
    public List<OrderResponseDTO> findOrderByUser(Long userId) {
        List<Order> orders = orderRepository.findByUser_Id(userId);

        return orders.stream()
                .map(order -> modelMapper.map(order, OrderResponseDTO.class))
                .collect(Collectors.toList());
    }

    @Override
    public OrderResponseDTO updateOrderStatus(Long orderId, OrderStatus orderStatus) {
        Order order = orderRepository.findById(orderId)
                .orElseThrow(()-> new RuntimeException(" Không tìm thấy đơn hàng theo id " + orderId));
        if(orderStatus != null){
            order.setDayCreate(LocalDateTime.now());
            order.setStatus(orderStatus);
        }
        Order update = orderRepository.save(order);
        return modelMapper.map(update, OrderResponseDTO.class);
    }

    @Override
    public OrderResponseDTO findOrderById(Long orderId) {
        Order order = orderRepository.findById(orderId)
                .orElseThrow(()-> new RuntimeException("KHông tìm thấy đơn hàng theo id " + orderId));
        return modelMapper.map(order, OrderResponseDTO.class);
    }

    @Override
    public OrderResponseDTO updateOrderByPaymentMethodAndPaymentStatus(Long orderId, PaymentMethod paymentMethod, PaymentStatus paymentStatus) {
        Order order = orderRepository.findById(orderId)
                .orElseThrow(()-> new RuntimeException(" Không tìm thấy đơn hàng theo id " + orderId));
        if(paymentMethod != null && paymentStatus != null){
            order.setDayCreate(LocalDateTime.now());
            order.setPaymentStatus(paymentStatus);
            order.setPaymentMethod(paymentMethod);
        }
        Order update = orderRepository.save(order);
        return modelMapper.map(update, OrderResponseDTO.class);
    }

    @Override
    public OrderResponseDTO deleteOrder(Long orderId, OrderStatus orderStatus) {
        Order order = orderRepository.findById(orderId)
                .orElseThrow(()-> new RuntimeException(" Không tìm thấy đơn hàng theo id " + orderId));
        if(orderStatus != null){
            order.setDayCreate(LocalDateTime.now());
            order.setStatus(orderStatus);
        }
        Order delete = orderRepository.save(order);
        return modelMapper.map(delete, OrderResponseDTO.class);
    }

    @Override
    public List<OrderResponseDTOU> findAllOrders() {
        List<Order> orders = orderRepository.findAllOrder();

        return orders.stream().map(order -> {
            OrderResponseDTOU dto = new OrderResponseDTOU();

            // Gán trực tiếp các trường trong Order
            dto.setId(order.getId());
            dto.setDayCreate(order.getDayCreate());
            dto.setStatus(order.getStatus() != null ? order.getStatus().name() : null);
            dto.setPaymentMethod(order.getPaymentMethod() != null ? order.getPaymentMethod().name() : null);
            dto.setPaymentStatus(order.getPaymentStatus() != null ? order.getPaymentStatus().name() : null);
            dto.setTotalPrice(order.getTotalPrice());
            dto.setOrderName(order.getOrderName());

            // Gán thông tin User nếu có
            if (order.getUser() != null) {
                dto.setFullName(order.getUser().getFullName());
                dto.setPhoneNumber(order.getUser().getPhoneNumber());
                dto.setEmail(order.getUser().getEmail());
            }

            return dto;
        }).collect(Collectors.toList());
    }

    @Override
    public OrderResponseDTO updateOrderPaymentStatus(Long orderId, PaymentStatus paymentStatus) {
        Order order = orderRepository.findById(orderId)
                .orElseThrow(()-> new RuntimeException(" Không tìm thấy đơn hàng theo id " + orderId));
        if(paymentStatus != null){
            order.setDayCreate(LocalDateTime.now());
            order.setPaymentStatus(paymentStatus);
        }
        Order update = orderRepository.save(order);
        return modelMapper.map(update, OrderResponseDTO.class);
    }

    @Override
    public List<OrderResponseDTO> findAllOrdersByStatus(OrderStatus orderStatus) {
        List<Order> orders = orderRepository.findOrderByStatus(orderStatus);

        return orders.stream()
                .map(order -> modelMapper.map(order, OrderResponseDTO.class))
                .collect(Collectors.toList());
    }

    @Override
    public List<OrderCountStatusResponseDTO> countByStatus() {
        return orderRepository.countByStatus();
    }
}
