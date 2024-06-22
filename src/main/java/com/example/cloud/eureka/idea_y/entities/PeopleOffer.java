package com.example.cloud.eureka.idea_y.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.engine.internal.Cascade;

import java.time.LocalDateTime;

@Entity(name="people_offers")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PeopleOffer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String location;
    private String photoUrl;
    private String CVUrl;
    private String biography;
    private String occupation;
    private String categories;
    private String telegramUrl;
    private String phoneNumber;

    @ManyToOne
    @JoinColumn
    private User user;
    private LocalDateTime createdAt;

    @PrePersist
    private void init(){
        createdAt=LocalDateTime.now();
    }
}
