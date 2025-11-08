package com.caNhan.E_conomy.Repository;

import com.caNhan.E_conomy.Entity.OrderItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderItemRepository extends JpaRepository<OrderItem,Long> {
    List<OrderItem> findOrderItemByOrder_Id (Long orderId);
}
