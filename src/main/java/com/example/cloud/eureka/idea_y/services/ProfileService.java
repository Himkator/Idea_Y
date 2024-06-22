package com.example.cloud.eureka.idea_y.services;

import com.example.cloud.eureka.idea_y.dto.ProfileDTO;
import com.example.cloud.eureka.idea_y.entities.Profile;
import com.example.cloud.eureka.idea_y.factories.ProfileDTOFactory;
import com.example.cloud.eureka.idea_y.repositories.ProfileRepository;
import com.example.cloud.eureka.idea_y.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ProfileService {
    private final ProfileRepository profileRepository;
    private final UserRepository userRepository;
    private final JWTUtils jwtUtils;
    private final ProfileDTOFactory factoryProfile;

    public ProfileDTO getProfile(ProfileDTO profileDTO){
        if((profileDTO.getToken()+"").isBlank()) {
            ProfileDTO profileDTO1=new ProfileDTO();
            profileDTO1.setStatusCode(500);
            profileDTO1.setMessage("User id or id is null");
            return profileDTO1;
        }
        if(!userRepository.existsByEmail(jwtUtils.extractUsername(profileDTO.getToken()))){
            ProfileDTO profileDTO1=new ProfileDTO();
            profileDTO1.setStatusCode(500);
            profileDTO1.setMessage("User or profile is null");
            return profileDTO1;
        }
        ProfileDTO profileDTO1=factoryProfile.makeProfileDTO(profileRepository.findByUser(userRepository.findByEmail(jwtUtils.extractUsername(profileDTO.getToken()))));
        profileDTO1.setStatusCode(200);
        profileDTO1.setMessage("Profile");

        return profileDTO1;
    }

    public ProfileDTO updateProfile(ProfileDTO profileDTO){
        if((profileDTO.getToken()+"").isBlank() && (profileDTO.getId()+"").isBlank()){
            ProfileDTO profileDTO1=new ProfileDTO();
            profileDTO1.setStatusCode(500);
            profileDTO1.setMessage("User id or id is null");
            return profileDTO1;
        }
        Profile profile=profileRepository.findByUser(userRepository.findByEmail(jwtUtils.extractUsername(profileDTO.getToken())));
        profile.setFullName(profileDTO.getFullName());
        profile.setPhotoUrl(profileDTO.getPhotoUrl());
        profile.setTelegramUrl(profileDTO.getTelegramUrl());
        profile.setTelephoneNumber(profileDTO.getTelephoneNumber());
        profileRepository.saveAndFlush(profile);
        ProfileDTO profileDTO1=factoryProfile.makeProfileDTO(profile);
        profileDTO1.setStatusCode(200);
        profileDTO1.setMessage("Profile was successfully updated");
        return profileDTO1;
    }
}
