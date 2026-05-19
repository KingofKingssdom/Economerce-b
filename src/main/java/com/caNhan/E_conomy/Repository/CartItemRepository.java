package com.caNhan.E_conomy.Repository;

import com.caNhan.E_conomy.Entity.*;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CartItemRepository extends JpaRepository<CartItem, Long> {
    @Query("SELECT c FROM CartItem c WHERE c.cart.cartId = :cartId AND c.productVariant.id = :productVariantId")
    Optional<CartItem> findByCartIdAndVariantId(
            @Param("cartId") Long cartId,
            @Param("productVariantId") Long productVariantId
    );
    List<CartItem> findCartItemByCart(Cart cart);
    @Query("DELETE FROM CartItem c WHERE c.cart.cartId = :cartId")
    void deleteAllByCartId(@Param("cartId") Long cartId);
    @Query("DELETE FROM CartItem c WHERE c.id IN :cartItemIds AND c.cart.cartId = :cartId")
    void deleteSelectedItems(@Param("cartItemIds") List<Long> cartItemIds, @Param("cartId") Long cartId);
}
