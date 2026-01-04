package com.caNhan.E_conomy.Repository;

import com.caNhan.E_conomy.Dto.ResponseDto.OrderCountStatusResponseDTO;
import com.caNhan.E_conomy.Entity.Order;
import com.caNhan.E_conomy.Entity.User;
import com.caNhan.E_conomy.Response.Enum.OrderStatus;
import org.springframework.data.domain.Limit;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<Order,Long> {
    List<Order> findByUser_Id (Long userId);
    @Query("SELECT o FROM Order o JOIN FETCH o.user")
    List<Order> findAllOrder();

    @Query("SELECT o FROM Order o WHERE o.status = :status")
    List<Order> findOrderByStatus(@Param("status") OrderStatus status);

    @Query("SELECT new com.caNhan.E_conomy.Dto.ResponseDto.OrderCountStatusResponseDTO(o.status, COUNT (o)) FROM Order o GROUP BY o.status")
    List<OrderCountStatusResponseDTO> countByStatus ();
}
