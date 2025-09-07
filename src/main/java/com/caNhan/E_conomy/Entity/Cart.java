//package com.caNhan.E_conomy.Entity;
//
//import com.fasterxml.jackson.annotation.JsonIgnore;
//import jakarta.persistence.*;
//
//import java.util.List;
//
//@Entity
//@Table(name = "carts")
//public class Cart {
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private int cartId;
//
//    @OneToMany(mappedBy = "cart", cascade = CascadeType.ALL)
//    @JsonIgnore
//   private List<CartItem> cartItems;
//
//    @OneToOne
//    @JoinColumn(name = "user_id", referencedColumnName = "id", unique = true)
//    private User user;
//    public int getCartId() {
//        return cartId;
//    }
//
//    public void setCartId(int cartId) {
//        this.cartId = cartId;
//    }
//
//    public List<CartItem> getCartItems() {
//        return cartItems;
//    }
//
//    public void setCartItems(List<CartItem> cartItems) {
//        this.cartItems = cartItems;
//    }
//
//    public User getUser() {
//        return user;
//    }
//
//    public void setUser(User user) {
//        this.user = user;
//        if (user != null && user.getCart() == null) {
//            user.setCart(this);
//        }
//    }
//}
