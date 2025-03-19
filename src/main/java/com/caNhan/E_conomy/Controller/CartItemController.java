package com.caNhan.E_conomy.Controller;

import com.caNhan.E_conomy.Entity.CartItem;
import com.caNhan.E_conomy.Service.CartItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/cartItem")
public class CartItemController {
    private CartItemService cartItemService;
    @Autowired
    public CartItemController(CartItemService cartItemService) {
        this.cartItemService = cartItemService;
    }
    @GetMapping("/cart")
    public ResponseEntity<?> getCartItemByCartId(@RequestParam int cartId){
      List<CartItem> cartItems = cartItemService.findCartItemByCartId(cartId);
         return ResponseEntity.ok(cartItems);
    }
    @GetMapping("/count/{id}")
    public ResponseEntity<?> countCartItem(@PathVariable int id) {
        Long cartItem = cartItemService.CountCartItem(id);
        return ResponseEntity.ok(cartItem);
    }
    @DeleteMapping("/delete/{id}")
    public void deleteCartItem(@PathVariable int id){
         cartItemService.deleteCartItemById(id);
    }
}
