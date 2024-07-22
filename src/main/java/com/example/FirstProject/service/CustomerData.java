package com.example.FirstProject.service;

import com.example.FirstProject.model.Customer;
import com.example.FirstProject.dto.RetrieveCustomerDTO;
import com.example.FirstProject.dto.UpdateCustomerDTO;
import com.example.FirstProject.dto.CustomerDTO;
import org.springframework.stereotype.Service;


@Service
public interface CustomerData {

    Customer createCustomer(CustomerDTO customer);
    RetrieveCustomerDTO getCustById(Long id) ;
    RetrieveCustomerDTO getCustomerByEmail(String email);

    RetrieveCustomerDTO updateCust(Long id,
                                   UpdateCustomerDTO customer);
    Boolean deleteCustomer(Long id);

    Customer getLatestCust();
}


