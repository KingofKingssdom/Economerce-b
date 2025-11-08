package com.caNhan.E_conomy.Repository;

import com.caNhan.E_conomy.Entity.Order;
import com.caNhan.E_conomy.Entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<Order,Long> {
    List<Order> findByUser_Id (Long userId);
    @Query("SELECT o FROM Order o JOIN FETCH o.user")
    List<Order> findAllOrder();
}
