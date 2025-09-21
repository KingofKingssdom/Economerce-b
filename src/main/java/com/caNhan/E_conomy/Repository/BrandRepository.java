package com.caNhan.E_conomy.Repository;

import com.caNhan.E_conomy.Entity.Brand;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface BrandRepository extends JpaRepository<Brand,Long> {
    Optional<Brand> findByBrandName(String brandName);
    @Query(value = "SELECT b" +
            " FROM Brand b" +
            " JOIN b.categories c" +
            " WHERE c.id = :categoryId")
    List<Brand> findBrandByCategories (@Param("categoryId") Long categoryId);
}
