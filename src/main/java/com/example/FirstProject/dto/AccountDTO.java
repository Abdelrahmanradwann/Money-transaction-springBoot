package com.example.FirstProject.dto;

import com.example.FirstProject.model.Account;
import com.example.FirstProject.model.enums.AccountType;
import com.example.FirstProject.model.enums.Currency;
import jakarta.persistence.*;
import jdk.jshell.Snippet;
import lombok.Builder;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

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
