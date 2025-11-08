package com.caNhan.E_conomy.Service.Impl;

import com.caNhan.E_conomy.Entity.Cart;
import com.caNhan.E_conomy.GlobalExeption.Exception.NoSuchCustomerExistsException;
import com.caNhan.E_conomy.Repository.CartRepository;
import com.caNhan.E_conomy.Service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CartServiceImpl implements CartService {
    private CartRepository cartRepository;
    @Autowired
    public CartServiceImpl(CartRepository cartRepository) {
        this.cartRepository = cartRepository;
    }

    @Override
    public Cart findCartByUser(Long UserId) {
        Cart cart = cartRepository.findCartByUserId(UserId);
        if (cart.getUser() == null) {
            throw new NoSuchCustomerExistsException("Không tìm thấy giỏ hàng với user = " + UserId);
        }
        return cart;
    }
}
