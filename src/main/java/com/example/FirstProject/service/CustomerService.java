package com.example.FirstProject.service;

import com.example.FirstProject.exception.custom.CustomerNotFoundException;

import com.example.FirstProject.model.Customer;
import com.example.FirstProject.dto.RetrieveCustomerDTO;
import com.example.FirstProject.dto.UpdateCustomerDTO;
import com.example.FirstProject.repository.CustomerRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;



@Service
@AllArgsConstructor
public class CustomerService implements CustomerData{
    private CustomerRepository customerRepository;

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
        Customer cx = this.customerRepository.save(customer);
        return cx.toDTO();
    }
    @Override
    public Boolean deleteCustomer(Long id) throws CustomerNotFoundException {
        Customer customer = this.customerRepository.findById(id)
                .orElseThrow(()->new CustomerNotFoundException(String.format("Customer with id = %s not found",id)));
        this.customerRepository.delete(customer);
        return true;
    }


    public RetrieveCustomerDTO checkCustomerEmail(String email) throws CustomerNotFoundException {
        Customer customer = customerRepository.findUserByEmail(email)
                .orElseThrow(() -> new CustomerNotFoundException(String.format("Customer with Id %s not found", email)));
        return customer.toDTO();
    }


    public RetrieveCustomerDTO getCustomerByAccountNumber(String accountNumber) throws CustomerNotFoundException {
        Customer customer = customerRepository.findByAccountNumber(accountNumber)
                .orElseThrow(() -> new CustomerNotFoundException("Account number not found"));
        return customer.toDTO();

    }




}
