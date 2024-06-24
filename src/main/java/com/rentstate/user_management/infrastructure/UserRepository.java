package com.rentstate.user_management.infrastructure;

import com.rentstate.user_management.domain.model.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {

    Boolean existsByUsername(String username);
    User findByUsername(String username);
}
