package com.example.cloud.eureka.idea_y.services;

import com.example.cloud.eureka.idea_y.dto.CompanyOfferDTO;
import com.example.cloud.eureka.idea_y.dto.OfferDTO;
import com.example.cloud.eureka.idea_y.dto.PeopleOfferDTO;
import com.example.cloud.eureka.idea_y.entities.CompanyOffer;
import com.example.cloud.eureka.idea_y.entities.PeopleOffer;
import com.example.cloud.eureka.idea_y.entities.User;
import com.example.cloud.eureka.idea_y.factories.CompanyOfferDTOFactory;
import com.example.cloud.eureka.idea_y.factories.PeopleOfferDTOFactory;
import com.example.cloud.eureka.idea_y.repositories.CompanyOfferRepository;
import com.example.cloud.eureka.idea_y.repositories.PeopleOfferRepository;
import com.example.cloud.eureka.idea_y.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;


import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
@RequiredArgsConstructor
public class OfferService {
    private final PeopleOfferRepository peopleOfferRepository;
    private final CompanyOfferRepository companyOfferRepository;
    private final UserRepository userRepository;
    private final PeopleOfferDTOFactory factoryPeople;
    private final JWTUtils jwtUtils;
    private final CompanyOfferDTOFactory factoryCompany;

    public List<PeopleOfferDTO> allPeopleOffers(){
        List<PeopleOffer> peopleOffers=peopleOfferRepository.findAllByOrderByCreatedAtDesc();
        return peopleOffers.stream()
                .map(factoryPeople::makePeopleOfferDTO)
                .collect(Collectors.toList());
    }

    public List<CompanyOfferDTO> allCompanyOffer(){
        List<CompanyOffer> companyOffers= companyOfferRepository.findAllByOrderByCreatedAtDesc();
        return companyOffers.stream()
                .map(factoryCompany::makeCompanyOfferDTO)
                .collect(Collectors.toList());
    }

    public PeopleOfferDTO createPeopleOffer(PeopleOfferDTO peopleOfferDTO){
        PeopleOfferDTO peopleOfferDTO1;
        if(!(peopleOfferDTO.getToken()+"").isBlank()){
            User user=userRepository.findByEmail(jwtUtils.extractUsername(peopleOfferDTO.getToken()));
            PeopleOffer peopleOffer=factoryPeople.makePeopleOfferFromDTO(peopleOfferDTO);
            user.addPeopleToUser(peopleOffer);
            peopleOfferRepository.saveAndFlush(peopleOffer);
            peopleOfferDTO1=factoryPeople.makePeopleOfferDTO(peopleOffer);
            peopleOfferDTO1.setStatusCode(200);
            peopleOfferDTO1.setMessage("People offer was created successfully");
        }else{
            peopleOfferDTO1=new PeopleOfferDTO();
            peopleOfferDTO1.setStatusCode(500);
            peopleOfferDTO1.setMessage("We got a problem");
        }
        return peopleOfferDTO1;
    }

    public CompanyOfferDTO createCompanyOffer(CompanyOfferDTO companyOfferDTO){
        CompanyOfferDTO companyOfferDTO1;
        if(!(companyOfferDTO.getToken()+"").isBlank()){
            User user=userRepository.findByEmail(jwtUtils.extractUsername(companyOfferDTO.getToken()));
            CompanyOffer companyOffer=factoryCompany.makeCompanyOfferFromDTO(companyOfferDTO);
            user.addComapnyToUser(companyOffer);
            companyOfferRepository.saveAndFlush(companyOffer);
            companyOfferDTO1=factoryCompany.makeCompanyOfferDTO(companyOffer);
            companyOfferDTO1.setStatusCode(200);
            companyOfferDTO1.setMessage("Company offer was created successfully");
        }else{
            companyOfferDTO1=new CompanyOfferDTO();
            companyOfferDTO1.setStatusCode(500);
            companyOfferDTO1.setMessage("We got a problem");
        }
        return companyOfferDTO1;
    }

    public PeopleOfferDTO getPeopleOfferById(Long id){
        return factoryPeople.makePeopleOfferDTO(peopleOfferRepository.findById(id).orElseThrow());
    }

    public CompanyOfferDTO getCompanyOfferById(Long id){
        return factoryCompany.makeCompanyOfferDTO(companyOfferRepository.findById(id).orElseThrow());
    }

    public PeopleOfferDTO updatePeopleOffer(PeopleOfferDTO peopleOfferDTO){
        PeopleOfferDTO peopleOfferDTO1;
        if((peopleOfferDTO.getToken()+"").isBlank()&&(peopleOfferDTO.getId()+"").isBlank()){
            peopleOfferDTO1=new PeopleOfferDTO();
            peopleOfferDTO1.setStatusCode(500);
            peopleOfferDTO1.setMessage("User id or Offer id is null");
            return peopleOfferDTO1;
        }
        User user=userRepository.findByEmail(jwtUtils.extractUsername(peopleOfferDTO.getToken()));
        PeopleOffer peopleOffer=peopleOfferRepository.findById(peopleOfferDTO.getId()).orElseThrow();
        if(!user.getPeopleOffers().contains(peopleOffer) && !user.getRole().equals("Admin")){
            peopleOfferDTO1=new PeopleOfferDTO();
            peopleOfferDTO1.setStatusCode(500);
            peopleOfferDTO1.setMessage("User hasnt these offer");
            return peopleOfferDTO1;
        }
        peopleOffer.setCVUrl(peopleOfferDTO.getCVUrl());
        peopleOffer.setLocation(peopleOfferDTO.getLocation());
        peopleOffer.setCategories(peopleOfferDTO.getCategories());
        peopleOffer.setPhotoUrl(peopleOfferDTO.getPhotoUrl());
        peopleOffer.setOccupation(peopleOfferDTO.getOccupation());
        peopleOffer.setBiography(peopleOfferDTO.getBiography());
        peopleOffer.setPhoneNumber(peopleOfferDTO.getPhoneNumber());
        peopleOffer.setTelegramUrl(peopleOfferDTO.getTelegramUrl());
        peopleOfferRepository.saveAndFlush(peopleOffer);
        peopleOfferDTO1=factoryPeople.makePeopleOfferDTO(peopleOffer);
        peopleOfferDTO1.setStatusCode(200);
        peopleOfferDTO1.setMessage("Offer was successfully updated");
        return peopleOfferDTO1;
    }

    public CompanyOfferDTO updateCompanyOffer(CompanyOfferDTO companyOfferDTO){
        CompanyOfferDTO companyOfferDTO1;
        if((companyOfferDTO.getId()+"").isBlank()&&(companyOfferDTO.getToken()+"").isBlank()){
            companyOfferDTO1=new CompanyOfferDTO();
            companyOfferDTO1.setStatusCode(500);
            companyOfferDTO1.setMessage("User id or Offer id is null");
            return companyOfferDTO1;
        }
        User user=userRepository.findByEmail(jwtUtils.extractUsername(companyOfferDTO.getToken()));
        CompanyOffer companyOffer=companyOfferRepository.findById(companyOfferDTO.getId()).orElseThrow();
        if(!user.getCompanyOffers().contains(companyOffer) && !user.getRole().equals("Admin")) {
            companyOfferDTO1=new CompanyOfferDTO();
            companyOfferDTO1.setStatusCode(500);
            companyOfferDTO1.setMessage("User hasn't this company offer");
            return companyOfferDTO1;
        }
        companyOffer.setCategories(companyOfferDTO.getCategories());
        companyOffer.setLocation(companyOfferDTO.getLocation());
        companyOffer.setPhotoUrl(companyOfferDTO.getPhotoUrl());
        companyOffer.setVideoUrl(companyOfferDTO.getVideoUrl());
        companyOffer.setFacebookUrl(companyOfferDTO.getFacebookUrl());
        companyOffer.setLinkedinUrl(companyOfferDTO.getLinkedinUrl());
        companyOffer.setProjectDescription(companyOfferDTO.getProjectDescription());
        companyOffer.setShortDescription(companyOfferDTO.getShortDescription());
        companyOffer.setTelegramUrl(companyOfferDTO.getTelegramUrl());
        companyOffer.setTitle(companyOfferDTO.getTitle());

        companyOfferRepository.saveAndFlush(companyOffer);
        companyOfferDTO1=factoryCompany.makeCompanyOfferDTO(companyOffer);
        companyOfferDTO1.setStatusCode(200);
        companyOfferDTO1.setMessage("Offer was successfully updated");
        return companyOfferDTO1;
    }

    public PeopleOfferDTO deletePeopleOffer(PeopleOfferDTO peopleOfferDTO){
        PeopleOfferDTO peopleOfferDTO1=new PeopleOfferDTO();
        if((peopleOfferDTO.getId()+"").isBlank()&&(peopleOfferDTO.getToken()+"").isBlank()){
            peopleOfferDTO1.setStatusCode(500);
            peopleOfferDTO1.setMessage("User token or offer id is null");
            return peopleOfferDTO1;
        }
        User user=userRepository.findByEmail(jwtUtils.extractUsername(peopleOfferDTO.getToken()));
        PeopleOffer peopleOffer=peopleOfferRepository.findById(peopleOfferDTO.getId()).orElseThrow();
        if(!user.getPeopleOffers().contains(peopleOffer) && !user.getRole().equals("Admin")){
            peopleOfferDTO1.setStatusCode(500);
            peopleOfferDTO1.setMessage("User hasn't this offer");
            return peopleOfferDTO1;
        }
        peopleOfferRepository.deleteById(peopleOffer.getId());
        peopleOfferDTO1.setStatusCode(200);
        peopleOfferDTO1.setMessage("Successfully deleted");
        return peopleOfferDTO1;
    }

    public CompanyOfferDTO deleteCompanyOffer(CompanyOfferDTO companyOfferDTO){
        CompanyOfferDTO companyOfferDTO1=new CompanyOfferDTO();
        if((companyOfferDTO.getId()+"").isBlank()&&(companyOfferDTO.getToken()+"").isBlank()){
            companyOfferDTO1.setStatusCode(500);
            companyOfferDTO1.setMessage("User id or Offer id is null");
            return companyOfferDTO1;
        }
        User user=userRepository.findByEmail(jwtUtils.extractUsername(companyOfferDTO.getToken()));
        CompanyOffer companyOffer=companyOfferRepository.findById(companyOfferDTO.getId()).orElseThrow();
        if(!user.getCompanyOffers().contains(companyOffer) && !user.getRole().equals("Admin")){
            companyOfferDTO1.setStatusCode(500);
            companyOfferDTO1.setMessage("User hasn't this company offer");
            return companyOfferDTO1;
        }
        companyOfferRepository.deleteById(companyOffer.getId());
        companyOfferDTO1.setStatusCode(200);
        companyOfferDTO1.setMessage("Successfully deleted");
        return companyOfferDTO1;
    }

    public List<PeopleOfferDTO> getAllPeopleOfferByUser(PeopleOfferDTO peopleOfferDTO){
        User user=userRepository.findByEmail(jwtUtils.extractUsername(peopleOfferDTO.getToken()));
        List<PeopleOffer> peopleOffers=peopleOfferRepository.findAllByUser(userRepository.findByEmail(jwtUtils.extractUsername(peopleOfferDTO.getToken())));
        return peopleOffers.stream().map(factoryPeople::makePeopleOfferDTO).collect(Collectors.toList());
    }

    public List<CompanyOfferDTO> getAllCompanyOfferByUser(CompanyOfferDTO companyOfferDTO){
        List<CompanyOffer> companyOffers=companyOfferRepository.findAllByUser(userRepository.findByEmail(jwtUtils.extractUsername(companyOfferDTO.getToken())));
        return companyOffers.stream()
                .map(factoryCompany::makeCompanyOfferDTO)
                .collect(Collectors.toList());
    }
}
