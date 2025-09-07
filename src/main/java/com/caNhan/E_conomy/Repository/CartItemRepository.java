//package com.caNhan.E_conomy.Repository;
//
//import com.caNhan.E_conomy.Entity.CartItem;
//import jakarta.transaction.Transactional;
//import org.springframework.data.jpa.repository.JpaRepository;
//import org.springframework.data.jpa.repository.Modifying;
//import org.springframework.data.jpa.repository.Query;
//import org.springframework.data.repository.query.Param;
//import org.springframework.stereotype.Repository;
//
//import java.util.List;
//
//@Repository
//public interface CartItemRepository extends JpaRepository<CartItem, Integer> {
//    @Query(value = "SELECT  " +
//            "cart_items.id, " +
//            "cart_items.quantity, " +
//            "cart_items.product_id, " +
//            "cart_items.cart_id, " +
//            "products.id as product_db_id, " +
//            "products.product_name, " +
//            "products.price_current " +
//            "FROM cart_items " +
//            "JOIN products ON products.id = cart_items.product_id " +
//            "WHERE cart_id = ?", nativeQuery = true)
//     List<CartItem> findByCartId(int cartId);
//
//    @Query(value = "SELECT SUM(quantity) FROM cart_items WHERE cart_id = ?", nativeQuery = true)
//    Long findBySumQuantity (int id);
//
//    @Modifying
//    @Transactional
//    @Query( value = "DELETE FROM cart_items  WHERE cart_id = ?", nativeQuery = true)
//    void deleteByCartId( int cartId);
//}
