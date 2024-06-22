package com.example.cloud.eureka.idea_y.controllers;

import com.example.cloud.eureka.idea_y.dto.CompanyOfferDTO;
import com.example.cloud.eureka.idea_y.dto.OfferDTO;
import com.example.cloud.eureka.idea_y.dto.PeopleOfferDTO;
import com.example.cloud.eureka.idea_y.entities.CompanyOffer;
import com.example.cloud.eureka.idea_y.entities.PeopleOffer;
import com.example.cloud.eureka.idea_y.services.OfferService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/user/")
public class OfferController {
    private final OfferService offerService;


    @PostMapping("/create/peopleOffer")
    public ResponseEntity<PeopleOfferDTO> createPeopleOffer(@RequestBody PeopleOfferDTO peopleOfferDTO){
        return ResponseEntity.ok(offerService.createPeopleOffer(peopleOfferDTO));
    }

    @PostMapping("/create/companyOffer")
    public ResponseEntity<CompanyOfferDTO> createCompanyOffer(@RequestBody CompanyOfferDTO companyOfferDTO){
        return ResponseEntity.ok(offerService.createCompanyOffer(companyOfferDTO));
    }

    @PutMapping("/update/peopleOffer")
    public ResponseEntity<PeopleOfferDTO> updatePeopleOffer(@RequestBody PeopleOfferDTO peopleOfferDTO){
        return ResponseEntity.ok(offerService.updatePeopleOffer(peopleOfferDTO));
    }

    @PutMapping("/update/companyOffer")
    public ResponseEntity<CompanyOfferDTO> updateCompanyOffer(@RequestBody CompanyOfferDTO companyOfferDTO){
        return ResponseEntity.ok(offerService.updateCompanyOffer(companyOfferDTO));
    }

    @DeleteMapping("/delete/peopleOffer")
    public ResponseEntity<PeopleOfferDTO> deletePeopleOffer(@RequestBody PeopleOfferDTO peopleOfferDTO){
        return ResponseEntity.ok(offerService.deletePeopleOffer(peopleOfferDTO));
    }

    @DeleteMapping("/delete/companyOffer")
    public ResponseEntity<CompanyOfferDTO> deleteCompanyOffer(@RequestBody CompanyOfferDTO companyOfferDTO){
        return ResponseEntity.ok(offerService.deleteCompanyOffer(companyOfferDTO));
    }
}
