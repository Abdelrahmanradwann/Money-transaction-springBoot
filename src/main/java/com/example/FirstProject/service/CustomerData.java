package com.example.FirstProject.service;

import com.example.FirstProject.exception.custom.CustomerAlreadyExistsException;
import com.example.FirstProject.exception.custom.CustomerNotFoundException;
import com.example.FirstProject.model.Customer;
import com.example.FirstProject.dto.RetrieveCustomerDTO;
import com.example.FirstProject.dto.UpdateCustomerDTO;
import com.example.FirstProject.dto.CustomerDTO;
import org.springframework.stereotype.Service;


@Service
public interface CustomerData {

    Customer createCustomer(CustomerDTO customer) throws CustomerAlreadyExistsException;
    RetrieveCustomerDTO getCustById(Long id) throws CustomerNotFoundException;
    RetrieveCustomerDTO getCustomerByEmail(String email) throws CustomerNotFoundException;

    RetrieveCustomerDTO updateCust(Long id,
                                   UpdateCustomerDTO customer) throws CustomerNotFoundException;
    Boolean deleteCustomer(Long id) throws CustomerNotFoundException;

    Customer getLatestCust();
}


