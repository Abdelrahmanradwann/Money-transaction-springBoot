package com.example.FirstProject.dto;

import com.example.FirstProject.model.enums.Gender;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.Column;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.time.LocalDate;
import java.util.UUID;


@AllArgsConstructor
@Data
@Builder
public class CustomerDTO {
    @NonNull
    private final String email;
    @NonNull
    private final String password;
    @NonNull
    private  final String fname;
    @NonNull
    private  final String lname;
    @NonNull
    private final LocalDate  DOB;
    @NonNull
    private final String phoneNumber;
    @NonNull
    private final String nationality;
    @NonNull
    private final String nationalId;
    @NonNull
    private final String address;
    @NonNull
    private final Gender gender;

}
