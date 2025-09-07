//package com.caNhan.E_conomy.Repository;
//
//import com.caNhan.E_conomy.Entity.OrderItem;
//import org.springframework.data.jpa.repository.JpaRepository;
//import org.springframework.data.jpa.repository.Query;
//import org.springframework.stereotype.Repository;
//
//import java.util.List;
//
//@Repository
//public interface OrderItemRepository extends JpaRepository<OrderItem,Integer> {
//    @Query(value = "SELECT  " +
//            "order_item.id, " +
//            "order_item.quantity, " +
//            "order_item.product_id, " +
//            "order_item.order_id, " +
//            "products.id as product_db_id, " +
//                    "products.product_name, " +
//                    "products.price_current " +
//                    "FROM order_item " +
//                    "JOIN products ON products.id = order_item.product_id " +
//                    "WHERE order_item.order_id = ?", nativeQuery = true)
//    List<OrderItem> findByOrderId(int orderId);
//}
