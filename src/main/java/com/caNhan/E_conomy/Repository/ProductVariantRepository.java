package com.caNhan.E_conomy.Repository;

import com.caNhan.E_conomy.Entity.ProductVariant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductVariantRepository extends JpaRepository<ProductVariant, Long> {
List<ProductVariant> findAllByProductId (long productId);
@Query("SELECT SUM(pv.stock) FROM ProductVariant pv ")
Long sumProductVariantByStock();
}
