package com.example.FirstProject.service;

import com.example.FirstProject.exception.custom.CustomerNotFoundException;
import com.example.FirstProject.model.Customer;
import com.example.FirstProject.dto.RetrieveCustomerDTO;
import com.example.FirstProject.dto.UpdateCustomerDTO;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;


public interface CustomerData {

    /**
     * Retrieves a customer by their ID.
     *
     * @param id the ID of the customer to retrieve
     * @return the customer details wrapped in a {@link RetrieveCustomerDTO}
     * @throws CustomerNotFoundException if the customer with the given ID is not found
     */
    @Cacheable("CustomedId")
    RetrieveCustomerDTO getCustById(Long id) throws CustomerNotFoundException;

    /**
     * Retrieves a customer by their email.
     *
     * @param email the email of the customer to retrieve
     * @return the customer details wrapped in a {@link RetrieveCustomerDTO}
     * @throws CustomerNotFoundException if the customer with the given email is not found
     */
    RetrieveCustomerDTO getCustomerByEmail(String email) throws CustomerNotFoundException;

    /**
     * Updates the details of a customer.
     *
     * @param id he ID of the customer to update
     * @param customer the new details of the customer wrapped in a {@link UpdateCustomerDTO}
     * @return the updated customer details wrapped in a {@link RetrieveCustomerDTO}
     * @throws CustomerNotFoundException if the customer with the given ID is not found
     */
    RetrieveCustomerDTO updateCust(Long id, UpdateCustomerDTO customer) throws CustomerNotFoundException;

    /**
     * Deletes a customer by their ID.
     *
     * @param id the ID of the customer to delete
     * @return true if the customer was successfully deleted, false otherwise
     * @throws CustomerNotFoundException if the customer with the given ID is not found
     */
    Boolean deleteCustomer(Long id) throws CustomerNotFoundException;

    /**
     * Retrieves the latest added customer.
     *
     * @return the latest customer details wrapped in a {@link Customer}
     */
    Customer getLatestCust();
}
