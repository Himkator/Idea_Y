package com.example.cloud.eureka.idea_y.repositories;

import com.example.cloud.eureka.idea_y.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
    User findByEmail(String username);
    boolean existsByEmail(String email);
}
