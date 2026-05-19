package com.caNhan.E_conomy.Service.Impl;

import com.caNhan.E_conomy.Dto.RequestDto.ReqCarItemDto;
import com.caNhan.E_conomy.Dto.ResponseDto.ResCartItemDto;
import com.caNhan.E_conomy.Entity.*;
import com.caNhan.E_conomy.GlobalExeption.Exception.NoSuchCustomerExistsException;
import com.caNhan.E_conomy.Repository.*;
import com.caNhan.E_conomy.Service.CartItemService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CartItemSeriveImpl implements CartItemService {
    private CartItemRepository cartItemRepository;
    private CartRepository cartRepository;
    private ProductVariantRepository productVariantRepository;
    private UserRepository userRepository;
    private ModelMapper modelMapper;
    @Autowired
    public CartItemSeriveImpl(CartItemRepository cartItemRepository,
                              CartRepository cartRepository,
                              ProductVariantRepository productVariantRepository,
                              UserRepository userRepository,
                              ModelMapper modelMapper) {
        this.cartItemRepository = cartItemRepository;
        this.cartRepository = cartRepository;
        this.productVariantRepository = productVariantRepository;
        this.userRepository = userRepository;
        this.modelMapper = modelMapper;
    }


    @Override
    public ResCartItemDto createCartItem(ReqCarItemDto reqCarItemDto) {
        Optional<ProductVariant> productVariantOptional = productVariantRepository
                .findById(reqCarItemDto.getProductVariantId());
        if(productVariantOptional.isEmpty()){
            throw new NoSuchCustomerExistsException("Product variant not found with id "+ reqCarItemDto.getProductVariantId());
        }
        Optional<Cart> cartOptional = cartRepository.findById(reqCarItemDto.getCartId());
        double priceCurrent = productVariantOptional.get().getCurrentPrice();
        Optional<CartItem> cartItemOptional = cartItemRepository
                .findByCartIdAndVariantId(reqCarItemDto.getCartId(), reqCarItemDto.getProductVariantId());

        if(cartItemOptional.isPresent()){
            cartItemOptional.get().setQuantity(reqCarItemDto.getQuantity() +1);
            cartItemOptional.get().setPriceAtTime(priceCurrent);
            CartItem saveCartItem =  cartItemRepository.save(cartItemOptional.get());
            return modelMapper.map(saveCartItem, ResCartItemDto.class);
        }
        else {
           CartItem cartItem = new CartItem();
            cartItem.setQuantity(1);
            cartItem.setPriceAtTime(priceCurrent);
            cartItem.setProductVariant(productVariantOptional.get());
            cartItem.setCart(cartOptional.get());
            double total = cartItem.getPriceAtTime() * cartItem.getQuantity();
            cartItem.setTotalPrice(total);
            CartItem saveCartItem = cartItemRepository.save(cartItem);
            return modelMapper.map(saveCartItem, ResCartItemDto.class);
        }
    }
    @Override
    public List<ResCartItemDto> getCartItemByUserId(Long userId) {
        Optional<User> userOptional = userRepository.findById(userId);
        if(userOptional.isEmpty()){
            throw new NoSuchCustomerExistsException("User not found with id " + userId);
        }
        Cart cart = cartRepository.findCartByUserId(userId);
        List<CartItem> cartItems = cartItemRepository.findCartItemByCart(cart);
        List<ResCartItemDto> cartItemDtoList = cartItems.stream()
                .map(cartItem -> modelMapper.map(cartItem, ResCartItemDto.class))
                .toList();
        return cartItemDtoList;
    }

    @Override
    public ResCartItemDto updateCartItemByQuantity(Long cartItemId, int newQuantity) {
        Optional<CartItem> cartItemOptional = cartItemRepository.findById(cartItemId);
        if(cartItemOptional.isEmpty()){
            throw new NoSuchCustomerExistsException("Cart item not found with id " + cartItemId);
        }
        cartItemOptional.get().setQuantity(newQuantity);
        CartItem saveCartItem = cartItemRepository.save(cartItemOptional.get());
        return modelMapper.map(saveCartItem, ResCartItemDto.class);
    }

    @Override
    public void deleteCartItemById(List<Long> cartItemIds, Long userId) {
        if (!userRepository.existsById(userId)) {
            throw new NoSuchCustomerExistsException("User not found with id " + userId);
        }
        Cart cart = cartRepository.findCartByUserId(userId);
        if(cart == null){
            throw new NoSuchCustomerExistsException("Cart not found with user id " + userId);
        }
        cartItemRepository.deleteSelectedItems(cartItemIds, cart.getCartId());

    }

    @Override
    public void deleteAllCartItem(Long userId) {
        if (!userRepository.existsById(userId)) {
            throw new NoSuchCustomerExistsException("User not found with id " + userId);
        }

        Cart cart = cartRepository.findCartByUserId(userId);
        if (cart != null) {
            cartItemRepository.deleteAllByCartId(cart.getCartId());
        }
    }

}
