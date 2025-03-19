package com.caNhan.E_conomy.Repository;

import com.caNhan.E_conomy.Entity.Brand;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BrandRepository extends JpaRepository<Brand,Integer> {
    List<Brand> findByCategories_Id(Integer categoryId);
}
