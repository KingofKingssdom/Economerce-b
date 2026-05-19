package com.caNhan.E_conomy.Service.Impl;

import com.caNhan.E_conomy.Dto.ResponseDto.ResOrderItemDto;
import com.caNhan.E_conomy.Entity.OrderItem;
import com.caNhan.E_conomy.Repository.OrderItemRepository;
import com.caNhan.E_conomy.Service.OrderItemService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderItemServiceImpl implements OrderItemService {
    private OrderItemRepository orderItemRepository;
    private ModelMapper modelMapper;
    @Autowired
    public OrderItemServiceImpl(OrderItemRepository orderItemRepository, ModelMapper modelMapper) {
        this.orderItemRepository = orderItemRepository;
        this.modelMapper = modelMapper;
    }
    @Override
    public List<ResOrderItemDto> getAllOrderItemsByOrderId(Long orderId) {
        List<OrderItem> orderItems = orderItemRepository.findOrderItemByOrder_Id(orderId);
        List<ResOrderItemDto> resOrderItemDtos = orderItems.stream()
                .map(orderItem -> modelMapper.map(orderItem, ResOrderItemDto.class))
                .toList();
        return resOrderItemDtos;
    }
}
