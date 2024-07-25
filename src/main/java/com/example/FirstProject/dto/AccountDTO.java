package com.example.FirstProject.dto;


import com.example.FirstProject.model.enums.AccountType;
import com.example.FirstProject.model.enums.Currency;

import lombok.Builder;
import lombok.Data;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Builder
@Data
public class AccountDTO {
    private Long id;
    private String accountNumber;
    private String accountName;
    private Double balance;
    private Currency currency;
    private AccountType accountType;
    private LocalDate accountCreationDate;
    private String accountDescription;
    private LocalDateTime updatedAt;
    private LocalDateTime createdAt;
    private Boolean active=true;

}
