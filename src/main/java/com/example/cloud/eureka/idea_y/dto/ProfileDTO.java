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
public class ProfileDTO {
    private int statusCode;
    private String message;
//    private String token;
    private Long id;
    private String email;
    private String fullName;
    private String photoUrl;
    private String telegramUrl;
    private String telephoneNumber;
}
