package com.example.cloud.eureka.idea_y.repositories;

import com.example.cloud.eureka.idea_y.entities.CompanyOffer;
import com.example.cloud.eureka.idea_y.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CompanyOfferRepository extends JpaRepository<CompanyOffer, Long> {
    List<CompanyOffer> findAllByOrderByCreatedAtDesc();
    List<CompanyOffer> findAllByUser(User user);
}
