package com.example.FirstProject.controller;

import com.example.FirstProject.dto.RetrieveCustomerDTO;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;
import com.example.FirstProject.dto.TransactionDTO;
import com.example.FirstProject.dto.TransferRequestDTO;
import com.example.FirstProject.dto.enums.AccountCurrency;
import com.example.FirstProject.dto.enums.TransactionStatus;
import com.example.FirstProject.exception.custom.CustomerNotFoundException;
import com.example.FirstProject.service.CustomerService;
import com.example.FirstProject.service.TransactionService;
import com.example.FirstProject.service.security.JwtUtils;

@Data
@Component
@RestController
@RequestMapping("/api/transfer")
@RequiredArgsConstructor
public class MoneyTransferController {

    private final CustomerService customerService;
    private final TransactionService transactionService;
    private final JwtUtils jwt;

    @PostMapping
    public TransactionDTO transferMoney(
            @RequestHeader("Authorization") String authorizationHeader,
            @RequestBody TransferRequestDTO transferRequest ) throws Exception, CustomerNotFoundException {
        authorizationHeader = authorizationHeader.substring(7);
        String recipientAccountNumber = transferRequest.getRecipientAccountNumber();
        Double amount = transferRequest.getAmount();
        AccountCurrency currency = transferRequest.getCurrency();

        if((authorizationHeader) == null) {
            throw new CustomerNotFoundException("UnAuthorized");
        }

        String customerEmail = this.jwt.getUserNameFromJwtToken((authorizationHeader));
        RetrieveCustomerDTO customer = this.customerService.checkCustomerEmail(customerEmail);
        RetrieveCustomerDTO recipientCustomer = this.customerService.getCustomerByAccountNumber(recipientAccountNumber);
        TransactionStatus isValid = this.transactionService.isValidTransaction(customer,recipientCustomer,amount,currency);
        return this.transactionService.addTransaction(customer,recipientAccountNumber,isValid,amount,currency).toDTO();
    }

}