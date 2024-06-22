package com.example.cloud.eureka.idea_y.repositories;

import com.example.cloud.eureka.idea_y.entities.PeopleOffer;
import com.example.cloud.eureka.idea_y.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PeopleOfferRepository extends JpaRepository<PeopleOffer, Long> {
    List<PeopleOffer> findAllByOrderByCreatedAtDesc();
    List<PeopleOffer> findAllByUser(User user);
}
