package com.example.cloud.eureka.idea_y.repositories;

import com.example.cloud.eureka.idea_y.entities.Profile;
import com.example.cloud.eureka.idea_y.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProfileRepository extends JpaRepository<Profile, Long> {
    Profile findByUser(User user);
    boolean existsById(Long id);
}
