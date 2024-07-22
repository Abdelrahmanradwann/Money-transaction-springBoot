package com.example.FirstProject.dto;

import com.example.FirstProject.model.Account;
import com.example.FirstProject.model.enums.Gender;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@AllArgsConstructor
@Data
@NoArgsConstructor
@Builder
public class RetrieveCustomerDTO {
    private Long id;
    private  String fname;
    private  String lname;
    private LocalDate  DOB;
    private  String nationality;
    private String nationalId;
    private String address;
    private Gender gender;
    private AccountDTO account;
}
