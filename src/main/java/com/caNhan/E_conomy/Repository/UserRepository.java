package com.caNhan.E_conomy.Repository;

import com.caNhan.E_conomy.Dto.UserDto;
import com.caNhan.E_conomy.Entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
    Optional<User> findByEmail(String email);

}
