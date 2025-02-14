package com.example.FirstProject.service.security;


import com.example.FirstProject.dto.CustomerDTO;
import com.example.FirstProject.dto.LoginRequestDTO;
import com.example.FirstProject.dto.LoginResponseDTO;
import com.example.FirstProject.dto.enums.AccountCurrency;
import com.example.FirstProject.exception.custom.CustomerAlreadyExistsException;
import com.example.FirstProject.model.Account;
import com.example.FirstProject.model.Customer;
import com.example.FirstProject.model.enums.AccountType;
import com.example.FirstProject.repository.AccountRepository;
import com.example.FirstProject.repository.CustomerRepository;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.UUID;


@AllArgsConstructor
@Service
public class Authenticator implements IAuthenticator {
    private CustomerRepository customerRepository;
    private AccountRepository accountRepository;
    private PasswordEncoder encoder;
    private final JwtUtils jwtUtils;
    private AuthenticationManager authenticationManager;
    @Override
    @Transactional(rollbackFor = Exception.class, isolation = Isolation.READ_COMMITTED)
    // ensures atomic transactions and prevent dirty reads
    public Customer register(CustomerDTO customer)throws CustomerAlreadyExistsException{
        if(this.customerRepository.existsByEmail(customer.getEmail())){
            throw new CustomerAlreadyExistsException(String.format("Customer with email %s already exists",customer.getEmail()));
        }
        if(this.customerRepository.existsByNationalId(customer.getNationalId())){
            throw new CustomerAlreadyExistsException(String.format("Customer with national id %s already exists",customer.getNationalId()));
        }
        if(this.customerRepository.existsByPhoneNumber(customer.getPhoneNumber())){
            throw new CustomerAlreadyExistsException(String.format("Customer with national id %s already exists",customer.getNationalId()));
        }
        Account account =  Account
                .builder()
                .accountNumber(UUID.randomUUID().toString())
                .accountName(customer.getFname())
                .balance(1000.00)
                .currency(AccountCurrency.EGP)
                .accountType(AccountType.BUSINESS)
                .accountDescription("Primary savings account")
                .active(true)
                .build();
        this.accountRepository.save(account);

        Customer c = Customer
                .builder()
                .email(customer.getEmail())
                .password(encoder.encode(customer.getPassword()))
                .DOB(customer.getDOB())
                .phoneNumber(customer.getPhoneNumber())
                .fname(customer.getFname())
                .lname(customer.getLname())
                .address(customer.getAddress())
                .gender(customer.getGender())
                .nationality(customer.getNationality())
                .nationalId(customer.getNationalId())
                .account(account)
                .build();

        this.customerRepository.save(c);
        return c;
    }

    public LoginResponseDTO login(@RequestBody LoginRequestDTO loginRequestDTO) throws CustomerAlreadyExistsException{
        // Validates the info of user
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginRequestDTO.getEmail(),loginRequestDTO.getPassword()));

        // Save his info and like marks it that it is valid for the remaining functionality
        // and routes that will be used
        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = jwtUtils.generateJwtToken(authentication);
        CustomerDetailsImpl customerDetails = (CustomerDetailsImpl) authentication.getPrincipal();
        LoginResponseDTO tmp =  LoginResponseDTO.builder()
                .token(jwt)
                .message("Login successful")
                .status(HttpStatus.ACCEPTED)
                .token(jwt)
                .tokenType("Bearer")
                .build();
        return tmp;



    }

}
