package com.example.FirstProject.dto;

import com.example.FirstProject.model.enums.Gender;
import lombok.*;

import java.time.LocalDate;



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

    private AccountDTO account;

}
