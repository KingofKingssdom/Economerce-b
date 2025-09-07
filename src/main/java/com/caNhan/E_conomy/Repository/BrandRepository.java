package com.caNhan.E_conomy.Repository;

import com.caNhan.E_conomy.Entity.Brand;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface BrandRepository extends JpaRepository<Brand,Integer> {
    Optional<Brand> findByBrandName(String brandName);
}
