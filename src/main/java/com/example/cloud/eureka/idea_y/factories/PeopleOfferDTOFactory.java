package com.example.cloud.eureka.idea_y.factories;

import com.example.cloud.eureka.idea_y.dto.PeopleOfferDTO;
import com.example.cloud.eureka.idea_y.entities.PeopleOffer;
import com.example.cloud.eureka.idea_y.entities.User;
import com.example.cloud.eureka.idea_y.repositories.UserRepository;
import com.example.cloud.eureka.idea_y.services.JWTUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class PeopleOfferDTOFactory {
    private final UserRepository userRepository;
    private final JWTUtils jwtUtils;
    public PeopleOfferDTO makePeopleOfferDTO(PeopleOffer peopleOffer){
        return PeopleOfferDTO.builder()
                .id(peopleOffer.getId())
                .location(peopleOffer.getLocation())
                .CVUrl(peopleOffer.getCVUrl())
                .email(peopleOffer.getUser().getEmail())
                .fullName(peopleOffer.getUser().getProfile().getFullName())
                .photoUrl(peopleOffer.getPhotoUrl())
                .phoneNumber(peopleOffer.getPhoneNumber())
                .telegramUrl(peopleOffer.getTelegramUrl())
                .biography(peopleOffer.getBiography())
                .categories(peopleOffer.getCategories())
                .occupation(peopleOffer.getOccupation())
                .userId(peopleOffer.getUser().getId())
                .id(peopleOffer.getId())
                .build();
    }

    public PeopleOffer makePeopleOfferFromDTO(PeopleOfferDTO peopleOfferDTO){
        User user=userRepository.findByEmail(jwtUtils.extractUsername(peopleOfferDTO.getToken()));
        return PeopleOffer.builder()
                .biography(peopleOfferDTO.getBiography())
                .categories(peopleOfferDTO.getCategories())
                .CVUrl(peopleOfferDTO.getCVUrl())
                .photoUrl(peopleOfferDTO.getPhotoUrl())
                .user(user)
                .location(peopleOfferDTO.getLocation())
                .telegramUrl(peopleOfferDTO.getTelegramUrl())
                .phoneNumber(peopleOfferDTO.getPhoneNumber())
                .occupation(peopleOfferDTO.getOccupation())
                .build();
    }
}
