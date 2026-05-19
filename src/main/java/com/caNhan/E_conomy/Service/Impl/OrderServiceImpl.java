package com.caNhan.E_conomy.Service.Impl;

import com.caNhan.E_conomy.Dto.RequestDto.ReqOrderDto;
import com.caNhan.E_conomy.Dto.ResponseDto.OrderCountStatusResponseDTO;
import com.caNhan.E_conomy.Dto.ResponseDto.ResOrderDto;
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

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Random;
import java.util.stream.Collectors;

@Service
public class OrderServiceImpl implements OrderService {
    private final UserRepository userRepository;
    private OrderRepository orderRepository;
    private CartRepository cartRepository;
    private CartItemRepository cartItemRepository;
    private ModelMapper modelMapper;
    @Autowired
    public OrderServiceImpl(OrderRepository orderRepository,
                            CartRepository cartRepository,
                            CartItemRepository cartItemRepository,
                            ModelMapper modelMapper, UserRepository userRepository) {
        this.orderRepository = orderRepository;
        this.cartRepository = cartRepository;
        this.cartItemRepository =cartItemRepository;
        this.modelMapper = modelMapper;
        this.userRepository = userRepository;
    }

    @Override
    public ResOrderDto createOrder(Long userId, ReqOrderDto reqOrderDto) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new NoSuchCustomerExistsException("User not found with id " + userId));
        List<CartItem> selectedItems = cartItemRepository.findAllById(reqOrderDto.getSelectedCartItemIds());
        if (selectedItems.isEmpty()) {
            throw new IllegalArgumentException("No items selected to create order.");
        }
        Random rd = new Random();
        int randomNumber = rd.nextInt(1000, 10000);
        String ngayFormat = LocalDate.now().format(DateTimeFormatter.ofPattern("MM/dd/yyyy"));
        Order order = new Order();
        order.setOrderCode(String.format("DH-%d-%s", randomNumber, ngayFormat));
        order.setStatus(OrderStatus.PENDING);
        order.setPaymentMethod(reqOrderDto.getPaymentMethod());
        order.setPaymentStatus(reqOrderDto.getPaymentMethod() == PaymentMethod.COD ?
                PaymentStatus.UNPAID : PaymentStatus.PAID);

        order.setDayCreate(LocalDateTime.now());
        order.setReceiverName(reqOrderDto.getReceiverName());
        order.setReceiverPhone(reqOrderDto.getReceiverPhone());
        order.setShippingAddress(reqOrderDto.getShippingAddress());
        order.setOrderItems(new ArrayList<>());

        double totalPrice = 0.0;
        for (CartItem cartItem : selectedItems) {
            OrderItem orderItem = new OrderItem();
            orderItem.setOrder(order);
            orderItem.setProductVariant(cartItem.getProductVariant());
            orderItem.setQuantity(cartItem.getQuantity());

            double currentPrice = cartItem.getProductVariant().getCurrentPrice();
            orderItem.setPriceBuy(currentPrice);


            totalPrice += currentPrice * cartItem.getQuantity();

            order.getOrderItems().add(orderItem);
        }
        order.setTotalPrice(totalPrice);


        Order savedOrder = orderRepository.save(order);


        cartItemRepository.deleteAll(selectedItems);

        ResOrderDto resOrderDto = modelMapper.map(savedOrder, ResOrderDto.class);
        resOrderDto.setUserId(userId); // ModelMapper có thể thiếu userId nếu Order không lưu trực tiếp userId, nên set tay cho chắc chắn

        return resOrderDto;
    }

    @Override
    public List<ResOrderDto> getAllOrderByUser(Long userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new NoSuchCustomerExistsException("User not found with id " + userId));
        List<Order> orderList = orderRepository.findByUserId(userId);
        List<ResOrderDto> orderDtoList = orderList.stream().
                map(order -> modelMapper.map(order, ResOrderDto.class))
                .toList();
        return  orderDtoList;
    }

    @Override
    public ResOrderDto updateOrderByOrderStatus (Long orderId, OrderStatus newOrderStatus) {
        Order order = orderRepository.findById(orderId)
                .orElseThrow(()-> new RuntimeException("Order not found with id " + orderId));
            order.setDayCreate(LocalDateTime.now());
            order.setStatus(newOrderStatus);
        Order update = orderRepository.save(order);
        return modelMapper.map(update, ResOrderDto.class);
    }

    @Override
    public ResOrderDto cancelOrderByOrderId(Long orderId) {
        Order order = orderRepository.findById(orderId)
                .orElseThrow(()-> new RuntimeException("Order not found with id " + orderId));
            order.setDayCreate(LocalDateTime.now());
            order.setStatus(OrderStatus.CANCELLED);
        Order update = orderRepository.save(order);
        return modelMapper.map(update, ResOrderDto.class);
    }

    @Override
    public List<ResOrderDto> getAllOrder() {
        List<Order> orderList = orderRepository.findAll();
        List<ResOrderDto> resOrderDtoList = orderList.stream()
                .map(order -> modelMapper.map(order, ResOrderDto.class))
                .toList();
        return  resOrderDtoList;
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
    public ResOrderDto updateOrderPaymentStatus(Long orderId, PaymentStatus paymentStatus) {
        Order order = orderRepository.findById(orderId)
                .orElseThrow(()-> new RuntimeException(" Không tìm thấy đơn hàng theo id " + orderId));
        if(paymentStatus != null){
            order.setDayCreate(LocalDateTime.now());
            order.setPaymentStatus(paymentStatus);
        }
        Order update = orderRepository.save(order);
        return modelMapper.map(update, ResOrderDto.class);
    }

    @Override
    public List<ResOrderDto> findAllOrdersByStatus(OrderStatus orderStatus) {
        List<Order> orders = orderRepository.findOrderByStatus(orderStatus);

        return orders.stream()
                .map(order -> modelMapper.map(order, ResOrderDto.class))
                .collect(Collectors.toList());
    }

    @Override
    public List<OrderCountStatusResponseDTO> countByStatus() {
        return orderRepository.countByStatus();
    }
}
