package com.example.FirstProject.dto;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

@AllArgsConstructor

@NoArgsConstructor
@Component
@Builder
@Data
public class LoginResponseDTO {

    private  String token;
    private  String tokenType;
    private  HttpStatus status;
    private  String  message;


}
