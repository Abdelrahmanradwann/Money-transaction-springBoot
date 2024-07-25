package com.example.FirstProject.service;

import com.example.FirstProject.exception.custom.CustomerNotFoundException;

import com.example.FirstProject.model.Customer;
import com.example.FirstProject.dto.RetrieveCustomerDTO;
import com.example.FirstProject.dto.UpdateCustomerDTO;
import com.example.FirstProject.repository.CustomerRepository;
import lombok.AllArgsConstructor;
import org.springframework.cache.annotation.Cacheable;
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
