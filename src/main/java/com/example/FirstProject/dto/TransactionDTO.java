package com.example.FirstProject.dto;

import com.example.FirstProject.dto.enums.AccountCurrency;
import com.example.FirstProject.dto.enums.TransactionStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TransactionDTO {

    private Long id;
    private String senderAccountNumber;
    private String recipientAccountNumber;
    private String senderName;
    private String recipientName;
    private double amount;
    private LocalDateTime transactionTime;
    private AccountCurrency currency;
    private final String cardType = "Mastercard";
    private TransactionStatus status;
}