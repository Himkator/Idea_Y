package com.example.cloud.eureka.idea_y.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity(name="company_offers")
public class CompanyOffer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    private String location;
    private String categories;
    private String photoUrl;
    private String videoUrl;
    private String telegramUrl;
    private String linkedinUrl;
    private String facebookUrl;
    private String email;
    private String shortDescription;
    private String projectDescription;
    @ManyToOne
    @JoinColumn
    private User user;
    private LocalDateTime createdAt;

    @PrePersist
    private void init(){
        createdAt=LocalDateTime.now();
    }
}
