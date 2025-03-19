package com.caNhan.E_conomy.Service;

import com.caNhan.E_conomy.Entity.Cart;
import com.caNhan.E_conomy.Entity.CartItem;
import com.caNhan.E_conomy.Repository.CartItemRepository;
import com.caNhan.E_conomy.Repository.CartRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CartItemService {
    private CartItemRepository cartItemRepository;
    private CartRepository cartRepository;
    @Autowired
    public CartItemService(CartItemRepository cartItemRepository,CartRepository cartRepository) {
        this.cartItemRepository = cartItemRepository;
        this.cartRepository = cartRepository;
    }
    public List<CartItem> findCartItemByCartId (int cartId){
        return cartItemRepository.findByCartId(cartId);
    }
    public Long CountCartItem(int id) {
        return cartItemRepository.findBySumQuantity(id);
    }

    public void deleteCartItemById(int cartItemId){
       CartItem cartItem = cartItemRepository.findById(cartItemId).orElseThrow();
       if (cartItem != null){
          cartItemRepository.delete(cartItem);
       }
    }
    public void deleteByCartId(int cartId){

    }
}
