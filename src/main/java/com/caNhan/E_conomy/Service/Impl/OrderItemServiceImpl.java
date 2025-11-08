package com.caNhan.E_conomy.Service.Impl;

import com.caNhan.E_conomy.Dto.ProductColorDTO;
import com.caNhan.E_conomy.Dto.ProductVariantDTO;
import com.caNhan.E_conomy.Dto.ResponseDto.OrderItemResponseDTO;
import com.caNhan.E_conomy.Dto.ResponseDto.ProductColorResponseDTO;
import com.caNhan.E_conomy.Dto.ResponseDto.ProductResponseDTO;
import com.caNhan.E_conomy.Entity.OrderItem;
import com.caNhan.E_conomy.Repository.OrderItemRepository;
import com.caNhan.E_conomy.Service.OrderItemService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

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
    public List<OrderItemResponseDTO> findOrderItemByOrder(Long orderId) {
        List<OrderItem> orderItems = orderItemRepository.findOrderItemByOrder_Id(orderId);

        return orderItems.stream()
                .map(orderItem -> {
                    OrderItemResponseDTO dto = modelMapper.map(orderItem, OrderItemResponseDTO.class);

                    if (orderItem.getProduct() != null) {
                        dto.setProductName(orderItem.getProduct().getProductName());
                    }
                    if (orderItem.getProductColor() != null) {
                        dto.setProductColor(modelMapper.map(orderItem.getProductColor(), ProductColorResponseDTO.class));
                    }
                    if (orderItem.getProductVariant() != null) {
                        dto.setProductVariant(modelMapper.map(orderItem.getProductVariant(), ProductVariantDTO.class));
                    }

                    return dto;
                })
                .collect(Collectors.toList());
    }
}
