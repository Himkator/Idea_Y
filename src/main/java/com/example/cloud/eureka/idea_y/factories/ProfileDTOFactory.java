package com.example.cloud.eureka.idea_y.factories;

import com.example.cloud.eureka.idea_y.configs.JWTAuthFilter;
import com.example.cloud.eureka.idea_y.dto.ProfileDTO;
import com.example.cloud.eureka.idea_y.entities.Profile;
import com.example.cloud.eureka.idea_y.entities.User;
import com.example.cloud.eureka.idea_y.repositories.ProfileRepository;
import com.example.cloud.eureka.idea_y.repositories.UserRepository;
import com.example.cloud.eureka.idea_y.services.JWTUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ProfileDTOFactory {
    private final ProfileRepository profileRepository;
    private final UserRepository userRepository;
    private final JWTUtils jwtUtils;

    public ProfileDTO makeProfileDTO(Profile profile){
        return ProfileDTO.builder()
                .email(profile.getUser().getEmail())
                .id(profile.getId())
                .fullName(profile.getFullName())
                .telegramUrl(profile.getTelegramUrl())
                .photoUrl(profile.getPhotoUrl())
                .telephoneNumber(profile.getTelephoneNumber())
                .build();
    }

    public Profile makeProfileFromDTO(ProfileDTO profileDTO){
        User user=userRepository.findByEmail(jwtUtils.extractUsername(JWTAuthFilter.jwt));
        return Profile.builder()
                .user(user)
                .fullName(profileDTO.getFullName())
                .telephoneNumber(profileDTO.getTelephoneNumber())
                .photoUrl(profileDTO.getPhotoUrl())
                .telegramUrl(profileDTO.getTelegramUrl())
                .build();
    }
}
