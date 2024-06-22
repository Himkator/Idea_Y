package com.example.cloud.eureka.idea_y.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties
@JsonInclude(JsonInclude.Include.NON_NULL)
public class PeopleOfferDTO {
    private int statusCode;
    private String message;
    private String token;
    private Long id;
    private Long userId;
    private String email;
    private String fullName;
    private String location;
    private String photoUrl;
    private String CVUrl;
    private String biography;
    private String occupation;
    private String categories;
    private String telegramUrl;
    private String phoneNumber;
}
