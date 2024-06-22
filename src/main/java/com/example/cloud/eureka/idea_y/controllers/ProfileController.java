package com.example.cloud.eureka.idea_y.controllers;

import com.example.cloud.eureka.idea_y.dto.CompanyOfferDTO;
import com.example.cloud.eureka.idea_y.dto.OfferDTO;
import com.example.cloud.eureka.idea_y.dto.PeopleOfferDTO;
import com.example.cloud.eureka.idea_y.dto.ProfileDTO;
import com.example.cloud.eureka.idea_y.entities.CompanyOffer;
import com.example.cloud.eureka.idea_y.entities.PeopleOffer;
import com.example.cloud.eureka.idea_y.entities.Profile;
import com.example.cloud.eureka.idea_y.services.OfferService;
import com.example.cloud.eureka.idea_y.services.ProfileService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/profile")
public class ProfileController {

    private final ProfileService profileService;
    private final OfferService offerService;

    @GetMapping
    public ResponseEntity<ProfileDTO> getProfile(@RequestBody ProfileDTO profileDTO){
        return ResponseEntity.ok(profileService.getProfile(profileDTO));
    }

    @PutMapping("/change")
    public ResponseEntity<ProfileDTO> updateProfile(@RequestBody ProfileDTO profileDTO){
        return ResponseEntity.ok(profileService.updateProfile(profileDTO));
    }

    @GetMapping("/peopleOffers")
    public ResponseEntity<List<PeopleOfferDTO>> getAllPeopleOffersByUser(@RequestBody PeopleOfferDTO peopleOfferDTO){
        return ResponseEntity.ok(offerService.getAllPeopleOfferByUser(peopleOfferDTO));
    }

    @GetMapping("/companyOffers")
    public ResponseEntity<List<CompanyOfferDTO>> getAllCompanyOffersByUser(@RequestBody CompanyOfferDTO companyOfferDTO){
        return ResponseEntity.ok(offerService.getAllCompanyOfferByUser(companyOfferDTO));
    }

}
