package com.example.cloud.eureka.idea_y.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity(name="user_profile")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Profile {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String fullName;
    private String photoUrl;
    private String telegramUrl;
    private String telephoneNumber;

    @OneToOne
    @JoinColumn
    private User user;

    @Override
    public String toString() {
        return "Profile{" +
                "fullName='" + fullName + '\'' +
                // Предположим, что вы хотите избежать вызова user.toString()
                ", user=" + (user != null ? user.getEmail() : "null") +
                '}';
    }
}
