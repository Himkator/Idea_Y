package com.example.cloud.eureka.idea_y.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

@Data
@JsonIgnoreProperties
@JsonInclude(JsonInclude.Include.NON_NULL)
public class OfferDTO {
    private int statusCode;
    private String message;
    private Long userId;
    private Long offerId;
    private String title;
    private String location;
    private String photoUrl;
    private String videoUrl;
    private String CVUrl;
    private String biography;
    private String occupation;
    private String categories;
    private String telegramUrl;
    private String linkedinUrl;
    private String facebookUrl;
    private String phoneNumber;
    private String fullName;
    private String shortDescription;
    private String projectDescroption;
}
