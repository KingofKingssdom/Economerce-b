package com.caNhan.E_conomy.Repository;

import com.caNhan.E_conomy.Entity.ProductSpecification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ProductSpecificationRepository extends JpaRepository<ProductSpecification, Long> {
    Optional<ProductSpecification> findByNameSpecification(String nameSpecification);
}
