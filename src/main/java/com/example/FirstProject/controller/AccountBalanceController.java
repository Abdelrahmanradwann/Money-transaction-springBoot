package com.example.FirstProject.controller;

import com.example.FirstProject.dto.RetrieveCustomerDTO;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;

import com.example.FirstProject.exception.custom.AccountNotFoundException;
import com.example.FirstProject.exception.custom.CustomerNotFoundException;
import com.example.FirstProject.service.AccountBalanceService;
import com.example.FirstProject.service.CustomerService;
import com.example.FirstProject.service.security.JwtUtils;


@RestController
@RequestMapping("/api/balance")
@Data
@RequiredArgsConstructor
@Component
public class AccountBalanceController {

    private final JwtUtils jwtUtils;
    private final CustomerService customerService;
    private final AccountBalanceService accountBalanceService;
    @GetMapping
    public Double getBalance(@RequestHeader("Authorization") String authorizationHeader) throws AccountNotFoundException,CustomerNotFoundException{
        authorizationHeader = authorizationHeader.substring(7);
        String customerEmail = this.jwtUtils.getUserNameFromJwtToken(authorizationHeader);
        RetrieveCustomerDTO customer = this.customerService.checkCustomerEmail(customerEmail);
        String accountNumber = customer.getAccount().getAccountNumber();
        if(accountNumber==null){
            throw new AccountNotFoundException(String.format("Customer with id %s doesn't have an account",customer.getId()));
        }
        return accountBalanceService.getBalance(accountNumber);
    }

}