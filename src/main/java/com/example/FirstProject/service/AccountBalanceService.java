package com.example.FirstProject.service;

import com.example.FirstProject.model.Account;
import com.example.FirstProject.repository.AccountRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AccountBalanceService {
    private final AccountRepository accountRepository;
    public Double getBalance(String accountNumber){
        Account acc = this.accountRepository.findByAccountNumber(accountNumber);
        return acc.getBalance();
    }
}
