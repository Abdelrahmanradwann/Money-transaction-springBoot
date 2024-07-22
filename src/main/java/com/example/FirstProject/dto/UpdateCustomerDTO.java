package com.example.FirstProject.dto;

import lombok.Data;
import lombok.NonNull;

import java.time.LocalDate;


@Data
public class UpdateCustomerDTO {
    @NonNull
    private String password;
    @NonNull
    private  String fname;
    @NonNull
    private  String lname;
    @NonNull
    private LocalDate  DOB;
    @NonNull
    private  String nationality;
    @NonNull
    private String address;
}
