package com.example.FirstProject.dto;


import jakarta.validation.constraints.Email;
import lombok.*;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class LoginRequestDTO {

    @NonNull
    @Email
    private  String email;

    @NonNull
    private  String password;
}
