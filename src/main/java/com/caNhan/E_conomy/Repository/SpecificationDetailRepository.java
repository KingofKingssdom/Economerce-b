package com.caNhan.E_conomy.Repository;

import com.caNhan.E_conomy.Entity.SpecificationDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SpecificationDetailRepository extends JpaRepository<SpecificationDetail, Long> {
}
