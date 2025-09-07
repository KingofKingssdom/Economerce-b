//package com.caNhan.E_conomy.Service;
//
//import com.caNhan.E_conomy.Entity.Cart;
//import com.caNhan.E_conomy.Entity.CartItem;
//import com.caNhan.E_conomy.Entity.Product;
//import com.caNhan.E_conomy.Repository.CartItemRepository;
//import com.caNhan.E_conomy.Repository.CartRepository;
//import com.caNhan.E_conomy.Repository.ProductRepository;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//
//import java.util.List;
//import java.util.Objects;
//import java.util.Optional;
//
//@Service
//public class CartService {
//    private CartRepository cartRepository;
//    private ProductRepository productRepository;
//    private CartItemRepository cartItemRepository;
//    @Autowired
//    public CartService(CartRepository cartRepository, ProductRepository productRepository, CartItemRepository cartItemRepository) {
//        this.cartRepository = cartRepository;
//        this.productRepository = productRepository;
//        this.cartItemRepository = cartItemRepository;
//    }
//
//    public Cart saveCart (int cartId, int productId) {
//        Cart cart = cartRepository.findById(cartId).orElseThrow(() -> new RuntimeException("Cart not found"));
//        Product product = productRepository.findById(productId).orElseThrow(() -> new RuntimeException("Product not found"));
//
//        // Tìm sản phẩm trong giỏ hàng
//        Optional<CartItem> existingItem = cart.getCartItems()
//                .stream()
//                .filter(item -> item.getProduct().getId() == productId)
//                .findFirst();
//
//        if (existingItem.isPresent()) {
//            // Nếu sản phẩm đã có trong giỏ, tăng số lượng
//            CartItem cartItem = existingItem.get();
//            cartItem.setQuantity(cartItem.getQuantity() + 1);
//        } else {
//            // Nếu chưa có, tạo mới CartItem
//            CartItem newItem = new CartItem();
//            newItem.setCart(cart);
//            newItem.setProduct(product);
//            newItem.setQuantity(1);
//            cart.getCartItems().add(newItem);
//        }
//
//        return cartRepository.save(cart);
//    }
//
//    public Cart  findCartById(int id){
//        return cartRepository.findById(id).orElseThrow();
//    }
//}
