package com.example.FirstProject.service;

import com.example.FirstProject.model.Account;
import com.example.FirstProject.model.Customer;
import com.example.FirstProject.dto.RetrieveCustomerDTO;
import com.example.FirstProject.dto.UpdateCustomerDTO;
import com.example.FirstProject.dto.CustomerDTO;
import com.example.FirstProject.model.enums.AccountType;
import com.example.FirstProject.model.enums.Currency;
import com.example.FirstProject.model.enums.Gender;
import com.example.FirstProject.repository.AccountRepository;
import com.example.FirstProject.repository.CustomerRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.*;


@Service
@AllArgsConstructor
public class CustomerService implements CustomerData{
    private CustomerRepository customerRepository;
    private AccountRepository accountRepository;
    @Override
    public Customer createCustomer(CustomerDTO customer) {
        Account account =  Account
                .builder()
                .accountNumber("123456789")
                .accountName("John Doe's Savings Account")
                .balance(1000.00)
                .currency(Currency.EGP)
                .accountType(AccountType.BUSINESS)
                .accountDescription("Primary savings account")
                .active(true)
                .build();
        this.accountRepository.save(account);

        Customer c = Customer
                .builder()
                .email(customer.getEmail())
                .password(customer.getPassword())
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

        //?????
//       Customer cc = new Customer(
//                customer.getEmail(),
//                customer.getPassword(),
//                customer.getFname(),
//                customer.getLname(),
//                customer.getDOB(),
//                customer.getNationality(),
//                customer.getNationalId(),
//                customer.getAddress(),
//                customer.getGender())
//
//        )
        this.customerRepository.save(c);
        return c;
    }

    @Override
    public RetrieveCustomerDTO getCustById(Long id) {
        Customer customer = this.customerRepository.findById(id)
                .orElseThrow(()->new IllegalArgumentException(String.format("Customer with id = %s not found",id)));
        return customer.toDTO();
    }
    @Override
    public RetrieveCustomerDTO getCustomerByEmail(String email) {
        Customer customer = this.customerRepository.findByemailEquals(email)
                .orElseThrow(()->new IllegalArgumentException("error"));
        return customer.toDTO();
    }
    @Override
    public RetrieveCustomerDTO updateCust(Long id, UpdateCustomerDTO c) {
        Customer customer = customerRepository.findById(id).orElseThrow(()->new IllegalArgumentException("eRROR"));
        customer.setFname(c.getFname());
        customer.setLname(c.getLname());
        customer.setDOB(c.getDOB());
        customer.setAddress(c.getAddress());
        customer.setPassword(c.getPassword());
        customer.setNationality(c.getNationality());
        this.customerRepository.save(customer);
        return customer.toDTO();
    }
    @Override
    public Boolean deleteCustomer(Long id) {
        Customer c = customerRepository.findById(id)
                .orElseThrow(()->new IllegalArgumentException("Error while deleting customer"));
        this.customerRepository.delete(c);
        return true;
    }
    @Override
    public Customer getLatestCust(){
        return this.customerRepository
                .findTopByOrderByRegisterDesc()
                .orElseThrow(()->new IllegalArgumentException("No Customer found"));
    }
}
