package com.caNhan.E_conomy.Repository;

import com.caNhan.E_conomy.Dto.ResponseDto.RoleResponseDTO;
import com.caNhan.E_conomy.Dto.RoleDTO;
import com.caNhan.E_conomy.Entity.Roles;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.management.relation.Role;
import java.util.List;
import java.util.Optional;

@Repository
public interface RoleRepository extends JpaRepository<Roles, Long> {
    Optional<Roles> findRolesByRoleName (String roleName);
}
