package com.example.cloud.eureka.idea_y.factories;

import com.example.cloud.eureka.idea_y.configs.JWTAuthFilter;
import com.example.cloud.eureka.idea_y.dto.CompanyOfferDTO;
import com.example.cloud.eureka.idea_y.entities.CompanyOffer;
import com.example.cloud.eureka.idea_y.entities.User;
import com.example.cloud.eureka.idea_y.repositories.CompanyOfferRepository;
import com.example.cloud.eureka.idea_y.repositories.UserRepository;
import com.example.cloud.eureka.idea_y.services.JWTUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class CompanyOfferDTOFactory {
    private final CompanyOfferRepository companyOfferRepository;
    private final JWTUtils jwtUtils;
    private final UserRepository userRepository;

    public CompanyOfferDTO makeCompanyOfferDTO(CompanyOffer companyOffer){
        return CompanyOfferDTO.builder()
                .id(companyOffer.getId())
                .email(companyOffer.getEmail())
                .photoUrl(companyOffer.getPhotoUrl())
                .linkedinUrl(companyOffer.getLinkedinUrl())
                .facebookUrl(companyOffer.getFacebookUrl())
                .title(companyOffer.getTitle())
                .categories(companyOffer.getCategories())
                .telegramUrl(companyOffer.getTelegramUrl())
                .location(companyOffer.getLocation())
                .shortDescription(companyOffer.getShortDescription())
                .videoUrl(companyOffer.getVideoUrl())
                .projectDescription(companyOffer.getProjectDescription())
                .build();
    }

    public CompanyOffer makeCompanyOfferFromDTO(CompanyOfferDTO companyOfferDTO){
        User user=userRepository.findByEmail(jwtUtils.extractUsername(JWTAuthFilter.jwt));
        return CompanyOffer.builder()
                .user(user)
                .linkedinUrl(companyOfferDTO.getLinkedinUrl())
                .title(companyOfferDTO.getTitle())
                .videoUrl(companyOfferDTO.getVideoUrl())
                .location(companyOfferDTO.getLocation())
                .photoUrl(companyOfferDTO.getPhotoUrl())
                .projectDescription(companyOfferDTO.getProjectDescription())
                .shortDescription(companyOfferDTO.getShortDescription())
                .telegramUrl(companyOfferDTO.getTelegramUrl())
                .facebookUrl(companyOfferDTO.getFacebookUrl())
                .categories(companyOfferDTO.getCategories())
                .email(companyOfferDTO.getEmail())
                .build();
    }
}
