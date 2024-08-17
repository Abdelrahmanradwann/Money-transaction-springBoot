package com.example.FirstProject.controller;
import com.example.FirstProject.dto.RetrieveCustomerDTO;
import com.example.FirstProject.dto.TransactionDTO;
import com.example.FirstProject.exception.custom.CustomerNotFoundException;
import com.example.FirstProject.exception.custom.TransactionNotFoundException;
import com.example.FirstProject.service.CustomerService;
import com.example.FirstProject.service.TransactionService;
import com.example.FirstProject.service.security.JwtUtils;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/api/transactions")
@Component
@AllArgsConstructor
public class TransactionController {
    private final TransactionService transactionService;
    private final CustomerService customerService;
    private final JwtUtils Jwt;
    @GetMapping
    public List<TransactionDTO> getTransactionHistory(
            @RequestHeader("Authorization") String authorizationHeader,
            @RequestParam Integer page,
            @RequestParam Integer size) throws CustomerNotFoundException {
        if((authorizationHeader) == null) {
            throw new CustomerNotFoundException("UnAuthorized");
        }
        authorizationHeader = authorizationHeader.substring(7);
        String customerEmail = this.Jwt.getUserNameFromJwtToken((authorizationHeader));
        RetrieveCustomerDTO customer = this.customerService.checkCustomerEmail(customerEmail);
        return transactionService.getTransactionHistory(customer.getAccount().getAccountNumber(),page, size);
    }

    @GetMapping("/filtered")
    public List<TransactionDTO> getTransactionHistoryWithFilters(
            @RequestHeader("Authorization") String authorizationHeader,
            @RequestParam LocalDateTime startDate,
            @RequestParam LocalDateTime endDate,
            @RequestParam Integer page,
            @RequestParam Integer size) throws CustomerNotFoundException{

        authorizationHeader = authorizationHeader.substring(7);

        if((authorizationHeader) == null) {
            throw new CustomerNotFoundException("UnAuthorized");
        }

        String customerEmail = this.Jwt.getUserNameFromJwtToken((authorizationHeader));
        RetrieveCustomerDTO customer = this.customerService.checkCustomerEmail(customerEmail);
        return transactionService.getTransactionHistoryWithFilters(customer.getAccount().getAccountNumber(), startDate,endDate, page, size);
    }

    @GetMapping("/{transactionId}")
    @Operation(summary = "Get Transaction by ID")
    @ApiResponse(responseCode = "200", content = {@Content(mediaType = "application/json")})
    @ApiResponse(responseCode = "404", content = {@Content(mediaType = "application/json")})
    public TransactionDTO getTransactionById(
            @RequestHeader("Authorization") String authorizationHeader,
            @PathVariable Long transactionId) throws TransactionNotFoundException, CustomerNotFoundException {

        authorizationHeader = authorizationHeader.substring(7);
        if (authorizationHeader == null) {
            throw new CustomerNotFoundException("UnAuthorized");
        }

        return transactionService.getTransactionById(transactionId);
    }

}