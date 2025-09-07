package com.caNhan.E_conomy.Controller;

//import com.caNhan.E_conomy.Entity.Cart;
//import com.caNhan.E_conomy.Service.CartService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.*;
//
//@RestController
//@RequestMapping("/cart")
//public class CartController {
//    private CartService cartService;
//    @Autowired
//    public CartController(CartService cartService) {
//        this.cartService = cartService;
//    }
//
//    @PostMapping("/{cartId}/add/{productId}")
//    public ResponseEntity<Cart> addToCart(@PathVariable int cartId, @PathVariable int productId) {
//        Cart updatedCart = cartService.saveCart(cartId, productId);
//        return ResponseEntity.ok(updatedCart);
//    }
//    @GetMapping("/{id}")
//    public Cart findCartById(@PathVariable int id){
//        return  cartService.findCartById(id);
//    }
//}
