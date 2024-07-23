package com.example.FirstProject.controller;


import com.example.FirstProject.exception.custom.CustomerAlreadyExistsException;
import com.example.FirstProject.exception.custom.CustomerNotFoundException;
import com.example.FirstProject.model.Customer;
import com.example.FirstProject.dto.RetrieveCustomerDTO;
import com.example.FirstProject.dto.UpdateCustomerDTO;
import com.example.FirstProject.dto.CustomerDTO;
import com.example.FirstProject.service.CustomerData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;


@RequestMapping("api")
@RestController
@Validated
public class CustomerController {
    @Autowired
    private CustomerData customerData;
    @PostMapping("/customer")
    public Customer createCustomer(@RequestBody CustomerDTO customer) throws CustomerAlreadyExistsException {
        return  this.customerData.createCustomer(customer);
    }
    @GetMapping("/customer/{id}")
    public RetrieveCustomerDTO getCustomerById(@PathVariable Long id) throws CustomerNotFoundException {
        return this.customerData.getCustById(id);
    }
    @GetMapping("/customer/email/{email}")
    public RetrieveCustomerDTO getCustomerByEmail(@PathVariable String email) throws CustomerNotFoundException{
        return this.customerData.getCustomerByEmail(email);
    }
    @PutMapping("/customer/{id}")
    public RetrieveCustomerDTO updateCustomer(@PathVariable Long id,
                                              @RequestBody UpdateCustomerDTO customer) throws CustomerNotFoundException{
        return this.customerData.updateCust(id,customer);
    }
    @DeleteMapping("/customer/{id}")
    public Boolean deleteCustomer(@PathVariable Long id) throws CustomerNotFoundException{
        return this.customerData.deleteCustomer(id);
    }
    @GetMapping("/customer/latest")
    public Customer getLatestCustomer(){
        return this.customerData.getLatestCust();
    }

}
