package com.caNhan.E_conomy.Service.Impl;

import com.caNhan.E_conomy.Dto.ProductVariantDTO;
import com.caNhan.E_conomy.Dto.ResponseDto.CartItemResponseDTO;
import com.caNhan.E_conomy.Dto.ResponseDto.ProductColorResponseDTO;
import com.caNhan.E_conomy.Entity.*;
import com.caNhan.E_conomy.GlobalExeption.Exception.NoSuchCustomerExistsException;
import com.caNhan.E_conomy.Repository.*;
import com.caNhan.E_conomy.Service.CartItemService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CartItemSeriveImpl implements CartItemService {
    private CartItemRepository cartItemRepository;
    private CartRepository cartRepository;
    private ProductRepository productRepository;
    private ProductVariantRepository productVariantRepository;
    private ProductColorRepository productColorRepository;
    private ModelMapper modelMapper;
    @Autowired
    public CartItemSeriveImpl(CartItemRepository cartItemRepository,
                              CartRepository cartRepository,
                              ProductRepository productRepository,
                              ProductVariantRepository productVariantRepository,
                              ProductColorRepository productColorRepository,
                              ModelMapper modelMapper) {
        this.cartItemRepository = cartItemRepository;
        this.cartRepository = cartRepository;
        this.productRepository = productRepository;
        this.productVariantRepository = productVariantRepository;
        this.productColorRepository = productColorRepository;
        this.modelMapper = modelMapper;
    }


    @Override
    public CartItemResponseDTO create(Long userId, Long productId, Long productVariantId, Long productColorId) {
        Cart cart = cartRepository.findCartByUserId(userId);
        Product product = productRepository.findById(productId).
                orElseThrow(()-> new RuntimeException("Không tìm thấy sản phẩm với id " + productId));
        ProductVariant productVariant = productVariantRepository.findById(productVariantId)
                .orElseThrow(()-> new RuntimeException("Không tìm thấy phiên bản sản phẩm với id " + productVariantId));
        ProductColor productColor = productColorRepository.findById(productColorId)
                .orElseThrow(()-> new RuntimeException("Không tìm thấy màu sắc sản phẩm với id " + productColorId));
        if (cart.getUser() == null) {
            throw new NoSuchCustomerExistsException("Không tìm thấy giỏ hàng với user = " +userId);
        }
        Optional<CartItem> cartItemOptional = cartItemRepository.findByCartAndProductAndProductVariantAndProductColor(cart, product, productVariant, productColor);
        CartItem cartItem;
        if(cartItemOptional.isPresent()){
            cartItem = cartItemOptional.get();
            cartItem.setQuantity(cartItem.getQuantity() +1);
        }
        else {
            cartItem = new CartItem();
            cartItem.setCart(cart);
            cartItem.setProductPrice(productVariant.getPriceDiscount());
            cartItem.setProduct(product);
            cartItem.setCategoryId(product.getCategory().getId());
            cartItem.setProductColor(productColor);
            cartItem.setProductVariant(productVariant);
            cartItem.setQuantity(1);

        }
        double total = cartItem.getProductPrice() * cartItem.getQuantity();
        cartItem.setTotalPrice(total);

        CartItem saveCartItem = cartItemRepository.save(cartItem);
        return modelMapper.map(saveCartItem, CartItemResponseDTO.class);
    }

    @Override
    public List<CartItemResponseDTO> findAllByCartId(Long userId) {
        Cart cart = cartRepository.findCartByUserId(userId);
        if(cart == null) {
            throw new NoSuchCustomerExistsException("Không tìm thấy giỏ hàng với user id " +userId );
        }
           List<CartItem> cartItems = cartItemRepository.findCartItemByCart(cart);


        return cartItems.stream()
                .map(item -> {
                    CartItemResponseDTO dto = new CartItemResponseDTO();
                    dto.setId(item.getId());
                    dto.setQuantity(item.getQuantity());
                    dto.setProductPrice(item.getProductPrice());
                    dto.setTotalPrice(item.getTotalPrice());
                    dto.setCategoryId(item.getCategoryId());
                    if (item.getProduct() != null) {
                        dto.setProductName(item.getProduct().getProductName());
                    }
                    if (item.getProductColor() != null) {
                        dto.setProductColor(modelMapper.map(item.getProductColor(), ProductColorResponseDTO.class));
                    }
                    if (item.getProductVariant() != null) {
                        dto.setProductVariant(modelMapper.map(item.getProductVariant(), ProductVariantDTO.class));
                    }

                    return dto;
                })
                .collect(Collectors.toList());
    }

    @Override
    public void deleteByCartId(Long cartItemId) {
        CartItem cart = cartItemRepository.findById(cartItemId)
                .orElseThrow(()-> new RuntimeException("Không tìm  thấy món hàng theo id " + cartItemId));
        cartItemRepository.delete(cart);
    }
}
