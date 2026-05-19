package com.caNhan.E_conomy.Repository;


import com.caNhan.E_conomy.Entity.Order;
import com.caNhan.E_conomy.Response.Enum.OrderStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<Order,Long> {
    @Query("SELECT COUNT(o.status) FROM Order o WHERE o.status =:orderStatus")
    Long countAllOrderStatus(@Param("orderStatus") OrderStatus orderStatus);
    @Query("SELECT o FROM Order o WHERE o.user.id = :userId")
    List<Order> findByUserId(@Param("userId") Long userId);
    @Query("SELECT SUM(o.totalPrice) FROM Order o WHERE o.status=:orderStatus")
    Long sumPriceAllOrder(@Param("orderStatus") OrderStatus orderStatus);
}
