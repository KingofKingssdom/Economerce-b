package com.caNhan.E_conomy.Controller;

import com.caNhan.E_conomy.Custom.CustomUserDetail;
import com.caNhan.E_conomy.Entity.Cart;
import com.caNhan.E_conomy.Response.ResponseData;
import com.caNhan.E_conomy.Service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/cart")
public class CartController {
    private CartService cartService;
    @Autowired
    public CartController(CartService cartService) {
        this.cartService = cartService;
    }

//    @PostMapping("/{cartId}/add/{productId}")
//    public ResponseEntity<Cart> addToCart(@PathVariable int cartId, @PathVariable int productId) {
//        Cart updatedCart = cartService.saveCart(cartId, productId);
//        return ResponseEntity.ok(updatedCart);
//    }
    @GetMapping()
    private ResponseEntity<?> findCartByUserId(){
        SecurityContext securityContext = SecurityContextHolder.getContext();
        Authentication authentication = securityContext.getAuthentication();
        CustomUserDetail customUserDetail = (CustomUserDetail) authentication.getPrincipal();
        Long userId = customUserDetail.getId();
        Cart cart = cartService.findCartByUser(userId);
        ResponseData responseData = new ResponseData(
                HttpStatus.OK.value(),
                "Lấy giỏ hàng theo user thành công",
                cart
        );
        return ResponseEntity.ok(responseData);
    }
}
