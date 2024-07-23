package com.example.FirstProject.service;

import com.example.FirstProject.exception.custom.CustomerAlreadyExistsException;
import com.example.FirstProject.exception.custom.CustomerNotFoundException;
import com.example.FirstProject.model.Account;
import com.example.FirstProject.model.Customer;
import com.example.FirstProject.dto.RetrieveCustomerDTO;
import com.example.FirstProject.dto.UpdateCustomerDTO;
import com.example.FirstProject.dto.CustomerDTO;
import com.example.FirstProject.model.enums.AccountType;
import com.example.FirstProject.model.enums.Currency;
import com.example.FirstProject.repository.AccountRepository;
import com.example.FirstProject.repository.CustomerRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;


@Service
@AllArgsConstructor
public class CustomerService implements CustomerData{
    private CustomerRepository customerRepository;
    private AccountRepository accountRepository;
    @Override
    @Transactional(isolation = Isolation.READ_COMMITTED)
    public Customer createCustomer(CustomerDTO customer) throws CustomerAlreadyExistsException {
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
    public RetrieveCustomerDTO getCustById(Long id) throws CustomerNotFoundException {
        Customer customer = this.customerRepository.findById(id)
                .orElseThrow(()->new CustomerNotFoundException(String.format("Customer with id = %s not found",id)));
        return customer.toDTO();
    }
    @Override
    public RetrieveCustomerDTO getCustomerByEmail(String email) throws CustomerNotFoundException {
        Customer customer = this.customerRepository.findByemailEquals(email)
                .orElseThrow(()->new CustomerNotFoundException(String.format("Customer with email = %s not found",email)));
        return customer.toDTO();
    }
    @Override
    public RetrieveCustomerDTO updateCust(Long id, UpdateCustomerDTO c) throws CustomerNotFoundException{
        Customer customer = this.customerRepository.findById(id)
                .orElseThrow(()->new CustomerNotFoundException(String.format("Customer with id = %s not found",id)));
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
    public Boolean deleteCustomer(Long id) throws CustomerNotFoundException {
        Customer customer = this.customerRepository.findById(id)
                .orElseThrow(()->new CustomerNotFoundException(String.format("Customer with id = %s not found",id)));
        this.customerRepository.delete(customer);
        return true;
    }
    @Override
    public Customer getLatestCust(){
        return this.customerRepository
                .findTopByOrderByRegisterDesc()
                .orElseThrow(()->new IllegalArgumentException("No Customer found"));
    }
}
