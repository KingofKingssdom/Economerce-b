package com.caNhan.E_conomy.Service;

import com.caNhan.E_conomy.Entity.Cart;

public interface CartService {
    Cart findCartByUser(Long UserId);
}
