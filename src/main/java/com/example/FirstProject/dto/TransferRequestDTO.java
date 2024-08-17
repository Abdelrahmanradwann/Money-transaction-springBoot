package com.example.FirstProject.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import com.example.FirstProject.dto.enums.AccountCurrency;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class TransferRequestDTO {
    private String recipientAccountNumber;
    private Double amount;
    private AccountCurrency currency;
}
