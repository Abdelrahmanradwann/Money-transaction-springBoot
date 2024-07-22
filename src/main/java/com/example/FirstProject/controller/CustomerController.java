package com.example.FirstProject.controller;


import com.example.FirstProject.model.Customer;
import com.example.FirstProject.dto.RetrieveCustomerDTO;
import com.example.FirstProject.dto.UpdateCustomerDTO;
import com.example.FirstProject.dto.CustomerDTO;
import com.example.FirstProject.service.CustomerData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@RequestMapping("api")
@RestController
public class CustomerController {
    @Autowired
    private CustomerData customerData;
    @PostMapping("/customer")
    public Customer createCustomer(@RequestBody CustomerDTO customer){
        return  this.customerData.createCustomer(customer);
    }
    @GetMapping("/customer/{id}")
    public RetrieveCustomerDTO getCustomerById(@PathVariable Long id)  {
        return this.customerData.getCustById(id);
    }
    @GetMapping("/customer/email/{email}")
    public RetrieveCustomerDTO getCustomerByEmail(@PathVariable String email){
        return this.customerData.getCustomerByEmail(email);
    }
    @PutMapping("/customer/{id}")
    public RetrieveCustomerDTO updateCustomer(@PathVariable Long id,
                                              @RequestBody UpdateCustomerDTO customer){
        return this.customerData.updateCust(id,customer);
    }
    @DeleteMapping("/customer/{id}")
    public Boolean deleteCustomer(@PathVariable Long id){
        return this.customerData.deleteCustomer(id);
    }
    @GetMapping("/customer/latest")
    public Customer getLatestCustomer(){
        return this.customerData.getLatestCust();
    }

}
