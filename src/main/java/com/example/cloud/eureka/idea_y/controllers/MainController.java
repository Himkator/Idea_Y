package com.example.cloud.eureka.idea_y.controllers;

import com.example.cloud.eureka.idea_y.dto.CompanyOfferDTO;
import com.example.cloud.eureka.idea_y.dto.PeopleOfferDTO;
import com.example.cloud.eureka.idea_y.entities.CompanyOffer;
import com.example.cloud.eureka.idea_y.services.OfferService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/main")
public class MainController {
    private final OfferService offerService;
    @GetMapping("/peopleOffers")
    public ResponseEntity<List<PeopleOfferDTO>> getAllPeopleOffer(){
        return ResponseEntity.ok(offerService.allPeopleOffers());
    }

    @GetMapping("/companyOffers")
    public ResponseEntity<List<CompanyOfferDTO>> getAllCompanyOffer(){
        return ResponseEntity.ok(offerService.allCompanyOffer());
    }

    @GetMapping("/peopleOffers/{id}")
    public ResponseEntity<PeopleOfferDTO> getPeopleOfferById(@PathVariable Long id){
        return ResponseEntity.ok(offerService.getPeopleOfferById(id));
    }

    @GetMapping("/companyOffers/{id}")
    public ResponseEntity<CompanyOfferDTO> getCompanyOfferById(@PathVariable Long id){
        return ResponseEntity.ok(offerService.getCompanyOfferById(id));
    }
}
