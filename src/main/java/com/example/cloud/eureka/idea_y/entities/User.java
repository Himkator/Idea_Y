package com.example.cloud.eureka.idea_y.entities;

import jakarta.persistence.*;
import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Entity(name="idea_users")
@Data
public class User implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String email;
    @Column(length = 2550000)
    private String password;
    private String role;
    @OneToOne(mappedBy = "user")
    private Profile profile;
    @OneToMany(mappedBy = "user")
    private List<CompanyOffer> companyOffers=new ArrayList<>();
    @OneToMany(mappedBy = "user")
    private List<PeopleOffer> peopleOffers=new ArrayList<>();

    public void addPeopleToUser(PeopleOffer peopleOffer) {
        peopleOffer.setUser(this);
        peopleOffers.add(peopleOffer);
    }
    public void addComapnyToUser(CompanyOffer companyOffer) {
        companyOffer.setUser(this);
        companyOffers.add(companyOffer);
    }

    @Override
    public String toString() {
        return "User{" +
                "name='" + email + '\'' +
                ", profile=" + (profile != null ? profile.getFullName() : "null") +
                '}';
    }


    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority(role));
    }

    @Override
    public String getUsername() {
        return email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
