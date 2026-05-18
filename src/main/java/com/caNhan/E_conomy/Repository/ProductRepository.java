package com.caNhan.E_conomy.Repository;

import com.caNhan.E_conomy.Entity.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;


import java.util.List;
import java.util.Optional;

@Repository
public interface ProductRepository extends JpaRepository<Product,Long> {
    Optional<Product> findAllByProductCode (String productCode);
    Page<Product> findAll (Pageable pageable);
    List<Product> findByCategoryId (long categoryId);
    @Query(value = "SELECT p" +
            " FROM Product p" +
            " WHERE p.category.id = :categoryId" +
            " AND p.brand.id = :brandId")
    List<Product> findByCategoryAndBrand(@Param("categoryId") Long categoryId,
                                         @Param("brandId") Long BrandId);
    @Query("SELECT p FROM Product p" +
    " WHERE p.promotional = :promotional AND p.category.id = :categoryId"
    )
    List<Product> findAllByPromotionalAndCategory (@Param("promotional") boolean promotional,
                                                   @Param("categoryId") Long categoryId);
    List<Product> findAllByFeatured (boolean featured);
    List<Product> findAllByPromotional (boolean promotional);
    @Query("SELECT p FROM Product p " +
            "WHERE LOWER(p.productName) LIKE LOWER(CONCAT('%', :productName, '%'))")
    List<Product> findAllByProductName(@Param("productName") String productName);
}
